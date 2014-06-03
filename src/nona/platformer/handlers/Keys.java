package nona.platformer.handlers;

import java.awt.event.KeyEvent;

/*
 * @Author Leonard Vollmann
 * 
 * Handles the Keys
 */

public class Keys {

	public static final int NUM_KEYS = 5;
	
	public static boolean[] keyStates = new boolean[NUM_KEYS];
	
	public static final int W = 0;
	public static final int A = 1;
	public static final int S = 2;
	public static final int D = 3;
	public static final int SPACE = 4;
	
	public static void keyPressed(int key) {	
		switch(key) {
		case KeyEvent.VK_W:
			keyStates[W] = true;
			break;
		case KeyEvent.VK_A:
			keyStates[A] = true;
			break;
		case KeyEvent.VK_S:
			keyStates[S] = true;
			break;
		case KeyEvent.VK_D:
			keyStates[D] = true;
			break;
		case KeyEvent.VK_SPACE:
			keyStates[SPACE] = true;
			break;
		}
	}
	
	public static void keyReleased(int key) {						
		switch(key) {
		case KeyEvent.VK_W:
			keyStates[W] = false;
			break;
		case KeyEvent.VK_A:
			keyStates[A] = false;
			break;
		case KeyEvent.VK_S:
			keyStates[S] = false;
			break;
		case KeyEvent.VK_D:
			keyStates[D] = false;
			break;
		case KeyEvent.VK_SPACE:
			keyStates[SPACE] = false;
			break;
		}
	}
	
	public static boolean isKeyPressed(int index) {
		return keyStates[index];
	}
	
	public static boolean isKeyHit(int index) {
		if(keyStates[index]) {
			keyStates[index] = false;
			return true;
		} else return false;
	}
	
}
