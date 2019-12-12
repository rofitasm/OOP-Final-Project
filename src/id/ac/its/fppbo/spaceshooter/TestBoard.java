package id.ac.its.fppbo.spaceshooter;

import java.awt.EventQueue;

import javax.swing.JFrame;

import id.ac.its.fppbo.mazegame.MazeGameBoard;

public class TestBoard extends JFrame {
	
	public TestBoard(int bullet, int health) {
		initUI(bullet, health);
	}
	
	private void initUI(int bullet, int health) {
		add(new PapanShooter(bullet, health));

		setTitle("Test Board");
		setSize(500,600);
		
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
