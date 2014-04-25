package nona.platformer.drawable;

import java.awt.image.BufferedImage;

/*
 * @Author Leonard Vollmann
 * 
 * An image that can be drawn to a Bitmap
 */

public class Sprite {

	private int[] pixels;
	private int width;
	private Bitmap bitmap;

	// Constructor: Initializes from pixel array
	public Sprite(int[] pixels, int width, Bitmap bitmap) {
		this.pixels = pixels;
		this.width = width;
		this.bitmap = bitmap;
	}
	
	// Constructor: Initializes from BufferedImage
	public Sprite(BufferedImage image) {
	}
		
	// Draws Sprite on given Bitmap at given coordinates
	public void render(int x, int y) {
		for(int yy = y; yy < y + pixels.length / width; yy++) {
			if(yy > bitmap.getMaxY()) continue;
			for(int xx = x; xx < x + width; xx++) {
				if(xx > bitmap.getWidth()) continue;
				bitmap.getPixels()[xx + yy * bitmap.getWidth()] = pixels[(xx - x) + (yy - y) * width];
			}
		}
	}

}
