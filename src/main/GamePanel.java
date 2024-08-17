package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
		while (gameThread != null) {
			
			//This is the main game loop
			
			//1. UPDATE: update character position
			update();
			
			//2. DRAW: draw the screen with the updated info
			repaint();
		}
	
	};
	
	public void update() {
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		// graphics2d class has more functions
		// convert the "g" graphics object to a graphics2d object.
		Graphics2D g2= (Graphics2D)g;
		
		// draw a rectangle and fills with the specified color
		g2.setColor(Color.white);
		g2.fillRect(100, 100, tileSize, tileSize);
		//free system resources when not used, save memory
		g2.dispose();
	}
}
