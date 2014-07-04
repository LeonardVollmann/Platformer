package nona.platformer.main.gamestate;

import nona.platformer.graphics.Bitmap;

public abstract class GameState {

	public abstract void update();
	
	public abstract void render(Bitmap bitmap);
	
}
