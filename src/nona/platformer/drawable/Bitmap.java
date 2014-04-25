package nona.platformer.drawable;

import nona.platformer.main.Main;

/*
 * @Author Leonard Vollmann
 * 
 * Just like a Sprite, the Bitmap is just an image, on which the Sprites are drawn.
 * The Bitmap itself is drawn directly to the Screen
 */

public class Bitmap {

	private int[] pixels;
	private int width;
	
	public Bitmap(int[] pixels, int width) {
		this.pixels = pixels;
		this.width = width;
	}
	
	public void render(int x, int y, int[] pixelsToDrawTo) {
		for(int yy = y; yy < y + pixels.length / width; yy++) {
			if(yy > Main.HEIGHT || yy < 0) continue;
			for(int xx = x; xx < x + width; xx++) {
				if(xx > Main.WIDTH || xx < 0) continue;
				Main.getRaster()[xx + yy * Main.WIDTH] = pixels[(xx - x) + (yy - y) * width];
			}
		}
	}
	
	public int[] getPixels() {
		return pixels;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getMaxY() {
		return pixels.length / width;
	}
	
}
