package nona.platformer.drawable;

import nona.platformer.main.Main;

/*
 * @Author Leonard Vollmann
 * 
 * A Sprite that is animated using multiple images
 * Extending Sprite is necessary. If I made this 'independent', I would have to create seperate classes for animated and unanimated Tiles, Entities, etc.
 */

public class AnimatedSprite extends Sprite {
	
	private Sprite[] images;
	
	private int counter = 0;
	
	private float interval;
	
	private int currentIndex = 0;
		
	public AnimatedSprite(int[] pixels, int width, int height, float interval) {
		super(pixels, width);
		
		this.interval = interval;
		
		images = new Sprite[pixels.length / (width * height)];
		
		int index = 0;
		int[] tempPixels;
		for(int i = 0; i < pixels.length / (width * height); i++) {
			tempPixels = new int[width * height];
			for(int j = 0; j < width * height; j++) {
				tempPixels[j] = pixels[index];
				index++;
			}
			images[i] = new Sprite(tempPixels, width);
		}
	}
	
	public void update() {
		counter++;
		
		if(counter / (float) Main.FPS >= interval) {
			counter = 0;
			
			if(currentIndex + 1 == images.length) currentIndex = 0;
			else currentIndex++;
		}
	}
	
	public void render(int x, int y, Bitmap bitmap) {
		images[currentIndex].render(x, y, bitmap);
	}
	
}
