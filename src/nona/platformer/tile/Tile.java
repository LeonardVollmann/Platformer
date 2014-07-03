package nona.platformer.tile;

import java.util.ArrayList;

import nona.platformer.graphics.Sprite;
import nona.platformer.level.Level;


public abstract class Tile {
	
	public static final int TILE_AIR = 0;
	public static final int TILE_SOLID_1 = 1;
	public static final int TILE_SOLID_2 = 2;
	public static final int TILE_VISUAL = 3;
	
	public static final int TILE_COLLISION_FULL = 0;
	public static final int TILE_COLLISION_NONE = 1;
	
	public static final ArrayList<Integer> FullCollisionTiles = new ArrayList<Integer>();
	public static final ArrayList<Integer> NoCollisionTiles = new ArrayList<Integer>();
	
	protected int x;
	protected int y;
	
	protected Sprite sprite;
	
	public static void init() {
		FullCollisionTiles.add(TILE_SOLID_1);
		FullCollisionTiles.add(TILE_SOLID_2);
		
		NoCollisionTiles.add(TILE_AIR);
	}
	
	public void update() {
		sprite.update();
	}
	
	public void render(int x, int y, Level level) {
		sprite.render(x, y, level.getBitmap());
	}
	
	public Tile setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		
		return this;
	}
	
}
