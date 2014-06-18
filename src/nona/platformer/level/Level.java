package nona.platformer.level;

import java.util.ArrayList;

import nona.platformer.entity.Entity;
import nona.platformer.entity.character.player.Player;
import nona.platformer.graphics.Bitmap;
import nona.platformer.handlers.content.ContentLoader;
import nona.platformer.main.Game;
import nona.platformer.main.Main;
import nona.platformer.tile.Tile;

public class Level {
	
	private Player player;
	
	private ArrayList<Entity> entities = new ArrayList<Entity>();
		
	private float tween = 1f;
	
	private int width;
	private int height;
	
	private int xOffset;
	private int yOffset;
	
	private int[][] map;
	private Tile[][] tiles;
	
	private Bitmap bitmap;
	
	public Level(Player player, int[][] map) {
		this.player = player;
		setMap(map);

		bitmap = new Bitmap(width, height);
		
		player.setLevel(this);
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
				
		for(int y = iteratorY; y < iteratorY + Main.HEIGHT / Main.TILESIZE + 2 && y < tiles.length; y++) {
			for(int x = iteratorX; x < iteratorX + Main.WIDTH / Main.TILESIZE + 2 && x < tiles[0].length; x++) {
				if(tiles[y][x].getType() != Tile.TILE_AIR) tiles[y][x].render(x * Main.TILESIZE, y * Main.TILESIZE, this);
			}
		}
		
		player.accelerateY(Game.GRAVITY);
		player.update();
	} 
	
	public void render(Bitmap target) {
		for(int y = 0; y < height; y += Main.TILESIZE) {
			for(int x = 0; x < width; x += Main.TILESIZE) {
				tiles[y / Main.TILESIZE][x / Main.TILESIZE].render(x, y, this);
			}
		}
		
		player.render(bitmap);
		bitmap.render(xOffset, yOffset, target.getPixels(), target.getWidth());
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
	
	public void add(Entity e) {
		entities.add(e);
	}
	
	private void setMap(int[][] map) {
		this.map = map;
		
		this.tiles = ContentLoader.convertMapToTileArray(map);
				
		this.width = tiles[0].length * Main.TILESIZE;
		this.height = tiles.length * Main.TILESIZE;

		this.bitmap = new Bitmap(new int[width * height * Main.TILESIZE * Main.TILESIZE], width);
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	
	public int getXOffset() {
		return xOffset;
	}
	
	public int getYOffset() {
		return yOffset;
	}
	
	public int getType(int x, int y) {
		return map[y][x];
	}
	
}
