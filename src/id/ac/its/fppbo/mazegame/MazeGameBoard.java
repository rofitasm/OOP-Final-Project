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
import id.ac.its.fppbo.mazegame.TestMazeGame;


public class MazeGameBoard extends JPanel implements  ActionListener{
	
	private int a = 0;
	
	private Timer timer;
	
	private MapMazeGame m;
	private Player p;
	
	private int bonusHealth = 0;
	private int bonusBullet = 0;
	
	private boolean win = false;

	private String message = "";
	private Font font = new Font("Serif", Font.BOLD, 48);
	
	private int heartLoc[][] = {{3,7,1},{10,9,1},{3,11,1},{14,12,1},{6,13,1}};
	private int bulletLoc[][] = {{3,1,1},{6,1,1},{14,1,1},{8,6,1},{5,16,1}};

	private boolean bullet[] = new boolean[5];
	private boolean heart[] = new boolean[5];
	
	
	
	
	public MazeGameBoard() {
		
		m = new MapMazeGame();
		p = new Player();
		addKeyListener(new Al());
		setFocusable(true);
	
		for(int i = 0; i < 5; i++) {
			heart[i] = true;
			bullet[i] = true;
		}
		
		timer = new Timer(20,this);
		timer.restart();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (m.getMap(p.getTileX(), p.getTileY()).equals("f")) {
			message = "Selamat!";
			win = true;
		}
		
		check(p.getTileX(),p.getTileY(),bullet, false);
		check(p.getTileX(),p.getTileY(),heart, true);
		
		
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
	if(!win) {
			for(int y = 0; y<18; y++) {
				for(int x=0; x<18; x++) {
					if(m.getMap(x,y).equals("f")) {
						g.drawImage(m.getFinish(), x*30, y*30, null);
						}
					if(m.getMap(x,y).equals("o")) {
						g.drawImage(m.getGrass(), x*30, y*30, null);
						}
					if(m.getMap(x,y).equals("x")) {
						g.drawImage(m.getWall(), x*30 , y*30, null);
						}
					if(m.getMap(x,y).equals("b")) {
						g.drawImage(m.getGrass(), x*30, y*30, null);
						for(int i = 0; i < 5 ; i++){
							if(bulletLoc[i][0] == x && bulletLoc[i][1] == y && bulletLoc[i][2] == 1)
								g.drawImage(m.getBullet(), x*30, y*30, null);
							}
						}
					if(m.getMap(x,y).equals("h")) {
						g.drawImage(m.getGrass(), x*30, y*30, null);
						for(int i = 0; i < 5 ; i++){
							if(heartLoc[i][0] == x && heartLoc[i][1] == y && heartLoc[i][2] == 1)
								g.drawImage(m.getHealth(), x*30, y*30, null);
							}
						}
					}
				}	
			g.drawImage(p.getPlayer(), p.getTileX()*30, p.getTileY()*30, null);
			
	}else {
		g.setColor(Color.ORANGE);
		g.setFont(font);
		g.drawString(message, 150, 350);
		if(a==0) {
			TestBoard tb = new TestBoard(bonusBullet, bonusHealth);
			tb.setVisible(true);
		}
		a=1;
	}
	}
	
	public void check(int x, int y, boolean[] arr, boolean type) {
		for(int i = 0; i < 5 ; i ++) {
			if(type==false) {
				if(bulletLoc[i][0] == x && bulletLoc[i][1] == y && arr[i] == true) {
					arr[i] = false;
					bulletLoc[i][2] = 0;
					bonusBullet++;
				}
			}else {
				if(heartLoc[i][0] == x && heartLoc[i][1] == y && arr[i] == true) {
					arr[i] = false;
					heartLoc[i][2] = 0;
					bonusHealth++;
				}
			}
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