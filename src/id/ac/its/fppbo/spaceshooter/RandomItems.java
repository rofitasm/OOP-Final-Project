package id.ac.its.fppbo.spaceshooter;

public class RandomItems extends Sprite {
	
	private int BOARD_HEIGHT = 600;
	private int itemSpeed;
	
	public RandomItems(int x, int y,int itemSpeed){
		super(x,y);
		
		this.itemSpeed = itemSpeed;
		initItems();
	}
	
	@Override
	protected void setLoc(int x, int y) {
		// TODO Auto-generated method stub
		super.setLoc(x, y);
		itemSpeed = 0;
	}

	private void initItems() {
		loadImage("src/resource/SpaceShip/box.png");
		getImageDimension();
	}
	
	public void move() {
		y+=itemSpeed;
		
	}
	
}
