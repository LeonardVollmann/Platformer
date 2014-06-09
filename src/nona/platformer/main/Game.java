package nona.platformer.main;

import nona.platformer.entity.character.Player;
import nona.platformer.graphics.Bitmap;
import nona.platformer.handlers.content.Content;
import nona.platformer.tile.Tilemap;

/*
 * @Author Leonard Vollmann
 *
 * Updates all the Entities, renders scene, etc.
 */

public class Game {
	
	// Game Constants
	public static final float GRAVITY = 0.2f;
	
	// Rendering
	private Bitmap screen; // Is rendered directly to the main image
	
	// Temp
	private Tilemap tilemap;
	private Player player;

	public Game() {		
		screen = new Bitmap(new int[360 * 240], 360);

		tilemap = Content.Map;
		
		player = new Player(Main.TILESIZE, Main.TILESIZE * 4, Main.TILESIZE, Main.TILESIZE, tilemap);
	}

	// Updates the game
	public void update() {
		player.accelerateY(GRAVITY);
		player.update();
		tilemap.update();
	}

	// Renders the game
	public void render(int[] raster) {
		player.render(tilemap.getBitmap());
		tilemap.render(screen.getPixels(), screen.getWidth());
		screen.render(0, 0, raster, Main.WIDTH);
	}

	public Bitmap getScreen() {
		return screen;
	}

}
