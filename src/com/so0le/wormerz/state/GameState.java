package com.so0le.wormerz.state;

import com.so0le.wormerz.entity.Apple;
import com.so0le.wormerz.entity.SuperApple;
import com.so0le.wormerz.entity.Worm;
import com.so0le.wormerz.entity.WormPartHandler;
import com.so0le.wormerz.graphics.Screen;
import com.so0le.wormerz.graphics.Sprite;
import com.so0le.wormerz.input.Keyboard;
import com.so0le.wormerz.level.Level;

public class GameState extends State {

	public Worm worm;
	public SuperApple sa;
	private Level level;
	private Apple apple;
	private WormPartHandler handler;
	
	public GameState(Keyboard key, Screen screen) {
		this.key = key;
		this.screen = screen;
		this.level = new Level();
		this.worm = new Worm(112, 96, key, this.level);
		this.apple = new Apple(this.level);
		this.handler = new WormPartHandler(worm, level);
		this.sa = new SuperApple(this.level, Sprite.superApple);
		worm.setApple(apple);
		worm.setSuperApple(sa);
		worm.setHandler(handler);
	}
	
	public void update() {
		if (worm.collides)
			worm.sprite = Sprite.wormHeadHurt;
		else
			worm.update();
		if (sa.visible)
			sa.update();
		key.update();
	}
	
	public void render() {
		level.render(0, 0, screen);
		handler.render(screen);
		worm.render(screen);
		apple.render(screen);
		if (sa.visible)
			sa.render(screen);
	}
	
}
