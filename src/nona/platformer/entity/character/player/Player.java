package nona.platformer.entity.character.player;

import nona.platformer.entity.character.Character;
import nona.platformer.graphics.Sprite;
import nona.platformer.handlers.content.Content;
import nona.platformer.main.Main;

public class Player extends Character {
	
	public static final Sprite SPRITE_IDLE = Content.SPRITE_PLAYER_IDLE;
	public static final Sprite SPRITE_WALKING = Content.SPRITE_PLAYER_WALKING;
	public static final Sprite SPRITE_INAIR = Content.SPRITE_PLAYER_INAIR;
	public static final Sprite SPRITE_WALKING_FLIPPED = SPRITE_WALKING.getFlipped();
	public static final Sprite SPRITE_INAIR_FLIPPED = SPRITE_INAIR.getFlipped();
	
	public Player(int x, int y) {
		super(x, y, Main.TILESIZE, Main.TILESIZE, SPRITE_IDLE);
		
		jumpingSpeed = -5f;
		maxVel = 5;
	}
	
	public void update() {
		super.update();
		
		level.setPosition(-xscreen + Main.WIDTH / 2, -yscreen + Main.HEIGHT / 2);
	}
		
	public void render() {
		super.render();
	}
	
	protected void updateActions() {
		if(on_ground && xvel == 0 && yvel == 0)
			sprite = SPRITE_IDLE;
		else if(in_air && xvel == 0)
			sprite = SPRITE_IDLE;
		else if(on_ground && xvel > 0 && yvel == 0)
			sprite = SPRITE_WALKING;
		else if(on_ground && xvel < 0 && yvel == 0)
			sprite = SPRITE_WALKING_FLIPPED;
		else if(in_air && yvel < 0 && xvel > 0)
			sprite = SPRITE_INAIR;
		else if(in_air && yvel < 0 && xvel < 0)
			sprite = SPRITE_INAIR_FLIPPED;
	}
	
	public void setXvel(int xvel) {
		this.xvel = xvel;
	}
	
	public void setYvel(int yvel) {
		this.yvel = yvel;
	}
	
}
