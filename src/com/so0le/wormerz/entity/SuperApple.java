package com.so0le.wormerz.entity;

import com.so0le.wormerz.graphics.Screen;
import com.so0le.wormerz.graphics.Sprite;
import com.so0le.wormerz.level.Level;

public class SuperApple extends Entity {
	
	public Sprite sprite;
	
	public long timer;
	public boolean visible = false;
	
	public SuperApple(Level level, Sprite sprite) {
		this.sprite = sprite;
		this.level = level;
		
		//Check that it doesn't actually spawn on something else.
		do {
			this.x = (2 + rnd.nextInt(26 - 2 + 1)) << 3;
			this.y = (2 + rnd.nextInt(20 - 2 + 1)) << 3;
		} while (!canSpawn());
		
		level.EntityList.add(this);
	}
	
	public void spawn() {
		do {
			this.x = (2 + rnd.nextInt(26 - 2 + 1)) << 3;
			this.y = (2 + rnd.nextInt(20 - 2 + 1)) << 3;
		} while (!canSpawn());
		this.visible = true;
		this.timer = System.currentTimeMillis() + 4000;
	}
	
	public boolean eat(Worm worm) {
		if (worm.x == this.x && worm.y == this.y) return true;
		if (worm.x == (this.x + 8) && worm.y == this.y) return true;
		if (worm.x == this.x && worm.y == (this.y + 8)) return true;
		if (worm.x == (this.x + 8) && worm.y == (this.y + 8)) return true;
		return false;
	}
	
	private boolean canSpawn() {
		int found = 0;
		
		for (Entity e : this.level.EntityList) {
			if (this.x == e.x && this.y == e.y && e != this) {
				found = 1;
				break;
			}
			if (this.x == e.x && this.y + 8 == e.y && e != this) {
				found = 1;
				break;
			}
			if (this.x + 8 == e.x && this.y == e.y && e != this) {
				found = 1;
				break;
			}
			if (this.x + 8 == e.x && this.y + 8 == e.y && e != this) {
				found = 1;
				break;
			}
		}
		if (found == 0) return true;
		else return false;
	}
	
	public void update() {
		if (this.timer < System.currentTimeMillis()) {
			this.visible = false;
		}
	}
	
	public void render(Screen screen) {
		screen.renderApple(this.x, this.y, this.sprite);
	}
	
}
