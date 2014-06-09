package nona.platformer.handlers.content;

import nona.platformer.graphics.sprite.AnimatedSprite;
import nona.platformer.graphics.sprite.Sprite;
import nona.platformer.main.Main;
import nona.platformer.tile.AirTile;
import nona.platformer.tile.FullCollisionTile;
import nona.platformer.tile.Tilemap;
import nona.platformer.tile.VisualTile;

/*
 * @Author Leonard Vollmann
 * 
 * This class loads all the images, sounds, tilemaps etc. and stores them statically
 */

public class Content {

	// Sprites
	public static AnimatedSprite Sprite_Player_Idle = new AnimatedSprite(new Sprite[] {new Sprite(Main.TILESIZE, Main.TILESIZE, 0xffffff)}, Main.TILESIZE, Main.TILESIZE);
	public static AnimatedSprite Sprite_Player_Walking = new AnimatedSprite(new Sprite[] {new Sprite(Main.TILESIZE, Main.TILESIZE, 0xff0000)}, Main.TILESIZE, Main.TILESIZE);
	public static AnimatedSprite Sprite_Player_InAir = new AnimatedSprite(new Sprite[] {new Sprite(Main.TILESIZE, Main.TILESIZE, 0x00ff00)}, Main.TILESIZE, Main.TILESIZE);
	
	// Tilesets
	public static Sprite[][] Tileset = ContentLoader.loadTileSet("/tilesets/tileset.gif");
	
	// Tiles
	public static AirTile Tile_Air = new AirTile();
	public static FullCollisionTile Tile_Solid = new FullCollisionTile(Tileset[0][1]);
	public static VisualTile Tile_Visual = new VisualTile(Tileset[0][2]);
		
	// Tilemaps
//	public static Tilemap Map = new Tilemap("/maps/map.txt");
	public static Tilemap Map = new Tilemap(new int[][] {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
													     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
													     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
													     {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
													     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
													     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1},
				  									     {1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1},
													     {1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1},
													     {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
													     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
													     {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1},
													     {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
													     {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
													     {1, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 1},
													     {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}});
}
