package nona.platformer.tile;

import nona.platformer.graphics.Sprite;
import nona.platformer.level.Level;


public abstract class Tile {
	
	public static final int TILE_AIR = 0;
	public static final int TILE_FULLCOL = 1;
	public static final int TILE_VISUAL = 2;
	
	protected int x;
	protected int y;
	
	protected Sprite sprite;
	
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

	public int getType() {
		if(this instanceof AirTile) return TILE_AIR;
		else if(this instanceof FullCollisionTile) return TILE_FULLCOL;
		else if(this instanceof VisualTile) return TILE_VISUAL;
		else return TILE_AIR;
	}
	
}
