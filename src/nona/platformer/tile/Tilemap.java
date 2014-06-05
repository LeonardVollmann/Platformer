package nona.platformer.tile;

import nona.platformer.graphics.Bitmap;
import nona.platformer.handlers.content.ContentLoader;
import nona.platformer.main.Main;

/*
 * @Author Leonard Vollmann
 * 
 * Updates the Tiles and renders them to the correct position on the screen
 */

public class Tilemap {
	
	//The image
	private Bitmap bitmap;
	
	// Makes the Tilemap ease into a new position, all twitchy at the moment
	private double tween;
	
	// Tilemap
	private Tile[][] tiles;
	private int[][] map;

	// Determines which part of the Tilemap is rendered
	private float xOffset = 0;
	private float yOffset = 0;
	
	// Width, height of Tilemap
	private int width;
	private int height;
	
	// Empty constructor; setMap() is used instead
	public Tilemap() {
	}
	
	public Tilemap(String path) {
		setMap(path);
	}
	
	public Tilemap(int[][] map) {
		setMap(map);
		
		tween = 1f;
	}
	
	// Updates the Tilemap
	public void update() {
		bitmap.setColor(0);
		
		for(int y = 0; y < tiles.length; y++) {
			for(int x = 0; x < tiles[0].length; x++) {
				tiles[y][x].update();
			}
		}	
		
		// Sets the correct values to start the iteration through the tile array
		int iteratorX = (int) (Math.abs(xOffset / Main.TILESIZE));
		int iteratorY = (int) (Math.abs(yOffset / Main.TILESIZE));
				
		for(int y = iteratorY; y < iteratorY + Main.HEIGHT / Main.TILESIZE + 2 && y < tiles.length; y++) { // Only accesses tiles that should be visible; Adds one to prevent gaps
			for(int x = iteratorX; x < iteratorX + Main.WIDTH / Main.TILESIZE + 2 && x < tiles[0].length; x++) { // Tilemap cannot be smaller than width = Main.WIDTH / Main.TILESIZE, height = Main.HEIGHT / Main.TILESIZE
				if(tiles[y][x].getType() != Tile.TILE_AIR) tiles[y][x].render(x * Main.TILESIZE, y * Main.TILESIZE, this); // Doesn't render Air Tiles
			}
		}
	}
	
	// Renders the Tilemap
	public void render(int[] targetPixels, int targetImageWidth) {
		bitmap.render((int) xOffset, (int) yOffset, targetPixels, targetImageWidth);
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	
	// Note: only render or update a tilemap if either this function or the constructor taking in the parameter Tile[][] tiles have been called
	public void setMap(String path) {
		this.map = ContentLoader.loadTilemap(path);
		
		this.tiles = ContentLoader.convertMapToTileArray(map);
		
		this.width = tiles[0].length * Main.TILESIZE;
		this.height = tiles.length * Main.TILESIZE;

		this.bitmap = new Bitmap(new int[tiles.length * tiles[0].length * Main.TILESIZE * Main.TILESIZE], tiles[0].length * Main.TILESIZE);
	}
	
	// Same as above
	public void setMap(int[][] map) {
		this.map = map;
		
		this.tiles = ContentLoader.convertMapToTileArray(map);
				
		this.width = tiles[0].length * Main.TILESIZE;
		this.height = tiles.length * Main.TILESIZE;

		this.bitmap = new Bitmap(new int[width * height * Main.TILESIZE * Main.TILESIZE], width);
	}
	
	// Sets the position
	public void setPosition(int x, int y) { 
		this.xOffset -= ((this.xOffset - x)) * tween; 
		this.yOffset -= ((this.yOffset - y)) * tween;
		
		fixBounds();
	}
	
	// Corrects xOffset and yOffset if the Tilemap's end would be visible otherwise
	public void fixBounds() {
		if(xOffset > 0) xOffset = 0;
		else if(xOffset < -width + Main.WIDTH) xOffset = -width + Main.WIDTH;
		if(yOffset > 0) yOffset = 0;
		else if(yOffset < -height + Main.HEIGHT) yOffset = -height + Main.HEIGHT;
	}
	
	// Returns the TileType of a tile at a specified location
	public int getType(int x, int y) {
		return map[y][x];
	}
	
	public int getXOffset() {
		return (int) xOffset;
	}
	
	public int getYOffset() {
		return (int) yOffset;
	}
	
}