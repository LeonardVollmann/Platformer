package nona.platformer.tile;

import nona.platformer.graphics.sprite.Sprite;
import nona.platformer.main.Main;

/* 
 * @Author Leonard Vollmann
 * 
 * A Tile that is used as a filler and is skipped during rendering and updating
 */

public class AirTile extends Tile {

	public AirTile() {
		this.sprite = new Sprite(Main.TILESIZE, Main.TILESIZE, 0);
	}
	
}
