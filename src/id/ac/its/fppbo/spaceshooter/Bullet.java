package id.ac.its.fppbo.spaceshooter;

public class Bullet extends Sprite {

	private int BOARD_HEIGHT = 600;
	private int bulletSpeed;
	
	public Bullet(int x, int y, int speed) {
		super(x,y);
		bulletSpeed = speed;
		initBullet();
	}
	
	@Override
	protected void setLoc(int x, int y) {
		// TODO Auto-generated method stub
		super.setLoc(x, y);
		bulletSpeed = 0;
	}

	private void initBullet() {
		loadImage("src/resource/SpaceShip/bullet.png");
		getImageDimension();
	}
	
	public void move() {
		y+=bulletSpeed;
		if(y>BOARD_HEIGHT) {
			visible=false;
		}
	}
	
	
}
