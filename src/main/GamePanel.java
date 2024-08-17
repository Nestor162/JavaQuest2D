package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	// SCREEN SETTINGS
	final int originalTileSize = 16; //16x16px tile size
	final int scale = 3; //scaling factor of 3 for correct display on modern resolutions
	final int tileSize = originalTileSize * scale; //the actual size displayed, 3 times bigger than original size (48x48px)
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; //718px
	final int screenHeight = tileSize * maxScreenRow; //576px
	
	Thread gameThread;

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
	};
}
