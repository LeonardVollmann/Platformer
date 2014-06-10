package nona.platformer.main;

import nona.platformer.entity.character.Player;
import nona.platformer.graphics.Bitmap;
import nona.platformer.handlers.content.Content;
import nona.platformer.level.Level;

public class Game {
	
	public static final float GRAVITY = 0.2f;
	
	private Bitmap screen;
	
	private Level level;

	public Game() {		
		screen = new Bitmap(new int[360 * 240], 360);

		level = new Level(new Player(Main.TILESIZE, Main.TILESIZE * 4, Main.TILESIZE, Main.TILESIZE, Content.Sprite_Player_Idle, Content.Sprite_Player_Walking, Content.Sprite_Player_InAir, Content.Sprite_Player_InAir), Content.Map);
	}

	public void update() {
		level.update();
	}

	public void render(int[] raster) {
		level.render(screen);
		screen.render(0, 0, raster, Main.WIDTH);
	}

	public Bitmap getScreen() {
		return screen;
	}

}
