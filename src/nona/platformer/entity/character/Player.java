package nona.platformer.entity.character;

import nona.platformer.graphics.sprite.AnimatedSprite;
import nona.platformer.handlers.Keys;
import nona.platformer.main.Main;

public class Player extends Character {
	
	protected AnimatedSprite action_jumping;
	protected AnimatedSprite action_falling;
	
	public Player(int x, int y, int width, int height, AnimatedSprite action_idle, AnimatedSprite action_walking, AnimatedSprite action_jumping, AnimatedSprite action_falling) {
		super(x, y, width, height, action_idle, action_walking);
				
		this.action_jumping = action_jumping;
		this.action_falling = action_falling;
		
		jumpingSpeed = -5;
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
	
	protected void updateActions() {
		super.updateActions();
			
		if(yvel < 0 && in_air)
			sprite = action_jumping;
		else if(yvel > 0 && in_air)
			sprite = action_falling;
	
		((AnimatedSprite) sprite).reset();
	}
	
	public void render() {
		super.render();
	}
	
}
