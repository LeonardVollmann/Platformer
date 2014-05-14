package nona.platformer.tile;

import nona.platformer.drawable.Bitmap;
import nona.platformer.main.Main;

/*
 * @Author Leonard Vollmann
 * 
 * Updates the Tiles and renders them to the correct position on the screen
 */

public class Tilemap {

	private Bitmap bitmap;
	
	private Tile[][] tiles;

	private int xOffset = 0;
	private int yOffset = 0;
	
	private int width;
	private int height;
	
	public Tilemap() {
	}
	
	public Tilemap(Tile[][] tiles) {
		setMap(tiles);
	}
	
	// Updates the Tilemap
	public void update() {
		// Sets the correct values to start the iteration through the tile array
		int iteratorX = Math.abs(xOffset / Main.TILESIZE);
		int iteratorY = Math.abs(yOffset / Main.TILESIZE);
				
		for(int y = iteratorY; y < iteratorY + Main.HEIGHT / Main.TILESIZE + 2 && y < tiles.length; y++) { // Only accesses tiles that should be visible; Adds one to prevent gaps
			for(int x = iteratorX; x < iteratorX + Main.WIDTH / Main.TILESIZE + 2 && x < tiles[0].length; x++) { // Tilemap cannot be smaller than width = Main.WIDTH / Main.TILESIZE, height = Main.HEIGHT / Main.TILESIZE
				tiles[y][x].render(x * Main.TILESIZE, y * Main.TILESIZE, this);
			}
		}
		
	}
	
	// Renders the Tilemap
	public void render(int[] targetPixels, int targetImageWidth) {
		bitmap.render(xOffset, yOffset, targetPixels, targetImageWidth);
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	
	// Note: only render or update a tilemap if either this function or the constructor taking in the parameter Tile[][] tiles have been called
	public void setMap(Tile[][] tiles) {
		this.tiles = tiles;
		
		this.width = tiles[0].length * Main.TILESIZE;
		this.height = tiles.length * Main.TILESIZE;

		this.bitmap = new Bitmap(new int[tiles.length * tiles[0].length * Main.TILESIZE * Main.TILESIZE], tiles[0].length * Main.TILESIZE);
	}
	
	public void fixBounds() {
		if(xOffset > 0) xOffset = 0;
		else if(xOffset < -width + Main.WIDTH) xOffset = -width + Main.WIDTH;
		if(yOffset > 0) yOffset = 0;
		else if(yOffset < -height + Main.HEIGHT) yOffset = -height + Main.HEIGHT;
	}
	
	public void setPosition(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		
		fixBounds();
	}
	
	public void move(int x, int y) {
		this.xOffset += x;
		this.yOffset += y;
		
		fixBounds();
	}
	
	public int getXOffset() {
		return xOffset;
	}
	
	public int getYOffset() {
		return yOffset;
	}
	
}