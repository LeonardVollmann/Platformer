package nona.platformer.drawable;

import nona.platformer.main.Main;

/*
 * @Author Leonard Vollmann
 * 
 * Animates things using an array of Sprites
 */

public class Animation {

	private Sprite[] images;
	
	private float interval; // In seconds. Is used to determine when to switch to the next image
	
	private int counter = 0; // Is used to compute passed time
	
	private int index = 0;
	
	public Animation(Sprite[] images, float interval) {
		this.images = images;
		this.interval = interval;
	}
	
	// Calculates the passed time and changes the value of index
	public void update() {
		counter++;
		
		if(counter / (float) Main.FPS >= interval) {
			counter = 0;
			
			if(index + 1 == images.length) index = 0;
			else index++;
		}
	}
	
	public void render(int x, int y, Bitmap bitmap) {
		images[index].render(x, y, bitmap);
	}
	
}
