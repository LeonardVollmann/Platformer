package nona.platformer.drawable;

/*
 * @Author Leonard Vollmann
 * 
 * The Background.
 */

public class Background {
	
	private Bitmap image;
	
	private int x;
	private int y;
	
	public Background(Bitmap image, int x, int y) {
		this.image = image;
		this.x = x;
		this.y = y;
	}
	
	public void render(int[] targetPixels, int targetPixelWidth) {
		image.render(x, y, targetPixels, targetPixelWidth);
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}
