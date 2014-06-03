package nona.platformer.tile;

import nona.platformer.graphics.sprite.Sprite;

/*
 * @Author Leonard Vollmann
 * 
 * Superclass for all the tiles that collide with the player
 */

public class FullCollisionTile extends Tile {

	public FullCollisionTile(Sprite sprite) {
		this.sprite = sprite;
	}
	
}
