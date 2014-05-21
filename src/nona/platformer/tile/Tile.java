package nona.platformer.tile;

import java.awt.image.BufferedImage;

import nona.platformer.drawable.Sprite;
import nona.platformer.main.Content;

/*
 * @Author Leonard Vollmann
 * 
 * It's just a tile
 */

public class Tile {
	
	// Tile types
	public static final int TILE_AIR = 0;
	public static final int TILE_SOLID = 1;
	public static final int TILE_VISUAL = 2;
	
	private Sprite sprite;
	
	public Tile(int[] pixels, int width) {
		this.sprite = new Sprite(pixels, width);
	}
	
	public Tile(BufferedImage image) {
		this.sprite = new Sprite(image);
	}
	
	public void update() {
		
	}
	
	public void render(int x, int y, Tilemap tilemap) {
		sprite.render(x, y, tilemap.getBitmap());
	}
	
	public static Tile getInstance(int type) {
		if(type == TILE_AIR) return Content.Tile_Air;
		else if(type == TILE_SOLID) return Content.Tile_Solid;
		else if(type == TILE_VISUAL) return Content.Tile_Visual;
		else return null;
	}
	
}
