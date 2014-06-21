package nona.platformer.graphics;

import java.awt.image.BufferedImage;

import nona.platformer.graphics.Bitmap;
import nona.platformer.handlers.content.ContentLoader;
import nona.platformer.main.Main;

public class Sprite {
	
	private int[][] pixels;
	private int width;
	
	private int counter = 0;
	
	private int currentImage = 0;
	private float interval;
	
	private boolean animated;
	
	public Sprite(BufferedImage[] images, float interval) {
		pixels = new int[images.length][images[0].getWidth() * images[0].getHeight()];
		
		for(int i = 0; i < images.length; i++) {
			BufferedImage image = images[i];
			this.pixels[i] = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
		}
		
		this.interval = interval;
		
		this.width = images[0].getWidth();
		
		animated = true;
	}
	
	public Sprite(BufferedImage image) {
		this.pixels = new int[1][image.getWidth() * image.getHeight()];
		this.width = image.getWidth();
		
		pixels[0] = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
		
		animated = false;
	}
	
	public Sprite(int width, int height, int colour) {
		this.pixels = new int[1][width * height];
		this.width = width;
		
		for(int i = 0; i < pixels[0].length; i++)
			pixels[0][i] = colour;
		
		animated = false;
	}
	
	public Sprite(String path, int width, float interval) {
		this(ContentLoader.loadSpriteSheet(path, width), interval);
	}
	
	public void update() {
		if(animated) {
			counter++;
		
			if(counter / (float) Main.FPS >= interval) {
				counter = 0;
			
				if(currentImage == pixels.length - 1) 
					currentImage = 0;
				else 
					currentImage++;
			}
		}
	}

	public void render(int x, int y, Bitmap bitmap) {
		int count = 0;
		
		for(int yy = y; yy < y + (pixels[currentImage].length / width); yy++) {
			if(yy >= bitmap.getHeight() || yy < 0) continue;
			for(int xx = x; xx < x + width; xx++) {
				if(xx >= bitmap.getWidth() || xx < 0) continue;
				if(pixels[currentImage][count] != 0xffff00ff)
					bitmap.getPixels()[xx + yy * bitmap.getWidth()] = pixels[currentImage][count];
				
				count++;
			}
		}
	}
	
	public void reset() {
		currentImage = 0;
	}
	
	public Sprite getFlipped() {
		return this;
	}

}
