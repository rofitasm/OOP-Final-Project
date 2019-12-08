package id.ac.its.fppbo.spaceshooter;

public class Bullet extends Sprite {

	private int BOARD_HEIGHT = 600;
	private int bulletSpeed;
	
	public Bullet(int x, int y, int speed) {
		super(x,y);
		bulletSpeed = speed;
		initBullet();
	}
	
	private void initBullet() {
		loadImage("src/resource/SpaceShip/bullet.png");
		getImageDimension();
	}
	
	public void move() {
		y+=bulletSpeed;
		if(y>BOARD_HEIGHT) {
//			visible=false;
		}
	}
	
	
}
