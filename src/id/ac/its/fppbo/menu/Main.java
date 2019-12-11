package id.ac.its.fppbo.menu;
import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Main {
	
	private Image image;
	
	public Main() {
		menuframe();
	}
	
	public void menuframe() {
		JFrame frame = new JFrame();
		frame.setSize(500, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon img = new ImageIcon("player.png");
		image = img.getImage();
		image = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		
		JPanel panel = new JPanel();
		JButton playbutton = new JButton("Play");
		JButton quitbutton = new JButton("Quit");
		JLabel label = new JLabel();
		
		panel.add(playbutton);
		panel.add(quitbutton);
		panel.add(label);

		frame.add(panel, BorderLayout.SOUTH);
		
	}
	
	public static void main(String[] args) {
		
		new Main();
	}
}
