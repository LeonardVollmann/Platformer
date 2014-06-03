package nona.platformer.entity.character;

import nona.platformer.graphics.sprite.Sprite;
import nona.platformer.handlers.Keys;
import nona.platformer.main.Main;
import nona.platformer.tile.Tilemap;

/*
 * @Leonard Vollmann
 * 
 * The player.
 */

public class Player extends Character {
	
	public Player(int x, int y, int width, int height, Tilemap tilemap, Sprite sprite) {
		super(x, y, width, height, tilemap, sprite);
		
		jumpingSpeed = -5;
		maxVel = 5;
	}
	
	// Updates the player
	public void update() {
		super.update();
		
		handleKeys();
	
		tilemap.setPosition(Main.WIDTH / 2 - xscreen, Main.HEIGHT / 2 - yscreen);
	}
	
	// Handles input
	public void handleKeys() {
		if(Keys.isKeyPressed(Keys.A)) xvel = -2;
		else if(Keys.isKeyPressed(Keys.D)) xvel = 2;
		else xvel = 0;

		if(Keys.isKeyHit(Keys.W)) jump();
	
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
	
	// Renders the player
	public void render() {
		super.render();
	}
	
}
