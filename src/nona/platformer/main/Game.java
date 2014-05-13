package nona.platformer.main;

import java.util.Random;

import nona.platformer.drawable.Bitmap;
import nona.platformer.drawable.Sprite;
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
	private Random random = new Random();
	
	public Game() {
		screen = new Bitmap(new int[360 * 240], 360);
		
		tilemap = new Tilemap();
				
		int tileMapWidth = 11;
		int tileMapHeight = 8;
		
		Tile[][] tiles = new Tile[tileMapHeight][tileMapWidth];
		for(int i = 0; i < tileMapHeight; i++) {
			for(int j = 0; j < tileMapWidth; j++) {
				tiles[i][j] = new Tile(new Sprite(Image.readImage("/testTile.png")));
			}
		}
		tilemap.setMap(tiles);
	}
	
	// Updates the game
	public void update() {
		tilemap.update();
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
