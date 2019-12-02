package id.ac.its.fppbo.game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


public class SpaceShip extends Sprite {
	
	private int dx;
	private int dy; 
	private List<Missile> missiles;
	private int health = 3;
	private int MAX_HEALTH = 8;
	
	public SpaceShip(int x, int y) {
		super(x,y);

		initSpaceShip();
	}
	
	private void initSpaceShip() {
		missiles = new ArrayList<>();
		
		loadImage("src/resource/SpaceShip/pesawat.png");
		getImageDimension();
		
	}
	
	public void move() {
		x += dx;
		y += dy;
		if(x+width >= 500) 
			x = 500 - width;
		else if(x <= 0)
			x = 0;
		
		
		if(y+height >= 600)
			y = 600 - height;
		else if(y <= 200)
			y = 200;
	}
	
	public List<Missile> getMissiles(){
		return missiles;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT) {
			dx = -2;
		}
		
		if(key == KeyEvent.VK_RIGHT) {
			dx = 2;
		}
		
		if(key == KeyEvent.VK_UP) {
			dy = -2;
		}
		
		if(key == KeyEvent.VK_DOWN) {
			dy = 2;
		}
		
		if(key == KeyEvent.VK_SPACE) {
			fire();
		}
		//test hp
		if(key == KeyEvent.VK_C) {
			health-=1;
			System.out.println(health);
		}
		
		if(key == KeyEvent.VK_V) {
			health+=1;
			System.out.println(health);
		}
	}
	
	public void getHit() {
		health-=1;
	}
	
	public void fire() {
		missiles.add(new Missile(x+width/2-6, y-height, -2));
//		missiles.add(new Missile(x, y-height, -2));
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		
		if(key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
		
		if(key == KeyEvent.VK_UP) {
			dy = 0;
		}
		
		if(key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}
	
}
