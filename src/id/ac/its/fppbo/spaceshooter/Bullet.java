package id.ac.its.fppbo.spaceshooter;

public class Bullet extends Sprite {

	private int BOARD_HEIGHT = 600;
	private int bulletSpeed;
	private int dxb;
	private int arah;
	
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
		arah = (int)(Math.random() * 2);
	}
	
	public void move() {
		y+=bulletSpeed;
		if(arah == 0)
			dxb=1;
		else
			dxb=-1;
		x+=dxb;
		if(y>=700) {
			visible=false;
		}
	}
	
	
}
