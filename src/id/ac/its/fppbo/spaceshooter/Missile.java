package id.ac.its.fppbo.spaceshooter;

public class Missile extends Sprite {
	
	private int BOARD_HEIGHT;
	private int missileSpeed;
	
	public Missile(int x, int y, int speed){
		super(x,y);
		missileSpeed = speed;
		initMissile();
	}
	
	@Override
	protected void setLoc(int x, int y) {
		super.setLoc(x, y);
		missileSpeed = 0;
	}

	private void initMissile() {
		loadImage("src/resource/SpaceShip/missile.png");
		getImageDimension();
	}
	
	public void move() {
		y+=missileSpeed;
	}
}
