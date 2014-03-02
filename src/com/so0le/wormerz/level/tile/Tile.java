package com.so0le.wormerz.level.tile;

import com.so0le.wormerz.graphics.Screen;
import com.so0le.wormerz.graphics.Sprite;

public class Tile {

	public Sprite sprite;
	
	public static Tile sand = new SandTile(Sprite.sand);
	
	public static Tile wallX = new WallTile(Sprite.wallX);
	public static Tile wallY = new WallTile(Sprite.wallY);
	public static Tile wallNW = new WallTile(Sprite.wallNW);
	public static Tile wallNE = new WallTile(Sprite.wallNE);
	public static Tile wallSW = new WallTile(Sprite.wallSW);
	public static Tile wallSE = new WallTile(Sprite.wallSE);
	
	public static Tile empty = new VoidTile(Sprite.empty);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) { /* Overrode by subclasses */ }
	
	public boolean solid() {
		return false;
	}
	
	
}
