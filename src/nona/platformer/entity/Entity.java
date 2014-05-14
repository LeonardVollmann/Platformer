package nona.platformer.entity;

import nona.platformer.drawable.Bitmap;
import nona.platformer.drawable.Sprite;

/*
 * @Author Leonard Vollmann
 * 
 * This is the superclass for all Entities (Who would've guessed that?)
 */

public class Entity {
	
	private int x;
	private int y;
	
	private int width;
	private int height;
	
	private Sprite sprite;
	
	public Entity(int x, int y, int width, int height, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}
	
	public void update() {
		
	}
	
	public void render(Bitmap target) {
		sprite.render(x, y, target);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidt() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
}
