package com.so0le.wormerz.entity;

import com.so0le.wormerz.graphics.Screen;
import com.so0le.wormerz.graphics.Sprite;
import com.so0le.wormerz.input.Keyboard;
import com.so0le.wormerz.level.Level;

public class Worm extends Entity {

	public int direction;
	private int nextDirection;
	public int points = 0;
	public long cooldown;
	
	public boolean collides;
	
	private long animtime;
	private int nextSuper;
	
	public Sprite sprite;
	private WormPartHandler handler;
	private Keyboard key;
	private Apple apple;
	public SuperApple sa;
	
	public Worm(int x, int y, Keyboard input, Level level) {
		this.x = x;
		this.y = y;
		this.key = input;
		this.sprite = Sprite.wormHead;
		this.cooldown = System.currentTimeMillis() + 100;
		this.direction = 3;
		this.nextDirection = 3;
		this.level = level;
		this.level.EntityList.addLast(this);
	}
	
	public void render(Screen screen) {
		screen.renderWormHead(this.x, this.y, this.sprite, this.direction);
	}
	
	public void setApple(Apple apple) {
		this.apple = apple;
	}
	
	public void setSuperApple(SuperApple sa) {
		this.sa = sa;
	}
	
	public void setHandler(WormPartHandler handler) {
		this.handler = handler;
	}
	
	private void move() {
		if (System.currentTimeMillis() > cooldown) {
			int oldx = this.x;
			int oldy = this.y;
			if (this.nextDirection == 0) { this.y -= 8; this.direction = 0; }
			if (this.nextDirection == 1) { this.x += 8; this.direction = 1; }
			if (this.nextDirection == 2) { this.y += 8; this.direction = 2; }
			if (this.nextDirection == 3) { this.x -= 8; this.direction = 3; }
			handler.update(oldx, oldy, this.direction);
			collision();
			cooldown = System.currentTimeMillis() + 100;
		}
	}
	
	public void update() {
		//Superapples.
		if (nextSuper == 5) {
			nextSuper = 0;
			sa.spawn();
		}
		
		//Animations.
		if (animtime < System.currentTimeMillis())
			this.sprite = Sprite.wormHead;
		
		//Input handling.
		if (key.up && direction != 2 && direction != 0) this.nextDirection = 0;
		if (key.right && direction != 3 && direction != 1) this.nextDirection = 1;
		if (key.down && direction != 0 && direction != 2) this.nextDirection = 2;
		if (key.left && direction != 1 && direction != 3) this.nextDirection = 3;
		move();
	}
	
	private void collision() {
		//Wall collision.
		if (this.x <= 15 || this.x >= 223 || this.y <= 15 || this.y >= 175) {
			collides = true;
		}
		//WormPart collision.
		for (int i = 0; i < handler.count; i++) {
			if (this.x == handler.wp[i].x && this.y == handler.wp[i].y)
				collides = true;
		}
		//Eating apples.
		if (this.x == apple.x && this.y == apple.y) {
			//Add seven points.
			points += 7;
			nextSuper++;
			
			//Eating animation, adding a new worm part and updating the apple.
			this.sprite = Sprite.wormHeadHappy;
			animtime = System.currentTimeMillis() + 200;
			handler.addPart(level);
			apple.update(handler);
		}
		
		if (sa.visible && sa.eat(this)) {
			//Add points based on speed.
			if (sa.timer - System.currentTimeMillis() > 3500) points += 20;
			else if (sa.timer - System.currentTimeMillis() > 3000) points += 18;
			else if (sa.timer - System.currentTimeMillis() > 2500) points += 17;
			else if (sa.timer - System.currentTimeMillis() > 2000) points += 15;
			else if (sa.timer - System.currentTimeMillis() > 1500) points += 12;
			else if (sa.timer - System.currentTimeMillis() > 1000) points += 11;
			else points += 10;
			
			//Eating animation, adding a new worm part and setting the superapple to non visible.
			this.sprite = Sprite.wormHeadHappy;
			animtime = System.currentTimeMillis() + 200;
			handler.addPart(level);
			sa.visible = false;
		}
		
	}
	
}
