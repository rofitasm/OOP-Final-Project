 package id.ac.its.fppbo.mazegame;

import java.awt.Color;
import java.awt.Font;
//import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import id.ac.its.fppbo.spaceshooter.TestBoard;



public class MazeGameBoard extends JPanel implements  ActionListener{
	
	private int a = 0;
	
	private Timer timer;
	
	private MapMazeGame m;
	private Player p;
	
	private boolean win = false;

	private String message = "";
	private Font font = new Font("Serif", Font.BOLD, 48);
	
	
	public MazeGameBoard() {
		
		m = new MapMazeGame();
		p = new Player();
		addKeyListener(new Al());
		setFocusable(true);
		
		timer = new Timer(20,this);
		timer.restart();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (m.getMap(p.getTileX(), p.getTileY()).equals("f")) {
			message = "Selamat!";
			win = true;
		}
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
	if(!win) {
			for(int y = 0; y<18; y++) {
				for(int x=0; x<18; x++) {
					if(m.getMap(x,y).equals("f")) {
						g.drawImage(m.getFinish(), x*25, y*25, null);
					}
					if(m.getMap(x,y).equals("o")) {
						g.drawImage(m.getGrass(), x*25, y*25, null);
					}
					if(m.getMap(x,y).equals("x")) {
						g.drawImage(m.getWall(), x*25 , y*25, null);
						}
					}
				}	
			g.drawImage(p.getPlayer(), p.getTileX()*25, p.getTileY()*25, null);
			
	}
	
	else {
		g.setColor(Color.ORANGE);
		g.setFont(font);
		g.drawString(message, 150, 350);
		if(a==0) {
			TestBoard tb = new TestBoard();
			tb.setVisible(true);
			
		}
		a++;
		}
	
		
	}
	
	public class Al extends KeyAdapter{
		
		public void keyPressed(KeyEvent e) {

			
			int keycode = e.getKeyCode();
			
			if(keycode == KeyEvent.VK_UP) {
				if(!m.getMap(p.getTileX(), p.getTileY()-1).equals("x"))
				p.move(0, -1);
			}
			if(keycode == KeyEvent.VK_DOWN) {
				if(!m.getMap(p.getTileX(), p.getTileY()+1).equals("x"))
				p.move(0, 1);
			}
			if(keycode == KeyEvent.VK_LEFT) {
				if(!m.getMap(p.getTileX()-1, p.getTileY()).equals("x"))
				p.move(-1, 0);
			}
			if(keycode == KeyEvent.VK_RIGHT) {
				if(!m.getMap(p.getTileX()+1, p.getTileY()).equals("x"))
				p.move(1, 0);
			}
			
		}
		
	
		public void keyReleased(KeyEvent e) {
			
		}
		
		public void keyTyped(KeyEvent e) {
		
		}
	}//end of player moving class
	

		
	    
	
	
}