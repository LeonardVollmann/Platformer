package nona.platformer.handlers.content;

import nona.platformer.entity.character.player.Player;
import nona.platformer.graphics.Sprite;
import nona.platformer.level.Level;
import nona.platformer.main.Main;
import nona.platformer.tile.AirTile;
import nona.platformer.tile.FullCollisionTile;
import nona.platformer.tile.VisualTile;

public class Content {

	public static final Sprite SPRITE_PLAYER_IDLE = new Sprite(ContentLoader.loadImage("/spritesheets/player_idle.gif"));
	public static final Sprite SPRITE_PLAYER_WALKING = new Sprite(ContentLoader.loadSpriteSheet("/spritesheets/player_walk.gif", 2), 0.15f);
	public static final Sprite SPRITE_PLAYER_INAIR = new Sprite(ContentLoader.loadImage("/spritesheets/player_inair.gif"));
	
	public static Sprite[][] Tileset = ContentLoader.loadTileSet("/tilesets/tileset.gif");
	
	public static AirTile Tile_Air = new AirTile();
	public static FullCollisionTile Tile_Solid_1 = new FullCollisionTile(Tileset[0][0]);
	public static FullCollisionTile Tile_Solid_2 = new FullCollisionTile(Tileset[0][1]);
	public static VisualTile Tile_Visual = new VisualTile(Tileset[0][0]);
	
	public static Player Player = new Player(Main.TILESIZE + 8, Main.TILESIZE * 4 + 8);
	
	public static int[][] Map = ContentLoader.loadMap(ContentLoader.loadImage("/maps/map.gif"));
	
	public static Level Level = new Level(Player, Map);
	
}
