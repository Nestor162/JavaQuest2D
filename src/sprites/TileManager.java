package sprites;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	Tile[] tile;
	int tileSize = 16 * 3; // tile size * scale factor (3)

	public TileManager(GamePanel gp) {
		this.gp = gp;

		this.tile = new Tile[10];
		getTileImage();
	}

	public void getTileImage() {

		try {
			tile[0] = new Tile();
			tile[1] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResource("/world/overworld-single/tile000.png"));
			tile[1].image = ImageIO.read(getClass().getResource("/world/overworld-single/tile000.png"));

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void draw(Graphics2D g2) {
		g2.drawImage(tile[1].image, 0, 0, tileSize, tileSize, null);

	}
}
