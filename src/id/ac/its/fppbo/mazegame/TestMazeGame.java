package id.ac.its.fppbo.mazegame;

import javax.swing.JFrame;


public class TestMazeGame {
//	public static void main (String[] args) {
//		new TestMazeGame();
//	}
	
	public TestMazeGame() {
		JFrame fr = new JFrame();
		fr.setTitle("WaniWanis");
		fr.add(new MazeGameBoard());
		fr.setSize(500,600);
		fr.setLocationRelativeTo(null);
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//agar penutupan frame menghentikan jalannya program
	}
}
