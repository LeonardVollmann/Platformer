package nona.platformer.graphics;

public class Bitmap {

	private int[] pixels;
	private int width;
	
	public Bitmap(int[] pixels, int width) {
		this.pixels = pixels;
		this.width = width;
	}
	
	public Bitmap(int width, int height) {
		this.pixels = new int[width * height];
		this.width = width;
	}
	
	public void render(int x, int y, int[] targetPixels, int targetPixelsWidth) {
		for(int yy = y; yy < y + pixels.length / width; yy++) {
			if(yy >= targetPixels.length / targetPixelsWidth || yy < 0) continue;
			for(int xx = x; xx < x + width; xx++) {
				if(xx >= targetPixelsWidth || xx < 0) continue;
				targetPixels[xx + yy * targetPixelsWidth] = pixels[(xx - x) + (yy - y) * width];
			}
		}
	}
	
	public void setColor(int c) {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = c;
		}
	}
	
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
