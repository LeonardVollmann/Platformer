package nona.platformer.graphics.sprite;

import java.awt.image.BufferedImage;

import nona.platformer.graphics.Bitmap;

/*
 * @Author Leonard Vollmann
 * 
 * An image that can be drawn to a Bitmap
 */

public class Sprite {
	
	protected int[] pixels;
	protected int width;

	// Constructor: Initializes from pixel array
	public Sprite(int[] pixels, int width) {
		this.pixels = pixels;
		this.width = width;
	}
	
	// Constructor: Initializes from BufferedImage
	public Sprite(BufferedImage image) {
		this.pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth()); // Extracts the image's colour array and stores it in this.pixels

		this.width = image.getWidth();
	}
	
	public Sprite(int width, int height, int color) {
		this.width = width;
		this.pixels = new int[width * height];
	
		for(int i = 0; i < pixels.length; i++) pixels[i] = color;
	}
	
	public void update() {
	}

	// Draws Sprite on given Bitmap at given coordinates
	public void render(int x, int y, Bitmap bitmap) {
		int count = 0;
		
		for(int yy = y; yy < y + (pixels.length / width); yy++) {
			if(yy >= bitmap.getHeight() || yy < 0) continue; // Prevents Arrayindexoutofboundsexceptions
			for(int xx = x; xx < x + width; xx++) {
				if(xx >= bitmap.getWidth() || xx < 0) continue; // Prevents Arrayindexoutofboundsexceptions
				if(pixels[count] != 0xffff00ff)	//0xffff00ff is not rendered
					bitmap.getPixels()[xx + yy * bitmap.getWidth()] = pixels[count]; // Renders one pixel
				count++;
			}
		}
	}

}
