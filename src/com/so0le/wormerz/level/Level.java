package com.so0le.wormerz.level;

import java.util.LinkedList;

import com.so0le.wormerz.entity.Entity;
import com.so0le.wormerz.graphics.Screen;
import com.so0le.wormerz.level.tile.Tile;

public class Level {

	public final int width = 15;
	public final int height = 12;
	
	public int widthEight;
	public int heightEight;
	
	public int tiles[];
	public int tilesEight[];
	
	public LinkedList<Entity> EntityList = new LinkedList<Entity>();
	
	public Level() {
		
		widthEight = this.width << 1;
		heightEight = this.width << 1;
		
		tiles = new int[this.width * this.height];
		tilesEight = new int[widthEight * heightEight];
		
		for (int i = 0; i < tiles.length; i++)
			tiles[i] = 0;
			
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (x == 0 || x == width-1)
					tiles[x + y * width] = 2;
				if (y == 0 || y == height-1)
					tiles[x + y * width] = 1;
			}
		}

		tiles[0] = 3;
		tiles[width-1] = 4;
		tiles[width * height - width] = 5;
		tiles[height * width-1] = 6;
		
	}
	
	public void render(int x, int y, Screen screen) {
		
		int x0 = x >> 4;
		int x1 = (x + screen.width + 16) >> 4;
		int y0 = y >> 4;
		int y1 = (y + screen.height + 16) >> 4;
		
		for (int yi = y0; yi < y1; yi++) {
			for (int xi = x0; xi < x1; xi++) {
				getTile(xi, yi).render(xi, yi, screen);
			}
		}
			
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) { return Tile.empty; }
		else if (tiles[x + y * width] == 0) { return Tile.sand; }
		else if (tiles[x + y * width] == 1) { return Tile.wallX; }
		else if (tiles[x + y * width] == 2) { return Tile.wallY; }
		else if (tiles[x + y * width] == 3) { return Tile.wallNW; }
		else if (tiles[x + y * width] == 4) { return Tile.wallNE; }
		else if (tiles[x + y * width] == 5) { return Tile.wallSW; }
		else if (tiles[x + y * width] == 6) { return Tile.wallSE; }
		else { return Tile.empty; }
	}
	
}
