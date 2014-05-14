package nona.platformer.main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import nona.platformer.drawable.Sprite;
import nona.platformer.tile.Tile;

public class ContentLoader {

	public static BufferedImage loadImage(String path) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(Image.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
	public Tile[][] loadTileSet(String path) {
		BufferedImage image = loadImage(path);
		Tile[][] tiles = new Tile[image.getHeight() / Main.TILESIZE][image.getWidth() / Main.TILESIZE];
		
		for(int y = 0; y < image.getHeight(); y += Main.TILESIZE) {
			for(int x = 0; x < image.getWidth(); x += Main.TILESIZE) {
				tiles[y][x] = new Tile(image.getSubimage(x, y, Main.TILESIZE, Main.TILESIZE));
			}
		}
		
		return tiles;
	}
	
}
