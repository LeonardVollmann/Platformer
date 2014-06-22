package nona.platformer.entity.character.player;

import nona.platformer.entity.character.Character;
import nona.platformer.graphics.Sprite;
import nona.platformer.handlers.Keys;
import nona.platformer.main.Main;

public class Player extends Character {
	
	public Player(int x, int y, int width, int height, Sprite action_idle, Sprite action_walking, Sprite action_jumping, Sprite action_falling) {
		super(x, y, width, height, action_idle, action_walking, action_jumping, action_falling);
						
		jumpingSpeed = -5f;
		maxVel = 5;
	}
	
	public void update() {
		super.update();
		
		handleKeys();
		
		level.setPosition(-xscreen + Main.WIDTH / 2, -yscreen + Main.HEIGHT / 2);
	}
	
	private void handleKeys() {
		if(Keys.isKeyPressed(Keys.A) || Keys.isKeyPressed(Keys.LEFT)) xvel = -2;
		else if(Keys.isKeyPressed(Keys.D) || Keys.isKeyPressed(Keys.RIGHT)) xvel = 2;
		else xvel = 0;

		if(Keys.isKeyHit(Keys.W) || Keys.isKeyHit(Keys.UP)) jump();
	}
	
	public void render() {
		super.render();
	}
	
}
