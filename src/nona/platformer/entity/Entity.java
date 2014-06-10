package nona.platformer.entity;

import nona.platformer.graphics.Bitmap;
import nona.platformer.graphics.sprite.Sprite;
import nona.platformer.level.Level;

public abstract class Entity {
	
	protected int xscreen;
	protected int yscreen;
	
	protected int xmap;
	protected int ymap;
	
	protected int width;
	protected int height;
	
	protected Level level;
	
	protected Sprite sprite;
	
	public Entity(int x, int y, int width, int height, Sprite sprite) {
		this.xmap = x;
		this.xmap = y;
		this.xscreen = x;
		this.yscreen = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}
	
	public void update() {
		sprite.update();
	}
	
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
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	protected void calculateMapCoordinates() {
		xmap = xscreen - level.getTilemapOffsetX();
		ymap = yscreen - level.getTilemapOffsetY();
	}
	
}
