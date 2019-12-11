package id.ac.its.fppbo.menu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Main {
	
	private Image image;
	
	public Main() {
		
	JFrame frame = new JFrame();
	frame.setSize(500, 600);
	
	ImageIcon img = new ImageIcon("src/id/ac/its/fppbo/player.png");
	image = img.getImage();
	image = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
	
	JPanel panel = new JPanel();
	JButton playbutton = new JButton("Play");
	JButton quitbutton = new JButton("Quit");
	JLabel label = new JLabel();
	panel.setBackground(Color.WHITE);
	
	panel.add(playbutton);
	panel.add(quitbutton);
	panel.add(label);

	frame.add(panel, BorderLayout.SOUTH);
	
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public Image getimage() {
		return image;
	}
	
	public static void main(String[] args) {
		
		new Main();
	}
}