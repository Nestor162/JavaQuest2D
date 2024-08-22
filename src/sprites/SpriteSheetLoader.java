package sprites;

import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
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
		try {
			return spriteSheet.getSubimage(col * tileWidth, row * tileHeight, tileWidth, tileHeight);
		} catch (RasterFormatException e) {
			e.printStackTrace();
			return null;
		}
	}
}
