package id.ac.its.fppbo.game;

public class Missile extends Sprite {
	
	private int BOARD_HEIGHT;
	private int missileSpeed;
	
	public Missile(int x, int y, int speed){
		super(x,y);
		missileSpeed = speed;
		initMissile();
	}
	
	private void initMissile() {
		loadImage("src/resource/SpaceShip/missile.png");
		getImageDimension();
	}
	
	public void move() {
		y+=missileSpeed;
		
		if(y==0) {
//			visible=false;
		}
	}
}
