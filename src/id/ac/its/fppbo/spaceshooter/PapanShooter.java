package id.ac.its.fppbo.spaceshooter;

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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
	private Asteroid[] asteroids;
	private int jumlahAsteroid;
	private boolean inGame = true;
	private Boss boss;
	
	private static int asteroidCount = 0;
	private static int bulletCount = 0;
	//posisi pesawat dan boss
	private final int PESAWAT_X = 230;
	private final int PESAWAT_Y = 400;
	private final int BOSS_X = 230;
	private final int BOSS_Y = 30;
	
	
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
		boss = new Boss(BOSS_X, BOSS_Y);
		
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	private void initAsteroidWall() {
//		asteroid = new ArrayList<>();
		asteroids = new Asteroid[40];
		if(asteroidCount==0) {
			for(int i = 0;i < 40;i++) {
				if(asteroids[i] == null)
					asteroids[i] = new Asteroid(0, 700, 0);
			}
		}
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
		asteroidCount = randomAsteroid(jumlahAsteroid, asteroidCount);
		//contoh cara menambah asteroid 
		//asteroid.add(new Asteroid(100,100,1));

	}
	
	private int randomAsteroid(int size, int count) {
		System.out.println(size);
		ArrayList<Integer> list = new ArrayList<Integer>(6);
        for(int i = 1; i <= 6; i++) {
            list.add(i);
        }
        
        Random rand = new Random();
        for(int i = 0; i < size; i++) {
            int index = rand.nextInt(list.size());
//            asteroid.add(new Asteroid(list.remove(index) * 100,150,1));
            asteroids[count++%40] = new Asteroid(list.remove(index) * 100,150,1);
        }
        list.clear();
        return count;
	}
	
	private Asteroid[] getAsteroid() {
		return asteroids;
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
		
		if(boss.isVisible()) 
			g2d.drawImage(boss.getImage(), boss.getX(), boss.getY(), this);
		
		Missile[] missiles = spaceShip.getMissiles();
		
		for(int i = 0;i < 20; i++) {
			if(missiles[i].isVisible())
				g2d.drawImage(missiles[i].getImage(), missiles[i].getX(), missiles[i].getY(), this);
		}
		
		Asteroid[] asteroids = getAsteroid();
		
		for(Asteroid astero : asteroids) {
			if(astero.isVisible())
				g2d.drawImage(astero.getImage(), astero.getX(), astero.getY(), this);
		}
		
//		List<Bullet> bullets = boss.getBullets();
		Bullet[] bullet = boss.getBullets();
		
		for(int i = 0; i < 20; i++) {
			if(bullet[i].isVisible())
				g2d.drawImage(bullet[i].getImage(), bullet[i].getX(), bullet[i].getY(), this);
		}
		
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(10, 10, 166, 26);
		g2d.fillRect(0, 540, 500, 30);
		g2d.setColor(Color.RED);
		g2d.fillRect(13,13,20*spaceShip.getHealth(),20);
		g2d.fillRect(3, 543, 25*boss.getHealth(), 24);
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
		updateBoss();
		updateBullets();
		
		checkCollisions();
		
		repaint();
	}
	
	private void inGame() {
		if(!inGame) {
			timer.stop();
		}
	}
	
	private void updateMissiles() {
		Missile[] missiles = spaceShip.getMissiles();
		
		for(int i = 0; i< 20; i++) {
			Missile missile = missiles[i];
			
			if(missile.y<=-10 && missile.isVisible()) {
				missile.setVisible(false);;
			}
			
			if(missiles[i].isVisible()) {
				missile.move();
			}else {
//				missiles.remove(i); 
				missile.setLoc(0, 700);
			}
		}
	}
	
	private void updateAsteroid() {
		Asteroid[] asteroids = getAsteroid();
		
		for(int i=0;i < 40;i++) {
			Asteroid asteroid = asteroids[i];
			
//			if(asteroid.y>=600 && asteroid.isVisible());
//				asteroid.setVisible(false);;
			
			if(asteroid.isVisible()) {
				asteroid.move();
			}else {
//				asteroids.remove(i);
				asteroid.setLoc(0, 700);
			}
		}
	}
	
	private void updateSpaceShip() {
		spaceShip.move();
	}
	
	private void updateBoss() {
		boss.move();
	}
	
	private void updateBullets() {
		Bullet[] bullets = boss.getBullets();
		
		for(int i = 0; i < 20 ;i++) {
			Bullet bullet = bullets[i];
			
			if(bullet.y>=600 && bullet.isVisible())
				bullet.setVisible(false);
			
			if(bullet.isVisible()) {
				bullet.move();
			}else {
				bullet.setLoc(0, 700);
			}
		}
	}
	
	private void checkCollisions() {
		Rectangle recShip = new Rectangle(spaceShip.getBound());
		Rectangle recBoss = new Rectangle(boss.getBound());
		Missile[] ms = spaceShip.getMissiles();
		Asteroid[] astero = getAsteroid();
		//kolisi ship dan asteroid
		for(int i = 0; i < 40; i++) {
			Rectangle recAstero = new Rectangle(astero[i].getBound());
			if(recShip.intersects(recAstero) && astero[i].isVisible()) {
				astero[i].setVisible(false);
				spaceShip.getHit();
				System.out.println(spaceShip.getHealth());
			}
			//kolisi asteroid dan missile
			for(int j = 0; j < 20; j++) {
				Rectangle recMissile = new Rectangle(ms[j].getBound());
				if(recMissile.intersects(recAstero) && ms[j].isVisible() && astero[i].isVisible()) {
					astero[i].setVisible(false);
					ms[j].setVisible(false);
				}
			}
		}
		for(int i = 0; i< 20; i++) {
			Rectangle recMissile = new Rectangle(ms[i].getBound());
			if(recMissile.intersects(recBoss) && ms[i].isVisible()) {
				ms[i].setVisible(false);
//				invulerable frame after getting hit(belum diimplementasi)
				boss.getHit();
			}
		}
		
		
	}
	
	private class TAdapter extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			spaceShip.keyPressed(e);
			boss.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			spaceShip.keyReleased(e);
		}
	}
}
