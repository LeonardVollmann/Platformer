package nona.platformer.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

	public static BufferedImage readImage(String path) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(Image.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
}
