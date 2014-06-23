package nona.platformer.entity.character;

import nona.platformer.entity.Entity;
import nona.platformer.graphics.Sprite;
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
	protected boolean on_ground;
	
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

	protected Sprite action_idle;
	protected Sprite action_walking;
	protected Sprite action_jumping;
	protected Sprite action_falling;
	protected Sprite action_walking_flipped;
	protected Sprite action_jumping_flipped;
	protected Sprite action_falling_flipped;
	protected boolean facingRight = true;


	public Character(int x, int y, int width, int height, Sprite action_idle, Sprite action_walking, Sprite action_jumping, Sprite action_falling) {
		super(x, y, width, height, action_idle);
		
		this.action_idle = action_idle;
		this.action_walking = action_walking;
		this.action_jumping = action_jumping;
		this.action_falling = action_falling;
		this.action_walking_flipped = this.action_walking.getFlipped();
		this.action_jumping_flipped = this.action_jumping.getFlipped();
		this.action_falling_flipped = this.action_falling.getFlipped();
	}
	
	public void update() {		
		accelerateY(Game.GRAVITY);
		
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
		currTileX = Math.round((float) (xscreen - width / 2) / Main.TILESIZE);
		currTileY = Math.round((float) (yscreen - height / 2) / Main.TILESIZE);
		
		xDest = Math.round((float) xscreen + xvel);
		yDest = Math.round((float) yscreen + yvel);

		xTemp = xscreen;
		yTemp = yscreen;

		calculateCorners(xDest, yscreen);
		if(xvel < 0) {
			if(topLeft || bottomLeft) {
				xvel = 0;
				xTemp = currTileX * Main.TILESIZE + width / 2;
			} else
				xTemp += xvel;
		} else if(xvel > 0) {
			if(topRight || bottomRight) {
				xvel = 0;
				xTemp = currTileX * Main.TILESIZE + width / 2;
			} else
				xTemp += xvel;
		}
		
		calculateCorners(xscreen, yDest);
		if(yvel < 0) {
			if(topLeft || topRight) {
				yvel = 0;
				yTemp = currTileY * Main.TILESIZE + height / 2;
			} else
				yTemp += yvel;
		} else if(yvel > 0) {
			if(bottomLeft || bottomRight) {
				yvel = 0;
				yTemp = currTileY * Main.TILESIZE + height / 2;
				in_air = false;
				on_ground = true;
			} else
				yTemp += yvel;
		}
		
		if(!in_air) {
			calculateCorners(xTemp, yTemp + 1);
			if(!bottomLeft && !bottomRight) {
				on_ground = false;
				in_air = true;
			}
		}
		
		updateActions();
			
		xscreen = xTemp;
		yscreen = yTemp;
		
		calculateMapCoordinates();
	}
	
	protected void calculateCorners(int x, int y) {
		int left = (x - width / 2) / Main.TILESIZE;
		int right = ((x + width / 2) - 1) / Main.TILESIZE;
		int top = (y - height / 2) / Main.TILESIZE;
		int bottom = ((y + height / 2) - 1) / Main.TILESIZE;
		
		int tl = level.getType(left, top);
		int tr = level.getType(right, top);
		int bl = level.getType(left, bottom);
		int br = level.getType(right, bottom);
		
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
		if(on_ground && xvel == 0 && yvel == 0)
			sprite = action_idle;
		else if(on_ground && xvel > 0 && yvel == 0)
			sprite = action_walking;
		else if(on_ground && xvel < 0 && yvel == 0)
			sprite = action_walking_flipped;
		else if(in_air && yvel < 0 && xvel > 0)
			sprite = action_jumping;
		else if(in_air && yvel < 0 && xvel < 0)
			sprite = action_jumping_flipped;
		else if(in_air && yvel > 0 && xvel > 0)
			sprite = action_falling;
		else if(in_air && yvel < 0 && xvel < 0)
			sprite = action_falling_flipped;
		else if(in_air && xvel == 0)
			sprite = action_idle;

	}
	
	public void setLevel(Level level) {
		super.setLevel(level);
	}
	
}
