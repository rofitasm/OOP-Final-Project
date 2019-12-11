package id.ac.its.fppbo.menu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Main {
	
	private Image image;
	Container con;
	JPanel titleNamePanel;
	JLabel titleNameLabel;
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
	
	public Main() {

	JFrame frame = new JFrame();
	frame.setSize(500, 600);
	
	JPanel panel2 = new JPanel();
	ImageIcon img = new ImageIcon("src/id/ac/its/fppbo/player.png");
	image = img.getImage();
	image = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
	JLabel label1 = new JLabel(img);
	frame.add(label1);
	
	JPanel panel1 = new JPanel();
	titleNamePanel = new JPanel();
	titleNamePanel.setBackground(Color.white);
	titleNameLabel = new JLabel("WANI WANI");
	titleNameLabel.setForeground(Color.black);
	titleNameLabel.setFont(titleFont);
	titleNamePanel.add(titleNameLabel);
	panel1.add(titleNamePanel);
	
	
	JPanel panel = new JPanel();
	JButton playbutton = new JButton("Play");
	JButton quitbutton = new JButton("Quit");
	JLabel label = new JLabel();;
	panel.setBackground(Color.WHITE);
	
	panel.add(playbutton);
	panel.add(quitbutton);
	panel.add(label);
	
	frame.add(panel1);
	frame.add(panel, BorderLayout.SOUTH);
	
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public Image getImage() {
		return image;
	}
	
	public static void main(String[] args) {
		
		new Main();
	}
}