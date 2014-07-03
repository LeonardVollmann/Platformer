package nona.platformer.main;

import nona.platformer.graphics.Bitmap;
import nona.platformer.handlers.content.Content;
import nona.platformer.level.Level;
import nona.platformer.tile.Tile;

public class Game {
	
	public static final float GRAVITY = 0.2f;
	
	private Bitmap screen;
	
	private Level level;

	public Game() {		
		screen = new Bitmap(new int[Main.WIDTH * Main.HEIGHT], Main.WIDTH);

		level = new Level(Content.Player, Content.Map);
		
		Tile.init();
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
