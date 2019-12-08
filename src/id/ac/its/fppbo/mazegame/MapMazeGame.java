package id.ac.its.fppbo.mazegame;

import java.awt.Image;
import java.io.File;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class MapMazeGame {
	
	private Scanner m;

	private String Map[] = new String[18]; 
	
	private Image grass,
				  wall,
				  finish; 
	
	public MapMazeGame() {
		
		ImageIcon img = new ImageIcon("src/resource/MazeGame/grass.png");
		grass = img.getImage();
		grass = grass.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
		img = new ImageIcon("src/resource/MazeGame/wall.png"); 
		wall = img.getImage();
		wall = wall.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
		img = new ImageIcon("src/resource/MazeGame/door5.png"); 
		finish = img.getImage();
		finish = finish.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
		openFile();
		readFile();
		closeFile();
	}
	
	public Image getFinish() {
		return finish;
	}
	
	public Image getGrass() {
		return grass;
	}
	
	public Image getWall() {
		return wall;
	}
	
	public String getMap(int x, int y) {
		String index = Map[y].substring(x, x+1);
		return index;
	}
	
	public void openFile() {
		
		try {
		m = new Scanner(new File("src/resource/MazeGame/Map.TXT"));
		}catch(Exception e) {
			System.out.println("Error Loading Map");
		}
	}
	
	public void readFile() {
		while(m.hasNext()) {
			for(int i=0; i<18; i++) {
				 Map[i] = m.next(); 
			}
		}
	}

	public void closeFile() {
	  
	}
	
}
