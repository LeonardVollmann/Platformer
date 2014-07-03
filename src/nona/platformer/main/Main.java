package nona.platformer.main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import nona.platformer.handlers.Keys;

@SuppressWarnings("serial")
public class Main extends Canvas implements Runnable, KeyListener {
	
	public static final int WIDTH = 368;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	public static final Dimension SIZE = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
	public static final int FPS = 30;
	public static final int TILESIZE = 16;
	
	private Thread thread;
	
	private static BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private static int[] raster = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private BufferStrategy bs;
	private Graphics2D g;
	
	private JFrame frame;
	
	private Game game = new Game();
	
	public Main() {
		super();
				
		setFocusable(true);
		requestFocus();
		
		addKeyListener(this);
		
		setPreferredSize(SIZE);
		setMaximumSize(SIZE);
		setMinimumSize(SIZE);
		
		frame = new JFrame("Platformer");
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		frame.add(this, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.requestFocus();
		frame.pack();
		frame.setVisible(true);				
		
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
//		boolean shouldrender = false;
		
		while (true) {
			now = System.nanoTime();
			
			delta += (now - lastTime) / nsPerUpdate;
		
			while (delta > 1) {
				delta--;
				update();
				updates++;
//				shouldrender = true;
			}
			
//			if(shouldrender) {
				render();
				frames++;
//				shouldrender = false;
//			}
			
			lastTime = now;
			
			if (System.currentTimeMillis() - lastTimeMillis >= 1000) {
				lastTimeMillis += 1000;
				frame.setTitle("Platformer | UPS [ " + updates + " ] FPS [ " + frames + " ]");
				updates = 0;
				frames = 0;
			}
		}
	}
	
	public void update() {
		game.update();
	}
	
	public void render() {
		bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		clearScreen();
						
		game.render(raster);
		
		g = (Graphics2D) bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g.dispose();
				
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
	
	private void clearScreen() {
		for(int i = 0; i < raster.length; i++) {
			raster[i] = 0;
		}
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
}