package nona.platformer.tile;

import nona.platformer.graphics.sprite.Sprite;

/*
 * @Author Leonard Vollmann
 * 
 * Superclass of all tiles
 */

public abstract class Tile {
	
	// Tile type constants
	public static final int TILE_AIR = 0;
	public static final int TILE_FULLCOL = 1;
	public static final int TILE_VISUAL = 2;
	
	// Tile-precise coordinates of the Tile on the Tilemap
	protected int x;
	protected int y;
	
	// The image
	protected Sprite sprite;
	
	// Updates the sprite
	public void update() {
		sprite.update();
	}
	
	public void render(int x, int y, Tilemap tilemap) {
		sprite.render(x, y, tilemap.getBitmap());
	}
	
	// Sets x and y
	public Tile setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		
		return this;
	}

	// Returns type of this
	public int getType() {
		if(this instanceof AirTile) return TILE_AIR;
		else if(this instanceof FullCollisionTile) return TILE_FULLCOL;
		else if(this instanceof VisualTile) return TILE_VISUAL;
		else return TILE_AIR;
	}
	
}
