package sprites;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	Tile[] tile;
	int map[][];
	int tileSize = 16 * 3; // tile size * scale factor (3)

	public TileManager(GamePanel gp) {
		this.gp = gp;

		this.tile = new Tile[10];
		map = new int[gp.maxWorldCol][gp.maxWorldRow];

		getTileImage();
		loadMap("/maps/map02.txt");
	}

	public void getTileImage() {

		try {
			tile[0] = new Tile(); // grass (tile 0)
			tile[0].image = ImageIO.read(getClass().getResource("/world/overworld-single/tile000.png"));

			tile[1] = new Tile(); // water (tile 1)
			tile[1].image = ImageIO.read(getClass().getResource("/world/overworld-single/tile1442.png"));

			tile[2] = new Tile(); // stone (tile 2)
			tile[2].image = ImageIO.read(getClass().getResource("/world/overworld-single/tile496.png"));

			tile[3] = new Tile(); // dirt (tile 3)
			tile[3].image = ImageIO.read(getClass().getResource("/world/overworld-single/tile1282.png"));

			tile[4] = new Tile(); // sign (tile 4)
			tile[4].image = ImageIO.read(getClass().getResource("/world/overworld-single/tile1440.png"));

			tile[5] = new Tile(); // bush (tile 5)
			tile[5].image = ImageIO.read(getClass().getResource("/world/overworld-single/tile1441.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadMap(String filePath) {
		try {
			InputStream iStream = getClass().getResourceAsStream(filePath);
			BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream));

			int col = 0;
			int row = 0;

			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

				String line = bReader.readLine();

				while (col < gp.maxWorldCol) {

					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					map[col][row] = num;
					col++;
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			bReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void draw(Graphics2D g2) {

		int worldCol = 0;
		int worldRow = 0;

		while (worldRow < gp.maxWorldRow && worldCol < gp.maxWorldCol) {

			int tileNum = map[worldCol][worldRow];

			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;

			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
					&& worldX - +gp.tileSize < gp.player.worldX + gp.player.screenX
					&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
					&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, tileSize, tileSize, null);
			}
			worldCol++;

			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;

			}

		}

	}
}
