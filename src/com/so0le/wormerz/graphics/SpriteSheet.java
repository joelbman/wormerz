package com.so0le.wormerz.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	public int width, height;
	public int SIZE;
	public int pixels[];
	private String path;
	
	public static SpriteSheet tiles = new SpriteSheet(112, 16, "/textures/tiles.png");
	public static SpriteSheet apple = new SpriteSheet(24, 16, "/textures/apple.png");
	public static SpriteSheet worm = new SpriteSheet(32, 8, "/textures/worm.png");
	
	public SpriteSheet(int x, int y, String path) {
		this.width = x;
		this.height = y;
		this.SIZE = x * y;
		this.path = path;
		pixels = new int[SIZE * SIZE];
		load();
	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
