package nona.platformer.tile;

import nona.platformer.graphics.Bitmap;
import nona.platformer.handlers.content.ContentLoader;
import nona.platformer.main.Main;

public class Tilemap {
	
	private Bitmap bitmap;
	
	private double tween;
	
	private Tile[][] tiles;
	private int[][] map;

	private float xOffset = 0;
	private float yOffset = 0;
	
	private int width;
	private int height;
	
	public Tilemap() {
	}
	
	public Tilemap(String path) {
		setMap(path);
	}
	
	public Tilemap(int[][] map) {
		setMap(map);
		
		tween = 1f;
	}
	
	public void update() {
		bitmap.setColor(0);
		
		for(int y = 0; y < tiles.length; y++) {
			for(int x = 0; x < tiles[0].length; x++) {
				tiles[y][x].update();
			}
		}	
		
		int iteratorX = (int) (Math.abs(xOffset / Main.TILESIZE));
		int iteratorY = (int) (Math.abs(yOffset / Main.TILESIZE));
				
		for(int y = iteratorY; y < iteratorY + Main.HEIGHT / Main.TILESIZE + 2 && y < tiles.length; y++) { // Only accesses tiles that should be visible; Adds one to prevent gaps
			for(int x = iteratorX; x < iteratorX + Main.WIDTH / Main.TILESIZE + 2 && x < tiles[0].length; x++) { // Tilemap cannot be smaller than width = Main.WIDTH / Main.TILESIZE, height = Main.HEIGHT / Main.TILESIZE
				if(tiles[y][x].getType() != Tile.TILE_AIR) tiles[y][x].render(x * Main.TILESIZE, y * Main.TILESIZE, this); // Doesn't render Air Tiles
			}
		}
	}
	
	public void render(Bitmap targetBitmap) {
		bitmap.render(0, 0, targetBitmap.getPixels(), targetBitmap.getWidth());
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	
	public void setMap(String path) {
		this.map = ContentLoader.loadTilemap(path);
		
		this.tiles = ContentLoader.convertMapToTileArray(map);
		
		this.width = tiles[0].length * Main.TILESIZE;
		this.height = tiles.length * Main.TILESIZE;

		this.bitmap = new Bitmap(new int[tiles.length * tiles[0].length * Main.TILESIZE * Main.TILESIZE], tiles[0].length * Main.TILESIZE);
	}
	
	public void setMap(int[][] map) {
		this.map = map;
		
		this.tiles = ContentLoader.convertMapToTileArray(map);
				
		this.width = tiles[0].length * Main.TILESIZE;
		this.height = tiles.length * Main.TILESIZE;

		this.bitmap = new Bitmap(new int[width * height * Main.TILESIZE * Main.TILESIZE], width);
	}
	
	public void setPosition(int x, int y) { 
		this.xOffset -= ((this.xOffset - x)) * tween; 
		this.yOffset -= ((this.yOffset - y)) * tween;
		
		fixBounds();
	}
	
	public void fixBounds() {
		if(xOffset > 0) xOffset = 0;
		else if(xOffset < -width + Main.WIDTH) xOffset = -width + Main.WIDTH;
		if(yOffset > 0) yOffset = 0;
		else if(yOffset < -height + Main.HEIGHT) yOffset = -height + Main.HEIGHT;
	}
	
	public int getType(int x, int y) {
		return map[y][x];
	}
	
	public int getXOffset() {
		return (int) xOffset;
	}
	
	public int getYOffset() {
		return (int) yOffset;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
}