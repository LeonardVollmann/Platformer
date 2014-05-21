package nona.platformer.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.imageio.ImageIO;

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
	public static Tile[][] loadTileSet(String path) {
		BufferedImage image = loadImage(path);
		Tile[][] tiles = new Tile[image.getHeight() / Main.TILESIZE][image.getWidth() / Main.TILESIZE];
		
		// Splits the image
		for(int y = 0; y < image.getHeight() / Main.TILESIZE; y++) {
			for(int x = 0; x < image.getWidth() / Main.TILESIZE; x++) {
				tiles[y][x] = new Tile(image.getSubimage(x * Main.TILESIZE, y * Main.TILESIZE, Main.TILESIZE, Main.TILESIZE));
			}
		}
		
		return tiles;
	}
	
	@SuppressWarnings("deprecation")
	public static int[][] loadTilemap(String path) {
		try {
			File file = new File(path);
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			
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
				tiles[y][x] = Tile.getInstance(map[y][x]);
			}
		}
		
		return tiles;
	}
	
}
