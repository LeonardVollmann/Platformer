package nona.platformer.tile;

import nona.platformer.graphics.Sprite;
import nona.platformer.main.Main;

public class AirTile extends Tile {

	public AirTile() {
		this.sprite = new Sprite(Main.TILESIZE, Main.TILESIZE, 0);
	}
	
}
