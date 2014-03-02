package com.so0le.wormerz.graphics;

public class Sprite {

	private int x, y;
	public int SIZE;
	public int pixels[];
	private SpriteSheet sheet;
	
	public static Sprite sand = new Sprite(0, 0, 16, SpriteSheet.tiles);
	
	public static Sprite wallX = new Sprite(1, 0, 16, SpriteSheet.tiles);
	public static Sprite wallY = new Sprite(2, 0, 16, SpriteSheet.tiles);
	public static Sprite wallNW = new Sprite(3, 0, 16, SpriteSheet.tiles);
	public static Sprite wallNE = new Sprite(4, 0, 16, SpriteSheet.tiles);
	public static Sprite wallSW = new Sprite(5, 0, 16, SpriteSheet.tiles);
	public static Sprite wallSE = new Sprite(6, 0, 16, SpriteSheet.tiles);
	
	public static Sprite empty = new Sprite(16, 0x364A21);
	
	public static Sprite wormHead = new Sprite(0, 0, 8, SpriteSheet.worm);
	public static Sprite wormTail = new Sprite(1, 0, 8, SpriteSheet.worm);
	public static Sprite wormHeadHappy = new Sprite(2, 0, 8, SpriteSheet.worm);
	public static Sprite wormHeadHurt = new Sprite(3, 0, 8, SpriteSheet.worm);
	public static Sprite wormPart = new Sprite(8, 0xFFA782);
	
	public static Sprite apple = new Sprite(2, 0, 8, SpriteSheet.apple);
	public static Sprite superApple = new Sprite(0, 0, 16, SpriteSheet.apple);
	
	public Sprite(int x, int y, int size, SpriteSheet sheet) {
		this.x = x;
		this.y = y;
		this.SIZE = size;
		this.sheet = sheet;
		
		pixels = new int[SIZE * SIZE];
		
		load();
	}
	
	public Sprite(int size, int color) {
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		setColor(color);
	}
	
	private void setColor(int color) {
		for (int i = 0; i < SIZE*SIZE; i++) {
			pixels[i] = color;
		}
	}
	
	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x+this.x*SIZE) + (y+this.y*SIZE) * sheet.width];
			}
		}
	}
	
}
