package com.so0le.wormerz.graphics;

import com.so0le.wormerz.level.tile.Tile;

public class Screen {

	public int width, height;
	public int pixels[];
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = 0;
	}
	
	public void renderTile(int xp, int yp, Tile tile) {
		
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = yp + y;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = xp + x;
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) { break; }
				if (xa < 0) { xa = 0; }
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
		
	}
	
	public void renderWormHead(int xp, int yp, Sprite sprite, int dir) {
		int pix;
		for (int y = 0; y < sprite.SIZE; y++) {
			int ya = yp + y;
			int ys = y;
			if (dir == 2) ys = 7 - y;
			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = xp + x;
				int xs = x;
				if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) { break; }
				if (xa < 0) { xa = 0; }
				if (dir == 1) xs = 7 - x;
				if (dir == 3 || dir == 1)
					pix = sprite.pixels[ys + xs * sprite.SIZE];
				else
					pix = sprite.pixels[x + ys * sprite.SIZE];
				if (pix != 0xFFFF00FF)
					pixels[xa + ya * width] = pix;
			}
		}
	}
	
	public void renderWormPart(int xp, int yp, Sprite sprite) {
		for (int y = 0; y < sprite.SIZE; y++) {
			int ya = yp + y;
			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = xp + x;
				if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) { break; }
				if (xa < 0) { xa = 0; }
				pixels[xa + ya * width] = sprite.pixels[x + y * sprite.SIZE];
			}
		}
	}

	public void renderApple(int xp, int yp, Sprite sprite) {
		for (int y = 0; y < sprite.SIZE; y++) {
			int ya = yp + y;
			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = xp + x;
				if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) { break; }
				if (xa < 0) { xa = 0; }
				int col = sprite.pixels[x + y * sprite.SIZE];
				if (col != 0xFFFF00FF)
					pixels[xa + ya * width] = sprite.pixels[x + y * sprite.SIZE];
			}
		}
	}
	
}
