package nona.platformer.entity.character;

import nona.platformer.graphics.sprite.AnimatedSprite;
import nona.platformer.handlers.Keys;
import nona.platformer.handlers.content.Content;
import nona.platformer.main.Main;
import nona.platformer.tile.Tilemap;

/*
 * @Leonard Vollmann
 * 
 * The player.
 */

public class Player extends Character {
	
	public static final AnimatedSprite ACTION_IDLE = Content.Sprite_Player_Idle;
	public static final AnimatedSprite ACTION_WALKING = Content.Sprite_Player_Walking;
	public static final AnimatedSprite ACTION_INAIR = Content.Sprite_Player_InAir;
	
	public Player(int x, int y, int width, int height, Tilemap tilemap) {
		super(x, y, width, height, tilemap, ACTION_IDLE);
				
		jumpingSpeed = -5;
		maxVel = 5;
	}
	
	// Updates the player
	public void update() {
		super.update();
		
		handleKeys();
		
		updateActions();
		
		tilemap.setPosition(Main.WIDTH / 2 - xscreen, Main.HEIGHT / 2 - yscreen);
	}
	
	// Handles input
	private void handleKeys() {
		if(Keys.isKeyPressed(Keys.A) || Keys.isKeyPressed(Keys.LEFT)) xvel = -2;
		else if(Keys.isKeyPressed(Keys.D) || Keys.isKeyPressed(Keys.RIGHT)) xvel = 2;
		else xvel = 0;

		if(Keys.isKeyHit(Keys.W) || Keys.isKeyHit(Keys.UP)) jump();
	
		if(Keys.isKeyHit(Keys.SPACE)) {
			System.out.println("---------------------------");
			System.out.println("Screen coordinates: " + xscreen + ", " + yscreen);
			System.out.println("Map coordinates:    " + xmap + ", " + ymap);
			System.out.println("Tilemap offsets:    " + tilemap.getXOffset() + ", " + tilemap.getYOffset());
			System.out.println("Current Tiles:      " + currTileX + ", " + currTileY);
			System.out.println("Velocities:         " + xvel + ", " + yvel);
			System.out.println("---------------------------");
		}
	}
	
	private void updateActions() {
		if(xvel == 0 && yvel == 0 && !in_air) sprite = ACTION_IDLE;
		else if(in_air) sprite = ACTION_INAIR;
		else if(xvel != 0 && yvel == 0 && facingRight) sprite = ACTION_WALKING;
		else if(xvel != 0 && yvel == 0 && !facingRight) sprite = ACTION_WALKING;
	}
	
	// Renders the player
	public void render() {
		super.render();
	}
	
}
