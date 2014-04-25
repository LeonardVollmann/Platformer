package nona.platformer.main;

import java.awt.event.KeyEvent;

/*
 * @Author Leonard Vollmann
 * 
 * Handles the Keys
 */

public class Keys {
	
	// Booleans that indicate whether or not the key is pressed
	public static boolean up;
	public static boolean down;
	public static boolean left;
	public static boolean right;
	public static boolean w;
	public static boolean a;
	public static boolean s;
	public static boolean d;
	public static boolean space;
	
	// Sets boolean to true if the corresponding key is pressed
	public static void keyPressed(KeyEvent key) {
		int k = key.getKeyCode();
		switch (k) {
		case KeyEvent.VK_UP:
			up = true;
			return;
		case KeyEvent.VK_DOWN:
			down = true;
			return;
		case KeyEvent.VK_LEFT:
			left = true;
			return;
		case KeyEvent.VK_RIGHT:
			right = true;
			return;
		case KeyEvent.VK_W:
			w = true;
			return;
		case KeyEvent.VK_A:
			a = true;
			return;
		case KeyEvent.VK_S:
			s = true;
			return;
		case KeyEvent.VK_D:
			d = true;
			return;
		case KeyEvent.VK_SPACE:
			space = true;
			return;
		}
	}
	
	// Sets boolean to false if the corresponding key is released
	public static void keyReleased(KeyEvent key) {
		int k = key.getKeyCode();
		switch (k) {
		case KeyEvent.VK_UP:
			up = false;
			return;
		case KeyEvent.VK_DOWN:
			down = false;
			return;
		case KeyEvent.VK_LEFT:
			left = false;
			return;
		case KeyEvent.VK_RIGHT:
			right = false;
			return;
		case KeyEvent.VK_W:
			w = false;
			return;
		case KeyEvent.VK_A:
			a = false;
			return;
		case KeyEvent.VK_S:
			s = false;
			return;
		case KeyEvent.VK_D:
			d = false;
			return;
		case KeyEvent.VK_SPACE:
			space = false;
			return;
		}
	}
	
}
