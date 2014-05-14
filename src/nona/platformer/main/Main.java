package nona.platformer.main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

/*
 * @Author Leonard Vollmann
 * 
 * Main class: contains main method, main loop, etc.
 */

@SuppressWarnings("serial")
public class Main extends Canvas implements Runnable, KeyListener {
	
	// Constants
	public static final int WIDTH = 360;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	public static final Dimension SIZE = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
	public static final int FPS = 60;
	public static final int TILESIZE = 16;
	
	// Main loop
	private Thread thread;
	
	// Drawing
	private static BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private static int[] raster = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private BufferStrategy bs;
	private Graphics2D g;
	
	// Frame
	private JFrame frame;
	
	// Game
	private Game game = new Game();
	
	public Main() {
		super();
				
		setFocusable(true);
		requestFocus();
		
		// Add KeyListener
		addKeyListener(this);
		
		// Set size
		setPreferredSize(SIZE);
		setMaximumSize(SIZE);
		setMinimumSize(SIZE);
		
		// Initialize frame
		frame = new JFrame("Platformer");
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		frame.add(this, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.requestFocus();
		frame.pack();
		frame.setVisible(true);				
		
		// Initialize thread
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		long lastTime = System.nanoTime();
		long now;
		double nsPerUpdate = 1000000000 / FPS;
		double delta = 0;
		long lastTimeMillis = System.currentTimeMillis();		
		int updates = 0;
		int frames = 0;
		boolean shouldrender = false;
		
		// Main loop
		while (true) {
			now = System.nanoTime();
			
			delta += (now - lastTime) / nsPerUpdate;
		
			while (delta > 1) {
				delta--;
				update();
				updates++;
				shouldrender = true;
			}
			
			if(shouldrender) {
				render();
				frames++;
				shouldrender = false;
			}
			
			lastTime = now;
			
			if (System.currentTimeMillis() - lastTimeMillis >= 1000) {
				lastTimeMillis += 1000;
				frame.setTitle("Platformer | UPS [ " + updates + " ] FPS [ " + frames + " ]");
				updates = 0;
				frames = 0;
			}
		}
	}
	
	// Updates the game
	public void update() {
		game.update();
	}
	
	// Handles all the rendering
	public void render() {
		bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		game.render();
		
		// Draw image
		g = (Graphics2D) bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g.dispose();
		
		// Swap buffers
		bs.show();
	}
	
	public void keyTyped(KeyEvent key) {
	}
	
	public void keyPressed(KeyEvent key) {
		Keys.keyPressed(key.getKeyCode());
	}
	
	public void keyReleased(KeyEvent key) {
		Keys.keyReleased(key.getKeyCode());
	}
	
	public static int[] getRaster() {
		return raster;
	}
	
	// Used to find out wether or not a function is being called
	public static void debugMessage() {
		System.out.println("Debug message");
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
}
