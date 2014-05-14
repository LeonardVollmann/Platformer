package nona.platformer.main;

import nona.platformer.drawable.Bitmap;
import nona.platformer.tile.Tile;
import nona.platformer.tile.Tilemap;

/*
 * @Author Leonard Vollmann
 *
 * Updates all the Entities, renders scene, etc.
 */

public class Game {
  
	// Rendering
	private Bitmap screen; // Is rendered directly to the main image

	// Temp
	private Tilemap tilemap;

	public Game() {
		screen = new Bitmap(new int[360 * 240], 360);

		tilemap = new Tilemap();

		int tileMapWidth = 25;
		int tileMapHeight = 25;

		Tile tile = new Tile(ContentLoader.loadImage("/testTile.png"));
		Tile[][] tiles = new Tile[tileMapHeight][tileMapWidth];
		for(int i = 0; i < tileMapHeight; i++) {
			for(int j = 0; j < tileMapWidth; j++) {
				tiles[i][j] = tile;
			}
		}
		tilemap.setMap(tiles);
	}

	// Updates the game
	public void update() {
		tilemap.update();
		
		if(Keys.isKeyPressed(Keys.W)) tilemap.move(0, 1);
		if(Keys.isKeyPressed(Keys.A)) tilemap.move(1, 0);
		if(Keys.isKeyPressed(Keys.S)) tilemap.move(0, -1);
		if(Keys.isKeyPressed(Keys.D)) tilemap.move(-1, 0);
	}

	// Renders the game
	public void render() {
		tilemap.render(screen.getPixels(), screen.getWidth());
		screen.render(0, 0, Main.getRaster(), Main.WIDTH);
	}

	public Bitmap getScreen() {
		return screen;
	}

}
