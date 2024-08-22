package main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("JavaQuest2D");
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);

		window.pack();

		// Request focus to GamePanel, without this line focus owner was
		// "javax.swing.JFrame"
		// and keyHandler was not working at all.
		// after this line, the focus owner is " main.GamePanel" and controls work
		SwingUtilities.invokeLater(() -> gamePanel.requestFocusInWindow());

		gamePanel.startGameThread();
	}

}
