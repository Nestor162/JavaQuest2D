package sprites;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
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

	public BufferedImage getFlippedSprite(int col, int row) {
		BufferedImage sprite = getSprite(col, row);
		// Create a mirrored image
		AffineTransform transform = AffineTransform.getScaleInstance(-1, 1);
		transform.translate(-sprite.getWidth(), 0);

		BufferedImage flippedImage = new BufferedImage(sprite.getWidth(), sprite.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = flippedImage.createGraphics();
		g2d.drawImage(sprite, transform, null);
		g2d.dispose();

		return flippedImage;
	}
}
