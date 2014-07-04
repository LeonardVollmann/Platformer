package nona.platformer.main.gamestate;

import java.util.ArrayList;

import nona.platformer.graphics.Bitmap;


public class GameStateManager {

	private static ArrayList<GameState> gameStates;
	
	private static int currentState;
	
	public static void init() {
		gameStates = new ArrayList<GameState>();
		
		gameStates.add(new LevelState());
		
		currentState = 0;
	}
	
	public static void update() {		
		gameStates.get(currentState).update();
	}
	
	public static void render(Bitmap bitmap) {
		gameStates.get(currentState).render(bitmap);
	}
	
	public static void getState(int state) {
		currentState = state;
	}
	
}