package id.ac.its.fppbo.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PapanShooter extends JPanel implements ActionListener {

	private Timer timer;
	private SpaceShip spaceShip;
	private final int DELAY = 10;
	private List<Asteroid> asteroid;
	private int jumlahAsteroid;
	//posisi pesawat
	private final int PESAWAT_X = 250;
	private final int PESAWAT_Y = 500;
	
	public PapanShooter() {
		initBoard();
		initAsteroidWall();
	}
	
	private void initBoard() {
		addKeyListener(new TAdapter());
		setBackground(Color.black);
		setFocusable(true);
		
		spaceShip = new SpaceShip(PESAWAT_X, PESAWAT_Y);
		
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	private void initAsteroidWall() {
		asteroid = new ArrayList<>();
		initAsteroid();
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
			asteroid.add(new Asteroid((int)(Math.random() * 6) * 100,100,1));
		}
	//		asteroid.add(new Asteroid(100,100,1));

	}
	
	public List<Asteroid> getAsteroid() {
		return asteroid;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		doDrawing(g);
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), this);
		
		List<Missile> missiles = spaceShip.getMissiles();
		
		for(Missile missile : missiles) {
			g2d.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
		}
		
		List<Asteroid> asteroids = getAsteroid();
		
		for(Asteroid astero : asteroids) {
			g2d.drawImage(astero.getImage(), astero.getX(), astero.getY(), this);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		updateMissiles();
		updateSpaceShip();
		updateAsteroid();
		
		repaint();
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
