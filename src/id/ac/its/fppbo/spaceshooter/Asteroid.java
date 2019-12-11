package id.ac.its.fppbo.spaceshooter;

public class Asteroid extends Sprite {
	
	private int BOARD_HEIGHT = 600;
	private int asteroidSpeed;
	
	public Asteroid(int x, int y,int asteroidSpeed){
		super(x,y);
		
		this.asteroidSpeed = asteroidSpeed;
		initAsteroid();
	}
	
	@Override
	protected void setLoc(int x, int y) {
		// TODO Auto-generated method stub
		super.setLoc(x, y);
		asteroidSpeed = 0;
	}

	private void initAsteroid() {
		loadImage("src/resource/SpaceShip/asteroid.png");
		getImageDimension();
	}
	
	public void move() {
		y+=asteroidSpeed;
		
	}
	
}
