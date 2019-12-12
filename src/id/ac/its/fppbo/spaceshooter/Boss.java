package id.ac.its.fppbo.spaceshooter;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Boss extends Sprite {
	private int dx = 1;
	private Bullet[] bullets;
	private int health = 20;
	private int MAX_HEALTH = 20;
	private static int bulletCount = 0;

	
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
		bullets = new Bullet[4];
		if(bulletCount == 0) {
			for(int i = 0; i < 4 ; i++) {
				if(bullets[i] == null)
					bullets[i] = new Bullet(0, 900, 0);
			}
		}
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
	
	public Bullet[] getBullets(){
		return bullets;
	}
	
	public void getHit() {
		health-=1;
	}
	
	public int fire(int count) {
		bullets[count++%4] = new Bullet(x+width/2-15, y+height, 1);
		return count;
	}
		
}
