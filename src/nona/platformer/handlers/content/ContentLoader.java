package nona.platformer.handlers.content;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.imageio.ImageIO;

import nona.platformer.graphics.sprite.Sprite;
import nona.platformer.main.Main;
import nona.platformer.tile.Tile;

public class ContentLoader {

	public static BufferedImage loadImage(String path) {		
		try {
			BufferedImage image = ImageIO.read(ContentLoader.class.getResourceAsStream(path));
			
			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// Returns an array of tiles that are loaded from an image
	public static Sprite[][] loadTileSet(String path) {
		BufferedImage image = loadImage(path);
		Sprite[][] tiles = new Sprite[image.getHeight() / Main.TILESIZE][image.getWidth() / Main.TILESIZE];
		
		// Splits the image
		for(int y = 0; y < image.getHeight() / Main.TILESIZE; y++) {
			for(int x = 0; x < image.getWidth() / Main.TILESIZE; x++) {
				tiles[y][x] = new Sprite(image.getSubimage(x * Main.TILESIZE, y * Main.TILESIZE, Main.TILESIZE, Main.TILESIZE));
			}
		}
		
		return tiles;
	}
	
	@SuppressWarnings("deprecation")
	public static int[][] loadTilemap(String path) {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
			
			int width = Integer.parseInt(in.readLine());
			int height = Integer.parseInt(in.readLine());
			
			int[][] map = new int[height][width];
			
			String line;
			String[] symbols = new String[width];
			for(int y = 0; y < height; y++) {
				line = in.readLine();
				symbols = line.split("\\s+");
				for(int x = 0; x < width; x++) {
					int type = Integer.parseInt(symbols[x]);
					map[y][x] = type;
				}
			}
			in.close();
			
			return map;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Tile[][] convertMapToTileArray(int[][] map) {
		Tile[][] tiles = new Tile[map.length][map[0].length];
		
		for(int y = 0; y < tiles.length; y++) {
			for(int x = 0; x < tiles[0].length; x++) {
				if(map[y][x] == Tile.TILE_AIR) tiles[y][x] = Content.Tile_Air.setPosition(x + 1, y + 1);
				else if(map[y][x] == Tile.TILE_FULLCOL) tiles[y][x] = Content.Tile_Solid.setPosition(x + 1, y + 1);
				else if(map[y][x] == Tile.TILE_VISUAL) tiles[y][x] = Content.Tile_Visual.setPosition(x + 1, y + 1);
			}
		}
		
		return tiles;
	}
	
}