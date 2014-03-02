package com.so0le.wormerz;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.so0le.wormerz.graphics.Screen;
import com.so0le.wormerz.input.Keyboard;
import com.so0le.wormerz.state.GameState;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static int width = 300;
	public static int height = width / 4 * 3;
	public static int scale = 2;

	private Thread thread;
	private JFrame frame;
	private Screen screen;
	private GameState gs;
	private Keyboard key;
	private Font font = new Font("Tahoma", Font.BOLD, 14);
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int pixels[] = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	private boolean running = false;
	
	public Game() {
		Dimension size = new Dimension(width*scale, height*scale);
		setPreferredSize(size);
		
		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		gs = new GameState(key, screen);
		
		addKeyListener(key);
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		//Get the current time in nanosecs and millisecs.
		long timeLast = System.nanoTime();
		
		//Stuff for the update/render limiting.
		final double nsu = 1000000000.0 / 60.0; //60ups
		final double nsf = 1000000000.0 / 60.0; //60fps
		double du = 0;
		double df = 0;
		
		requestFocus();

		while (running) {
			//Take the current time and calculate the change.
			long timeNow = System.nanoTime();
			du += (timeNow-timeLast) / nsu;
			df += (timeNow-timeLast) / nsf;
			timeLast = timeNow;
			//UPS limit.
			while (du >= 1) {
				update();
				du--;
			}
			//FPS limit.
			while (df >= 1) {
				render();
				df--;
			}
		}
	}
	
	public void update() {
		gs.update();
		if (gs.worm.collides) {
			if (gs.key.enter)
				gs = new GameState(key, screen);
		}
	}
	
	/*
	########## GRAPHIC RENDERING ##########
	*/
	public void render() {
		
		BufferStrategy bs = getBufferStrategy();
		
		if (bs == null) {
			createBufferStrategy(3);
			return;
		} 
		
		screen.clear();
		gs.render();
		
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = screen.pixels[i];
		
		Graphics g = bs.getDrawGraphics();
	
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Points: " + gs.worm.points, 500, 20);
		
		if (gs.worm.collides) {
			g.drawString("GAME OVER!", 200, 180);
			g.drawString("Press Enter to retry.", 175, 200);
		}
		
		if (gs.sa.visible) {
			g.setColor(Color.RED);
			int rectwidth = (int)(gs.sa.timer - System.currentTimeMillis()) / 100;
			g.fillRect(500, 50, rectwidth, 20);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("WormerZ");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
	}
	
}
