package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16px tile size
	final int scale = 3; // scaling factor of 3 for correct display on modern resolutions
	final int tileSize = originalTileSize * scale; // the actual size displayed, 3 times bigger than original size
													// (48x48px)
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; // 718px
	final int screenHeight = tileSize * maxScreenRow; // 576px

	// Set game FPS
	int FPS = 60;

	KeyHandler keyHandler = new KeyHandler();

	Thread gameThread;

	// set player default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		// 1 second in nanoseconds (1000000000) / 60FPS
		// 1 frame in 0.0166666667 seconds, so in 1 second we will get 60 frames
		double drawInterval = 1000000000 / FPS;

		double delta = 0; // indicates how much time has passed in term of frames
		long lastTime = System.nanoTime(); // current time at the start of the game
		long currentTime; // track the time within the loop

		while (gameThread != null) {

			// This is the main game loop

			// Get the current time in nanoseconds
			currentTime = System.nanoTime();

			// the first subtraction indicates the time that have passed,
			// we divide this value by the time of every frame (in this case 0.0166666667
			// seconds)
			delta += (currentTime - lastTime) / drawInterval;

			// update the time variable
			lastTime = currentTime;

			// if delta is equal or greater to 1 it means that has passed the time indicated
			// for the frame, so we update, draw and start over
			if (delta >= 1) {
				// 1. UPDATE: update character position
				update();

				// 2. DRAW: draw the screen with the updated info
				repaint();

				delta--;
			}

		}

	};

	public void update() {
		if (keyHandler.upPressed) {
			playerY -= playerSpeed;
		}
		if (keyHandler.downPressed) {
			playerY += playerSpeed;
		}
		if (keyHandler.rightPressed) {
			playerX += playerSpeed;
		}
		if (keyHandler.leftPressed) {
			playerX -= playerSpeed;
		}
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		// this method fix the lag problem by syncing the drawing and the screen
		Toolkit.getDefaultToolkit().sync();

		// graphics2d class has more functions
		// convert the "g" graphics object to a graphics2d object.
		Graphics2D g2 = (Graphics2D) g;

		// draw a rectangle and fills with the specified color
		g2.setColor(Color.white);
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		// free system resources when not used, save memory
		g2.dispose();
	}
}
