package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

import entities.Player;

public class GamePanel extends JPanel implements Runnable {

	// SCREEN SETTINGS
	public final int spriteWidth = 16; // 16x32px tile size
	public final int spriteHeight = 32;
	final int scale = 3; // scaling factor of 3 for correct display on modern resolutions
	public final int scaledSpriteWidth = spriteWidth * scale; // the actual size displayed, 3 times bigger
	public final int scaledSpriteHeight = spriteHeight * scale;

	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = scaledSpriteWidth * maxScreenCol;
	final int screenHeight = scaledSpriteWidth * maxScreenRow;

	// Set game FPS
	int FPS = 60;

	private KeyHandler keyHandler;
	private Player player;
	private Thread gameThread;

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.GREEN);
		this.setDoubleBuffered(true);
		keyHandler = new KeyHandler();
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
		this.requestFocusInWindow(); // request to focus on window

		player = new Player(this, keyHandler);

	}

	// the following function is useful to debug the focus owner
//	public void printFocusOwner() {
//		KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
//		Component focusOwner = focusManager.getFocusOwner();
//		if (focusOwner != null) {
//			System.out.println("Current focus owner: " + focusOwner.getClass().getName());
//		} else {
//			System.out.println("No component has focus.");
//		}
//	}

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

			// printFocusOwner();
		}

	};

	public void update() {
		player.update();
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		// this method fix the lag problem by syncing the drawing and the screen
		Toolkit.getDefaultToolkit().sync();

		// graphics2d class has more functions
		// convert the "g" graphics object to a graphics2d object.
		Graphics2D g2 = (Graphics2D) g;

		player.draw(g2);

		// free system resources when not used, save memory
		g2.dispose();
	}
}
