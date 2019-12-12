package id.ac.its.fppbo.spaceshooter;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


public class SpaceShip extends Sprite {
	
	private int dx;
	private int dy; 
//	private List<Missile> missiles;
	private Missile[] missiles;
	private int health = 3;
	private int MAX_HEALTH = 8;
	public long lastMissile = 0;
	private static int missileCount = 0;
	private int jumlahPeluru;
	
	public SpaceShip(int x, int y, int jumlahPeluru) {
		super(x,y);
		this.jumlahPeluru = jumlahPeluru;
		initSpaceShip();
	}
	
	private void initSpaceShip() {
		missiles = new Missile[20];
		if(missileCount==0) {
			for(int i = 0;i < 20;i++) {
				if(missiles[i] == null)
					missiles[i] = new Missile(0,800,0);
			}
		}
		
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
		
		
		if(y+height >= 540)
			y = 540 - height;
		else if(y <= 200)
			y = 200;
	}
	
	public void addMissile() {
		jumlahPeluru += 10;
	}
	
	public Missile[] getMissiles(){
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
			long now = System.currentTimeMillis();
			if(jumlahPeluru>0){
				if(now - lastMissile > 300) {
					jumlahPeluru--;
					missileCount = fire(missileCount);
					lastMissile = now; 
				}
			}else {
				System.out.println("Peluru Habis~!");
			}
		}
	}
	
	public int getJumlahPeluru() {
		return jumlahPeluru;
	}
	
	public void getHit() {
		health-=1;
	}
	
	public int fire(int count) {
		missiles[count++%20] = new Missile(x+width/2-6, y-height, -2);
		return count;
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
