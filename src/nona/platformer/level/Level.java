package nona.platformer.level;

import java.util.ArrayList;

import nona.platformer.entity.Entity;
import nona.platformer.entity.character.Character;
import nona.platformer.entity.character.player.Player;
import nona.platformer.graphics.Bitmap;
import nona.platformer.handlers.Keys;
import nona.platformer.handlers.content.Content;
import nona.platformer.main.Game;
import nona.platformer.main.Main;
import nona.platformer.tile.Tile;

public class Level {
	
	private Player player;
	
	private ArrayList<Entity> entities = new ArrayList<Entity>();
		
	private float tween = 0.15f;
	
	private int width;
	private int height;
	
	private int xOffset;
	private int yOffset;
	
	private int[][] map;
//	private Tile[][] tiles;
	
	private Bitmap bitmap;
	
	private ArrayList<Character> characters;
	
	public Level(Player player, int[][] map) {
		this.player = player;
		setMap(map);

		bitmap = new Bitmap(width, height);
		
		player.setLevel(this);
		
		characters = new ArrayList<Character>();
	
		characters.add(player);
	}
	
	public void update() {
		bitmap.setColor(0);
		
//		for(int y = 0; y < tiles.length; y++) {
//			for(int x = 0; x < tiles[0].length; x++) {
//				tiles[y][x].update();
//			}
//		}	
		
		int iteratorX = (int) (Math.abs(xOffset / Main.TILESIZE));
		int iteratorY = (int) (Math.abs(yOffset / Main.TILESIZE));
				
//		for(int y = iteratorY; y < iteratorY + Main.HEIGHT / Main.TILESIZE + 2 && y < tiles.length; y++) {
//			for(int x = iteratorX; x < iteratorX + Main.WIDTH / Main.TILESIZE + 2 && x < tiles[0].length; x++) {
//				if(map[y][x] != Tile.TILE_AIR) 
//					tiles[y][x].render(x * Main.TILESIZE, y * Main.TILESIZE, this);
//			}
//		}
		
		for(int y = iteratorY; y < iteratorY + Main.HEIGHT / Main.TILESIZE + 2 && y < map.length; y++) {
			for(int x = iteratorX; x < iteratorX + Main.WIDTH / Main.TILESIZE + 2 && x < map[0].length; x++) {
				if(map[y][x] != Tile.TILE_AIR)
					getTile(map[y][x]).render(x * Main.TILESIZE, y * Main.TILESIZE, this);
			}
		}
		
		handleKeys();
		
		for(Character c : characters) {
			c.update();
			c.accelerateY(Game.GRAVITY);
		}
	} 
	
	public void render(Bitmap target) {
		for(int y = 0; y < height; y += Main.TILESIZE) {
			for(int x = 0; x < width; x += Main.TILESIZE) {
//				tiles[y / Main.TILESIZE][x / Main.TILESIZE].render(x, y, this);
				getTile(map[y / Main.TILESIZE][x / Main.TILESIZE]).render(x, y, this);
			}
		}
		
		player.render(bitmap);
		bitmap.render(xOffset, yOffset, target.getPixels(), target.getWidth());
	}
	
	public void handleKeys() {
		if(Keys.isKeyHit(Keys.W))
			player.jump();
		if(Keys.isKeyPressed(Keys.A)) 
			player.setXvel(-2);
		else if(Keys.isKeyPressed(Keys.D)) 
			player.setXvel(2);
		else
			player.setXvel(0);
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
		
//		this.tiles = ContentLoader.convertMapToTileArray(map);
		
//		this.width = tiles[0].length * Main.TILESIZE;
//		this.height = tiles.length * Main.TILESIZE;

		this.width = map[0].length * Main.TILESIZE;
		this.height = map.length * Main.TILESIZE;

		
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
	
	public Tile getTile(int type) {
		switch(type) {
		case Tile.TILE_AIR:
			return Content.Tile_Air;
		case Tile.TILE_SOLID_1:
			return Content.Tile_Solid_1;
		case Tile.TILE_SOLID_2:
			return Content.Tile_Solid_2;
		case Tile.TILE_VISUAL:
			return Content.Tile_Visual;
		default:
			return null;
		}
	}
	
	public int getCollisionType(int x, int y) {
		if(Tile.FullCollisionTiles.contains(map[y][x]))
			return Tile.TILE_COLLISION_FULL;
		else if(Tile.NoCollisionTiles.contains(map[y][x]))
			return Tile.TILE_COLLISION_NONE;
		else return -1;
	}
	
}
