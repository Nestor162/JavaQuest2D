package entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;
import sprites.SpriteSheetLoader;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;

	private SpriteSheetLoader spriteSheet;

	// create arrays to save the sprites for every direction
	private BufferedImage[] upSprites;
	private BufferedImage[] downSprites;
	private BufferedImage[] rightSprites;
	private BufferedImage[] leftSprites;

	// variables for sprite animation
	private BufferedImage currentSprite;
	private int spriteIndex;
	private int animationSpeed;
	private long lastUpdate;

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		setDefaultValues();
		initializeResources();
	}

	public void initializeResources() {
		spriteSheet = new SpriteSheetLoader("/player/character-movement.png", gp.spriteWidth, gp.spriteHeight);
		loadSprites();
		currentSprite = downSprites[0]; // Start with default direction
		spriteIndex = 0;
		animationSpeed = 100; // Adjust as needed (in milliseconds)
		lastUpdate = System.currentTimeMillis();
	}

	private void loadSprites() {
		// Load all sprite frames from spriteSheet
		downSprites = loadDirectionSprites(0); // Load sprites in the row 0
		upSprites = loadDirectionSprites(2); // Load sprites in the row 1
		rightSprites = loadDirectionSprites(1); // Load sprites in the row 2
		leftSprites = loadDirectionSprites(1); // Load sprites in the row 2
	}

	private BufferedImage[] loadDirectionSprites(int row) {
		BufferedImage[] sprites = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			sprites[i] = spriteSheet.getSprite(i, row);
		}
		return sprites;
	}

	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 4;
	}

	public void update() {
		if (keyH.upPressed) {
			currentSprite = upSprites[spriteIndex];
			y -= speed;
			updateAnimation();
		}
		if (keyH.downPressed) {
			currentSprite = downSprites[spriteIndex];
			y += speed;
			updateAnimation();
		}
		if (keyH.rightPressed) {
			currentSprite = rightSprites[spriteIndex];
			x += speed;
			updateAnimation();
		}
		if (keyH.leftPressed) {
			currentSprite = leftSprites[spriteIndex];
			x -= speed;
			updateAnimation();
		}
	}

	private void updateAnimation() {
		long now = System.currentTimeMillis();
		if (now - lastUpdate > animationSpeed) {
			spriteIndex = (spriteIndex + 1) % 4; // Loop through 4 frames
			lastUpdate = now;
		}
	}

	public void draw(Graphics2D g2) {
		if (currentSprite != null) {
			g2.drawImage(currentSprite, x, y, gp.scaledSpriteWidth, gp.scaledSpriteHeight, null);
			// Dibuja un borde alrededor del sprite
			g2.setColor(java.awt.Color.RED); // Color del borde
			g2.drawRect(x, y, gp.scaledSpriteWidth, gp.scaledSpriteHeight); // Dibuja el borde
		} else {
			System.err.println("Sprite is not loaded.");
		}
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize); // For debugging

	}

}
