package id.ac.its.fppbo.game;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class TestBoard extends JFrame {
	
	public TestBoard() {
		initUI();
	}
	
	private void initUI() {
		add(new PapanShooter());
		
		setTitle("Test Board");
		setSize(500,700);
		
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			TestBoard tb = new TestBoard();
			tb.setVisible(true);
		});
	}
	
}
