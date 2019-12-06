package id.ac.its.fppbo.mazegame;

//import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;


public class MazeGameBoard extends JPanel implements  ActionListener{
	
	private Timer timer;
	
	private MapMazeGame m;
	
	public MazeGameBoard() {
		
		m = new MapMazeGame();
		
		timer = new Timer(25,this);
		timer.restart();
	}
	
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		for(int y = 0; y<18; y++) {
			for(int x=0; x<18; x++) {
				if(m.getMap(x,y).equals("o")) {
					g.drawImage(m.getGrass(), x*25, y*30, null);
				}
				if(m.getMap(x,y).equals("x")) {
					g.drawImage(m.getWall(), x*25 , y*30, null);
				}
			}
		}
//		g.setColor(Color.red);
//		g.fillRect(45, 60, 32, 32);
	}
}
