package nona.platformer.entity.character;

import nona.platformer.entity.Entity;
import nona.platformer.graphics.sprite.AnimatedSprite;
import nona.platformer.graphics.sprite.Sprite;
import nona.platformer.main.Game;
import nona.platformer.main.Main;
import nona.platformer.tile.Tile;
import nona.platformer.tile.Tilemap;

/*
 * @Author Leonard Vollmann
 * 
 * Superclass for mobs and the player
 */

public abstract class Character extends Entity {
	
	// Movement
	protected float xvel;
	protected float yvel;
	
	protected float maxVel;
	protected float acceleration;
	protected float deceleration;
	
	protected float jumpingSpeed;
	
	protected boolean in_air;
	
	// Collision
	protected int currTileX;
	protected int currTileY;
	
	// Destination of player
	protected int xDest;
	protected int yDest;
	
	// Temporary values equal to x and y to preserve the original coordinates
	protected int xTemp;
	protected int yTemp;
	
	// Booleans saying whether or not the four corners of the collision rectangle are in solid tiles
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;
	
	// Animation
	protected AnimatedSprite[] actions;
	protected boolean facingRight = true;

	// Constructor
	public Character(int x, int y, int width, int height, Tilemap tilemap, Sprite sprite) {
		super(x, y, width, height, tilemap, sprite);
		
		actions = new AnimatedSprite[] {(AnimatedSprite) sprite};
	}
	
	public Character(int x, int y, int width, int height, Tilemap tilemap, Sprite[] sprites) {
		super(x, y, width, height, tilemap, sprites[0]);
		
		actions = (AnimatedSprite[]) sprites;
	}
	
	// Updates the Character
	public void update() {		
		// Applies Gravity
		if(in_air) 
			yvel += Game.GRAVITY;
		
		// Horizontal speed is never bigger than maxVel
		if(xvel > maxVel)
			xvel = maxVel;
		
		if(xvel >= 0)
			facingRight = true;
		else
			facingRight = false;
		
		checkTilemapCollision();
		
		sprite.update();
	}
	
	public void render() {
		super.render(tilemap.getBitmap());
	}
	
	// Checks for collisions with tiles
	protected void checkTilemapCollision() {
		// Tile-perfect coordinates to keep track in what tile the players position (In this case the top left corner) is at the moment
		currTileX = Math.round((float) xscreen / Main.TILESIZE); // Had to do it that way because of how integer division works
		currTileY = Math.round((float) yscreen / Main.TILESIZE); // --> It doesn't round correctly, but takes away the decimal places
		
		// Sets the destination position. Their purpose is described above
		xDest = (int) (xscreen + xvel);
		yDest = (int) (yscreen + yvel);

		// Sets the temporary position. Their purpose is described above
		xTemp = xscreen;
		yTemp = yscreen;

		// Collision detection in x-direction
		calculateCorners(xDest, yscreen);
		if(xvel < 0) { // If moving left
			if(topLeft || bottomLeft) { // If one of the left corners would be in a solid Tile
				xvel = 0; // Stops the Character
				xTemp = currTileX * Main.TILESIZE; // Sets the Character's position to the current Tile
			} else // If none of the left corners would have collided
				xTemp += xvel; // Moves the Character
		} else if(xvel > 0) { // If moving right
			if(topRight || bottomRight) { // If one of the right corners would be in a solid Tile
				xvel = 0; // Stops the Character
				xTemp = currTileX * Main.TILESIZE; // Sets the Character's position to the current Tile
			} else // If none of the right corners would have collided
				xTemp += xvel; // Moves the Character
		}
		
		// Collision detection in y-direction
		calculateCorners(xscreen, yDest); // Sets corner-booleans representing the movement along the x-axis
		if(yvel < 0) { // If moving upwards
			if(topLeft || topRight) { // If one of the top corners would be in a solid Tile
				yvel = 0; // Stops the Character
				yTemp = currTileY * Main.TILESIZE; // Sets the Character's Position to the current Tile
			} else // If none of the top corners would have collided
				yTemp += yvel; // Moves the Character
		} else if(yvel > 0) { // If moving downwards
			if(bottomLeft || bottomRight) { // If one of the bottom corners would be in a solid Tile
				yvel = 0; // Stops the Character
				yTemp = currTileY * Main.TILESIZE; // Sets the Character's position to the current Tile
				in_air = false; // If the player's bottom side collides with something, he's obviously not in the air anymore
			} else // If none of the bottom corners would have collided
				yTemp += yvel; // Moves the Character
		}
		
		// Sets falling to true if the Character isn't falling but none of its bottom corners collide with anything
		if(!in_air) {
			calculateCorners(xscreen, yscreen + 1);
			if(!bottomLeft && !bottomRight) {
				in_air = true;
			}
		}
			
		// Sets the actual coordinates equal to the temp ones, that have been manipulated the whole time
		xscreen = xTemp;
		yscreen = yTemp;
		
		// Recalculates the correct value of the Character's coordinates on the map
		calculateMapCoordinates();
	}
	
	// Sets the corner booleans according to passed in x and y values 
	protected void calculateCorners(int x, int y) {
		// In this case, x and y represent the Character's top left corner, not his center
		int left = x / Main.TILESIZE;
		int right = (x + width - 1) / Main.TILESIZE;
		int top = y / Main.TILESIZE;
		int bottom = (y + height - 1) / Main.TILESIZE;
		
		// Gets the tiletypes for the tiles that the corners are in
		int tl = tilemap.getType(left, top);
		int tr = tilemap.getType(right, top);
		int bl = tilemap.getType(left, bottom);
		int br = tilemap.getType(right, bottom);
		
		// Determines wheter or not the tiles that the corners are in are solid or not
		topLeft = tl == Tile.TILE_FULLCOL;
		topRight = tr == Tile.TILE_FULLCOL;
		bottomLeft = bl == Tile.TILE_FULLCOL;
		bottomRight = br == Tile.TILE_FULLCOL;
	}

	// Makes the Character jump
	public void jump() {
		yvel = jumpingSpeed;
	}

	// Accelerates the player in x-direction
	public void accelerateX(float acceleration) {
		xvel += acceleration;
	}
	
	// Accelerates the player in y-direction
	public void accelerateY(float acceleration) {
		yvel += acceleration;
	}
	
	protected void setAction(int action) {
		sprite = actions[action];
		((AnimatedSprite) sprite).reset();
	}
	
}
