package nona.platformer.graphics.sprite;

import nona.platformer.graphics.Bitmap;
import nona.platformer.main.Main;

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
	
	public AnimatedSprite(Sprite[] sprites, int width, float interval) {
		super(sprites[0].pixels, width);
		
		this.interval = interval;
		
		this.images = sprites;
	}
	
	public void update() {
		counter++;
		
		if(counter / (float) Main.FPS >= interval) {
			counter = 0;
			
			if(currentIndex == images.length - 1) currentIndex = 0;
			else currentIndex++;
		}
	}
	
	// Renders the current image
	public void render(int x, int y, Bitmap bitmap) {
		images[currentIndex].render(x, y, bitmap);
	}
	
	public AnimatedSprite getFlipped() {
		Sprite[] sprites = new Sprite[images.length];
		for(int i = 0; i < sprites.length; i++)
			sprites[i] = images[i].getFlipped();
		return new AnimatedSprite(sprites, width, interval);
	}
	
	public void reset() {
		currentIndex = 0;
	}
	
}
