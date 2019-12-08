package id.ac.its.fppbo.game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Boss extends Sprite {
	private int dx = 1;
	private List<Bullet> bullets;
	private int health = 20;
	private int MAX_HEALTH = 20;

	//waktu untuk cooldown
	public long lastBullet = 0;
	
	public Boss(int x, int y) {
		super(x, y);
		
		initBoss();
	}
	
	private void initBoss() {
		initBossBullet();
		
		loadImage("src/resource/SpaceShip/boss.png");
		getImageDimension();
	}
	
	private void initBossBullet() {
		bullets = new ArrayList<>();
	}
	
	public void move() {
		if(x+width == 500)
			dx = -1;
		if(x == 0)
			dx = 1;
		x+=dx;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getHealth() {
		return health;
	}
	
	public List<Bullet> getBullets(){
		return bullets;
	}
	
	public void getHit() {
		health-=1;
	}
	
	public void fire() {
		bullets.add(new Bullet(x+width/2-6, y+height, 1));
	}
	
	//untuk test
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_M) {
			long now = System.currentTimeMillis();
			if(now - lastBullet > 500) {
				fire();
				lastBullet = now;
			}
		}
		
		if(key == KeyEvent.VK_B) {
			health-=1;
		}
		
		if(key == KeyEvent.VK_N) {
			health+=1;
		}
	}
	
	
		
}
