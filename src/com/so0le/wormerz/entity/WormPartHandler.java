package com.so0le.wormerz.entity;

import com.so0le.wormerz.graphics.Screen;
import com.so0le.wormerz.graphics.Sprite;
import com.so0le.wormerz.level.Level;

public class WormPartHandler {

	private WormPart wpart;
	private Worm worm;
	public int count;
	
	public WormPart[] wp = new WormPart[300];
	
	public WormPartHandler(Worm worm, Level level) {
		this.worm = worm;
		addPart(level);
		this.count = 1;
	}
		
	public void render(Screen screen) {
		for (int i = 0; i < count; i++) {
			if (i == count-1) {
				wp[i].sprite = Sprite.wormTail;
				wp[i].render(screen, wp[i].direction);
			}
			else {
				wp[i].sprite = Sprite.wormPart;
				wp[i].render(screen);
			}
		}
	}
	
	public void update(int oldx, int oldy, int olddir) {
		if (count > 0) {
			int tempx = wp[0].x;
			int tempy = wp[0].y;
			int tempdir = wp[0].direction;
			wp[0].x = oldx;
			wp[0].y = oldy;
			wp[0].direction = olddir;
			for (int i = 1; i < count; i++) {
				int tempx2 = wp[i].x;
				int tempy2 = wp[i].y;
				int tempdir2 = wp[i].direction;
				wp[i].x = tempx;
				wp[i].y = tempy;
				wp[i].direction = tempdir;
				tempx = tempx2;
				tempy = tempy2;
				tempdir = tempdir2;
			}
		}
	}
	
	public void addPart(Level level) {
		int x, y, dir;
		if (count == 0) {
			x = worm.x;
			y = worm.y;
			dir = worm.direction;
		}
		else {
			x = wp[count-1].x;
			y = wp[count-1].y;
			dir = wp[count-1].direction;
		}

		wpart = new WormPart(x, y, dir, Sprite.wormPart, level);
		wp[count] = wpart;
		count++;
	}
	
}
