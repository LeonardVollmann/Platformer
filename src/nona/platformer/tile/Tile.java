package nona.platformer.tile;

import java.awt.image.BufferedImage;

import nona.platformer.drawable.Sprite;

/*
 * @Author Leonard Vollmann
 * 
 * It's just a tile
 */

public class Tile {
	
	private Sprite sprite;
		
	public Tile(BufferedImage image) {
		this.sprite = new Sprite(image);
	}
	
	public void update() {
		
	}
	
	public void render(int x, int y, Tilemap tilemap) {
		sprite.render(x, y, tilemap.getBitmap());
	}
	
}
