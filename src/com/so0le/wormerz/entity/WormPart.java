package com.so0le.wormerz.entity;

import com.so0le.wormerz.graphics.Screen;
import com.so0le.wormerz.graphics.Sprite;
import com.so0le.wormerz.level.Level;

public class WormPart extends Entity {
	
	public Sprite sprite;
	public int direction;
	
	public WormPart(int x, int y, int dir, Sprite sprite, Level level) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.direction = dir;
		level.EntityList.add(this);
	}
	
	public void render(Screen screen) {
		screen.renderWormPart(this.x, this.y, this.sprite);
	}
	
	public void render(Screen screen, int dir) {
		screen.renderWormHead(this.x, this.y, this.sprite, dir);
	}
	
}
