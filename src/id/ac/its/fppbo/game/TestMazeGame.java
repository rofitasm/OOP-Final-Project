package id.ac.its.fppbo.game;

import javax.swing.JFrame;


public class TestMazeGame {
	public static void main (String[] args) {
		new TestMazeGame();
	}
	
	public TestMazeGame() {
		JFrame fr = new JFrame();
		fr.setTitle("NamaGame");
		fr.add(new MazeGameBoard());
		fr.setSize(500,600);
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//agar penutupan frame menghentikan jalannya program
	}
}
