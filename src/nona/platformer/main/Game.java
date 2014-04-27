package nona.platformer.main;

import nona.platformer.drawable.Bitmap;

/*
 * @Author Leonard Vollmann
 * 
 * Updates all the Entities, renders whole scene, etc.
 */

public class Game {
	
	// Rendering
	private Bitmap screen; // Is be rendered directly to the main image
	
	// Updates the game
	public void update() {
		
	}
	
	// Renders the game
	public void render() {
		screen.render(0, 0, Main.getRaster());
	}
	
}
