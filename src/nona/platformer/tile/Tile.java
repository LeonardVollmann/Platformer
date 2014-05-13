package nona.platformer.tile;

import nona.platformer.drawable.Sprite;

/*
 * @Author Leonard Vollmann
 * 
 * It's just a tile
 */

public class Tile {
	
	private Sprite sprite;
		
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void update() {
		
	}
	
	public void render(int x, int y, Tilemap tilemap) {
		sprite.render(x, y, tilemap.getBitmap());
	}
	
}
