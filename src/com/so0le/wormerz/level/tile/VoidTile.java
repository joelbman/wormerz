package com.so0le.wormerz.level.tile;

import com.so0le.wormerz.graphics.Screen;
import com.so0le.wormerz.graphics.Sprite;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
}
