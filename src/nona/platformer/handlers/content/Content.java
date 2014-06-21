package nona.platformer.handlers.content;

import nona.platformer.entity.character.player.Player;
import nona.platformer.graphics.Sprite;
import nona.platformer.main.Main;
import nona.platformer.tile.AirTile;
import nona.platformer.tile.FullCollisionTile;
import nona.platformer.tile.VisualTile;

public class Content {
	
	public static Sprite[][] Tileset = ContentLoader.loadTileSet("/tilesets/tileset.gif");

	public static Sprite Sprite_Player_Idle = new Sprite(Main.TILESIZE, Main.TILESIZE, 0xffffff);
	public static Sprite Sprite_Player_Walking = new Sprite(Main.TILESIZE, Main.TILESIZE, 0xff0000);
	public static Sprite Sprite_Player_InAir = new Sprite(Main.TILESIZE, Main.TILESIZE, 0x0000ff);
	
	public static AirTile Tile_Air = new AirTile();
	public static FullCollisionTile Tile_Solid = new FullCollisionTile(Tileset[0][1]);
	public static VisualTile Tile_Visual = new VisualTile(Tileset[0][2]);
	
	public static Player Player = new Player(Main.TILESIZE + 8, Main.TILESIZE * 4 + 8, Main.TILESIZE, Main.TILESIZE, Content.Sprite_Player_Idle, Content.Sprite_Player_Walking, Content.Sprite_Player_InAir, Content.Sprite_Player_InAir);
	
//	public static Tilemap LoadedMap = new Tilemap("/maps/map.txt");
	public static int[][] Map = new int[][] {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
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
													     {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
}
