package nona.platformer.graphics;


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
	
	// Renders the Bitmap to a 1D-Array of pixels
	public void render(int x, int y, int[] targetPixels, int targetPixelsWidth) {
		for(int yy = y; yy < y + pixels.length / width; yy++) {
			if(yy >= targetPixels.length / targetPixelsWidth || yy < 0) continue; // Prevents Arrayindexoutofboundsexceptions
			for(int xx = x; xx < x + width; xx++) {
				if(xx >= targetPixelsWidth || xx < 0) continue; // Prevents Arrayindexoutofboundsexceptions
				targetPixels[xx + yy * targetPixelsWidth] = pixels[(xx - x) + (yy - y) * width];
			}
		}
	}
	
	// Sets entire bitmap to one color
	public void setColor(int c) {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = c;
		}
	}
	
	// Returns pixels for Bitmaps to be drawn onto other Bitmaps
	public int[] getPixels() {
		return pixels;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return pixels.length / width;
	}
	
}