package nona.platformer.graphics;

public class Background {
	
	private Bitmap image;
	
	private int x;
	private int y;
	
	private float tween;
	
	public Background(Bitmap image, int x, int y, float tween) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.tween = tween;
	}
	
	public void render(int[] targetPixels, int targetPixelWidth) {
		image.render(x, y, targetPixels, targetPixelWidth);
	}
	
	public void setPosition(int x, int y) {
		this.x += (this.x - x) * tween;
		this.y += (this.y - y) * tween;
	}
	
}
