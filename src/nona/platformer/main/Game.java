package nona.platformer.main;

import nona.platformer.graphics.Bitmap;
import nona.platformer.handlers.content.Content;
import nona.platformer.level.Level;

public class Game {
	
	public static final float GRAVITY = 0.2f;
	
	private Bitmap screen;
	
	private Level level;

	public Game() {		
		screen = new Bitmap(new int[360 * 240], 360);

		level = new Level(Content.Player, Content.Map);
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
