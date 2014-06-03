package nona.platformer.entity;

import nona.platformer.graphics.Bitmap;
import nona.platformer.graphics.sprite.Sprite;
import nona.platformer.tile.Tilemap;

/*
 * @Author Leonard Vollmann
 * 
 * This is the superclass for all Entities (Who would've guessed that?)
 */

public abstract class Entity {
	
	// Global position: Position on screen
	protected int xscreen;
	protected int yscreen;
	
	// Position on map, also pixel-precise
	protected int xmap;
	protected int ymap;
	
	// Width, height of collision reactangle
	protected int width;
	protected int height;
	
	// Tilemap, may be replaced with protected Level level in the future
	protected Tilemap tilemap;
	
	// The image
	protected Sprite sprite;
	
	public Entity(int x, int y, int width, int height, Tilemap tilemap, Sprite sprite) {
		this.xmap = x;
		this.xmap = y;
		this.width = width;
		this.height = height;
		this.tilemap = tilemap;
		this.sprite = sprite;
		this.xscreen = x + tilemap.getXOffset();
		this.yscreen = y + tilemap.getYOffset();
	}
	
	// Updates sprite and recalculates map-coordinates
	public void update() {
		sprite.update();
	}
	
	// Renders 
	public void render(Bitmap target) {
		sprite.render(xscreen, yscreen, target);
	}
	
	public int getMapX() {
		return xmap;
	}
	
	public int getMapY() {
		return ymap;
	}
	
	public int getScreenX() {
		return xscreen;
	}
	
	public int getScreenY() {
		return yscreen;
	}
	
	public int getWidt() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	protected void calculateMapCoordinates() {
		xmap = xscreen - tilemap.getXOffset();
		ymap = yscreen - tilemap.getYOffset();
	}
	
}
