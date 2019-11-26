package id.ac.its.fppbo.game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PapanShooter extends JPanel implements ActionListener {

	Container con;
	private Timer timer;
	private SpaceShip spaceShip;
	private final int DELAY = 10;
	private List<Asteroid> asteroid;
	private int jumlahAsteroid;
	private boolean inGame = true;
	//posisi pesawat
	private final int PESAWAT_X = 230;
	private final int PESAWAT_Y = 400;
	
	JPanel healthBarPanel;
	
	public PapanShooter() {
		initBoard();
		initAsteroidWall();
	}
	
	private void initBoard() {
		addKeyListener(new TAdapter());
		setBackground(Color.black);
		setFocusable(true);
		
		spaceShip = new SpaceShip(PESAWAT_X, PESAWAT_Y);
		healthBarPanel = new JPanel();
		healthBarPanel.setBounds(10, 10, 100, 15);
		healthBarPanel.setBackground(Color.BLUE);
		
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	private void initAsteroidWall() {
		asteroid = new ArrayList<>();
		//mengeluarkan asteroid setiap detik
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	    executorService.scheduleAtFixedRate(new Runnable() {
	        @Override          
	        public void run() {
	        	initAsteroid();
	        }
	    }, 0, 1, TimeUnit.SECONDS);
	}
	
	private void initAsteroid() {
		jumlahAsteroid = (int)(Math.random() * ((66 - 10) + 1)) + 10;
		if(jumlahAsteroid%10 == jumlahAsteroid) {
			if(jumlahAsteroid%10<=3)
				jumlahAsteroid = 1;
			else
				jumlahAsteroid = 6;
		}else{
			jumlahAsteroid /= 10;
		}
		//jarak antar asteroid masih belum pas
		for(int i=0;i<jumlahAsteroid;i++) {
			asteroid.add(new Asteroid((int)(Math.random() * 6) * 100,150,1));
			
		}
	//		asteroid.add(new Asteroid(100,100,1));

	}
	
	public List<Asteroid> getAsteroid() {
		return asteroid;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(inGame) {
			doDrawing(g);
		}else {
			drawGameOver(g);
		}
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if(spaceShip.isVisible())
			g2d.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), this);
		
		List<Missile> missiles = spaceShip.getMissiles();
		
		for(Missile missile : missiles) {
			if(missile.isVisible())
				g2d.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
		}
		
		List<Asteroid> asteroids = getAsteroid();
		
		for(Asteroid astero : asteroids) {
			if(astero.isVisible())
				g2d.drawImage(astero.getImage(), astero.getX(), astero.getY(), this);
		}
		
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(10, 10, 166, 26);
		g2d.setColor(Color.RED);
		g2d.fillRect(13,13,20*spaceShip.getHealth(),20);
	}
	
	private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (500 - fm.stringWidth(msg)) / 2, 600 / 2);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		inGame();
		
		updateMissiles();
		updateSpaceShip();
		updateAsteroid();
		
		checkCollisions();
		
		repaint();
	}
	
	private void inGame() {
		if(!inGame) {
			timer.stop();
		}
	}
	
	private void updateMissiles() {
		List<Missile> missiles = spaceShip.getMissiles();
		
		for(int i = 0; i< missiles.size(); i++) {
			Missile missile = missiles.get(i);
			
			if(missile.isVisible()) {
				missile.move();
			}else {
				missiles.remove(i);
			}
		}
	}
	
	private void updateAsteroid() {
		List<Asteroid> asteroids = getAsteroid();
		
		for(int i=0;i<asteroids.size();i++) {
			Asteroid asteroid = asteroids.get(i);
			
			if(asteroid.isVisible()) {
				asteroid.move();
			}else {
				asteroids.remove(i);
			}
		}
	}
	
	private void updateSpaceShip() {
		spaceShip.move();
	}
	
	private void checkCollisions() {
		Rectangle recShip = new Rectangle(spaceShip.getBound());
		//kolisi ship dan asteroid
		for(Asteroid astero : asteroid) {
			Rectangle recAstero = new Rectangle(astero.getBound());
			if(recShip.intersects(recAstero)) {
				astero.setVisible(false);
				spaceShip.getHit();
				System.out.println(spaceShip.getHealth());
			}
			//kolisi asteroid dan missile
			List<Missile> ms = spaceShip.getMissiles();
			for(Missile m : ms) {
				Rectangle recMissile = new Rectangle(m.getBound());
				if(recMissile.intersects(recAstero)) {
					astero.setVisible(false);
					m.setVisible(false);
				}
			}
		}
		
		
	}
	
	private class TAdapter extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			spaceShip.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			spaceShip.keyReleased(e);
		}
	}
}
