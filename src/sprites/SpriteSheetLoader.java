package sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheetLoader {
	private BufferedImage spriteSheet;
	private int tileWidth, tileHeight;

	public SpriteSheetLoader(String path, int tileWidth, int tileHeight) {
		try {
			spriteSheet = ImageIO.read(getClass().getResource(path));
			this.tileWidth = tileWidth;
			this.tileHeight = tileHeight;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage getSprite(int col, int row) {
		return spriteSheet.getSubimage(col * tileWidth, row * tileHeight, tileWidth, tileHeight);
	}
}
