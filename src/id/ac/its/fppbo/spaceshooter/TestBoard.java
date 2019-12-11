package id.ac.its.fppbo.spaceshooter;

import java.awt.EventQueue;

import javax.swing.JFrame;

import id.ac.its.fppbo.mazegame.MazeGameBoard;

public class TestBoard extends JFrame {
	
	public TestBoard() {
		initUI();
	}
	
	private void initUI() {
		add(new PapanShooter());

//		add(new MazeGameBoard());
		
		setTitle("Test Board");
		setSize(500,600);
		
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			TestBoard tb = new TestBoard();
			tb.setVisible(true);
		});
		System.out.println("lele");
	}
	
}
