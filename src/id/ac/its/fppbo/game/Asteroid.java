package id.ac.its.fppbo.game;

public class Asteroid extends Sprite {
	
	private int BOARD_HEIGHT;
	private int asteroidSpeed;
	
	public Asteroid(int x, int y,int asteroidSpeed){
		super(x,y);
		
		this.asteroidSpeed = asteroidSpeed;
		initAsteroid();
	}
	
	private void initAsteroid() {
		loadImage("src/resource/SpaceShip/asteroid.png");
		getImageDimension();
	}
	
	public void move() {
		y+=asteroidSpeed;
	}
	
}
