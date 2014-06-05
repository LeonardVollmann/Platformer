package nona.platformer.handlers;

import java.awt.event.KeyEvent;

/*
 * @Author Leonard Vollmann
 * 
 * Handles the Keys
 */

public class Keys {

	// Number of keys
	public static final int NUM_KEYS = 12;
	
	// Boolean array that contains values for whether or not a key is pressed
	public static boolean[] keyStates = new boolean[NUM_KEYS];
	
	// The Key indices
	public static final int W = 0;
	public static final int A = 1;
	public static final int S = 2;
	public static final int D = 3;
	public static final int UP = 4;
	public static final int DOWN = 5;
	public static final int LEFT = 6;
	public static final int RIGHT = 7;
	public static final int SPACE = 8;
	public static final int SHIFT = 9;
	public static final int ESCAPE = 10;
	public static final int ENTER = 11;
	
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
		case KeyEvent.VK_UP:
			keyStates[UP] = true;
			break;
		case KeyEvent.VK_DOWN:
			keyStates[DOWN] = true;
			break;
		case KeyEvent.VK_LEFT:
			keyStates[LEFT] = true;
			break;
		case KeyEvent.VK_RIGHT:
			keyStates[RIGHT] = true;
			break;
		case KeyEvent.VK_SPACE:
			keyStates[SPACE] = true;
			break;
		case KeyEvent.VK_SHIFT:
			keyStates[SHIFT] = true;
			break;
		case KeyEvent.VK_ENTER:
			keyStates[ENTER] = true;
			break;
		case KeyEvent.VK_ESCAPE:
			keyStates[ESCAPE] = true;
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
		case KeyEvent.VK_UP:
			keyStates[UP] = false;
			break;
		case KeyEvent.VK_DOWN:
			keyStates[DOWN] = false;
			break;
		case KeyEvent.VK_LEFT:
			keyStates[LEFT] = false;
			break;
		case KeyEvent.VK_RIGHT:
			keyStates[RIGHT] = false;
			break;
		case KeyEvent.VK_SPACE:
			keyStates[SPACE] = false;
			break;
		case KeyEvent.VK_SHIFT:
			keyStates[SHIFT] = false;
			break;
		case KeyEvent.VK_ENTER:
			keyStates[ENTER] = false;
			break;
		case KeyEvent.VK_ESCAPE:
			keyStates[ESCAPE] = false;
			break;
		}
	}
	
	// Returns true if a key is pressed at the moment
	public static boolean isKeyPressed(int index) {
		return keyStates[index];
	}
	
	// Returns true if a key is pressed at the moment. Sets its value to false afterwards to ensure a keyhit is only recognized once
	public static boolean isKeyHit(int index) {
		if(isKeyPressed(index)) {
			keyStates[index] = false;
			return true;
		} else return false;
	}
	
}
