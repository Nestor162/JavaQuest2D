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
	private BufferedImage sprite;

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		setDefaultValues();
		getPlayerImage();
	}

	public void getPlayerImage() {
		spriteSheet = new SpriteSheetLoader("/player/character-movement.png", 16, 32);
		sprite = spriteSheet.getSprite(0, 0); // get first sprite (row 0, col 0)
	}

	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 4;

	}

	public void update() {
		if (keyH.upPressed) {
			y -= speed;
		}
		if (keyH.downPressed) {
			y += speed;
		}
		if (keyH.rightPressed) {
			x += speed;
		}
		if (keyH.leftPressed) {
			x -= speed;
		}
	}

	public void draw(Graphics2D g2) {
		g2.drawImage(sprite, 50, 50, null); // TEST: draw sprite at(50, 50)
	}

}
