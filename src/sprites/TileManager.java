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
		map = new int[gp.maxScreenCol][gp.maxScreenRow];

		getTileImage();
		loadMap("/maps/map01.txt");
	}

	public void getTileImage() {

		try {
			tile[0] = new Tile(); // grass (tile 0)
			tile[0].image = ImageIO.read(getClass().getResource("/world/overworld-single/tile000.png"));

			tile[1] = new Tile(); // water (tile 1)
			tile[1].image = ImageIO.read(getClass().getResource("/world/overworld-single/tile283.png"));

			tile[2] = new Tile(); // stone (tile 2)
			tile[2].image = ImageIO.read(getClass().getResource("/world/overworld-single/tile496.png"));

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

			while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

				String line = bReader.readLine();

				while (col < gp.maxScreenCol) {

					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					map[col][row] = num;
					col++;
				}
				if (col == gp.maxScreenCol) {
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

		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;

		while (row < gp.maxScreenRow && col < gp.maxScreenCol) {

			int tileNum = map[col][row];
			g2.drawImage(tile[tileNum].image, x, y, tileSize, tileSize, null);
			col++;
			x += tileSize;

			if (col == gp.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += tileSize;
			}

		}

	}
}
