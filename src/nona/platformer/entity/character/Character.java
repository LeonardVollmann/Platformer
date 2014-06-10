package nona.platformer.entity.character;

import nona.platformer.entity.Entity;
import nona.platformer.graphics.sprite.AnimatedSprite;
import nona.platformer.level.Level;
import nona.platformer.main.Game;
import nona.platformer.main.Main;
import nona.platformer.tile.Tile;

public abstract class Character extends Entity {
	

	protected float xvel;
	protected float yvel;
	
	protected float maxVel;
	protected float acceleration;
	protected float deceleration;
	
	protected float jumpingSpeed;
	
	protected boolean in_air;
	

	protected int currTileX;
	protected int currTileY;
	

	protected int xDest;
	protected int yDest;
	

	protected int xTemp;
	protected int yTemp;
	

	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;
	

	protected AnimatedSprite action_idle;
	protected AnimatedSprite action_walking;
	protected boolean facingRight = true;


	public Character(int x, int y, int width, int height, AnimatedSprite action_idle, AnimatedSprite action_walking) {
		super(x, y, width, height, action_idle);
		
		this.action_idle = action_idle;
		this.action_walking = action_walking;
	}
	
	public void update() {		
		if(in_air) 
			yvel += Game.GRAVITY;
		
		if(xvel > maxVel)
			xvel = maxVel;
		
		if(xvel > 0)
			facingRight = true;
		else if(xvel < 0)
			facingRight = false;
		
		checkTilemapCollision();
				
		sprite.update();
	}
	
	public void render() {
		super.render(level.getBitmap());
	}
	
	protected void checkTilemapCollision() {
		currTileX = Math.round((float) xscreen / Main.TILESIZE);
		currTileY = Math.round((float) yscreen / Main.TILESIZE);
		
		xDest = (int) (xscreen + xvel);
		yDest = (int) (yscreen + yvel);

		xTemp = xscreen;
		yTemp = yscreen;

		calculateCorners(xDest, yscreen);
		if(xvel < 0) {
			if(topLeft || bottomLeft) {
				xvel = 0;
				xTemp = currTileX * Main.TILESIZE;
			} else
				xTemp += xvel;
		} else if(xvel > 0) {
			if(topRight || bottomRight) {
				xvel = 0;
				xTemp = currTileX * Main.TILESIZE;
			} else
				xTemp += xvel;
		}
		
		calculateCorners(xscreen, yDest);
		if(yvel < 0) {
			if(topLeft || topRight) {
				yvel = 0;
				yTemp = currTileY * Main.TILESIZE;
			} else
				yTemp += yvel;
		} else if(yvel > 0) {
			if(bottomLeft || bottomRight) {
				yvel = 0;
				yTemp = currTileY * Main.TILESIZE;
				in_air = false;
			} else
				yTemp += yvel;
		}
		
		if(!in_air) {
			calculateCorners(xscreen, yscreen + 2);
			if(!bottomLeft && !bottomRight)
				in_air = true;
		}
		
		updateActions();
			
		xscreen = xTemp;
		yscreen = yTemp;
		
		calculateMapCoordinates();
	}
	
	protected void calculateCorners(int x, int y) {
		int left = x / Main.TILESIZE;
		int right = (x + width - 1) / Main.TILESIZE;
		int top = y / Main.TILESIZE;
		int bottom = (y + height - 1) / Main.TILESIZE;
		
		int tl = level.getTilemap().getType(left, top);
		int tr = level.getTilemap().getType(right, top);
		int bl = level.getTilemap().getType(left, bottom);
		int br = level.getTilemap().getType(right, bottom);
		
		topLeft = tl == Tile.TILE_FULLCOL;
		topRight = tr == Tile.TILE_FULLCOL;
		bottomLeft = bl == Tile.TILE_FULLCOL;
		bottomRight = br == Tile.TILE_FULLCOL;
	}

	public void jump() {
		yvel = jumpingSpeed;
	}

	public void accelerateX(float acceleration) {
		xvel += acceleration;
	}
	
	public void accelerateY(float acceleration) {
		yvel += acceleration;
	}
	
	protected void updateActions() {
		if(xvel == 0 && yvel == 0)
			sprite = action_idle;
		else if(xvel != 0 && yvel == 0)
			sprite = action_walking;
	}
	
	public void setLevel(Level level) {
		super.setLevel(level);
	}
	
}
