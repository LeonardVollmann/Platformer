package nona.platformer.main.gamestate;

import java.util.ArrayList;

import nona.platformer.graphics.Bitmap;
import nona.platformer.handlers.content.Content;
import nona.platformer.level.Level;

public class LevelState extends GameState {
	

	private ArrayList<Level> levels;
	private int currentLevel;
	
	public LevelState() {
		levels = new ArrayList<Level>();
		
		levels.add(Content.Level);
		
		currentLevel = 0;
	}
	
	@Override
	public void update() {
		levels.get(currentLevel).update();
	}

	@Override
	public void render(Bitmap bitmap) {
		levels.get(currentLevel).render(bitmap);
	}

}
