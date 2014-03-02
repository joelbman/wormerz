package com.so0le.wormerz.entity;

import com.so0le.wormerz.graphics.Screen;
import com.so0le.wormerz.graphics.Sprite;
import com.so0le.wormerz.level.Level;

public class Apple extends Entity {

	public Sprite sprite;
	
	public Apple(Level level) {
		this.sprite = Sprite.apple;
		this.level = level;
		do {
			this.x = (2 + rnd.nextInt(27 - 2 + 1)) << 3;
			this.y = (2 + rnd.nextInt(21 - 2 + 1)) << 3;
		} while (!canSpawn());
		level.EntityList.add(this);
	}
	
	public void render(Screen screen) {
		screen.renderApple(this.x, this.y, this.sprite);
	}
	
	public void update(WormPartHandler handler) {
		do {
			this.x = (2 + rnd.nextInt(27 - 2 + 1)) << 3;
			this.y = (2 + rnd.nextInt(21 - 2 + 1)) << 3;
		} while (!canSpawn());
	}
	
	private boolean canSpawn() {
		int found = 0;
		
		for (Entity e : this.level.EntityList) {
			if (this.x == e.x && this.y == e.y && e != this) {
				found = 1;
				break;
			}
		}
		if (found == 0) return true;
		else return false;
	}
	
}
