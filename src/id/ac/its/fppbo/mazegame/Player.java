package id.ac.its.fppbo.mazegame;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Player {

	private int x,y,tileX,tileY;
	
	private Image player;
	
	public Player() {
		ImageIcon img = new ImageIcon("src/resource/MazeGame/player.png");
		player = img.getImage();
		player = player.getScaledInstance(30, 25, Image.SCALE_DEFAULT);
		

		tileX=1;
		tileY=1;//??
		
	}
	
	public void move (int dx,int dy) {
		
		tileX+=dx;
		tileY+=dy;
	}
	
	public Image getPlayer() {
		return player;
	}

	public int getTileX() {
		return tileX;
	}

	public int getTileY() {
		return tileY;
	}
}
