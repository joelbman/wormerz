package com.so0le.wormerz.state;

import com.so0le.wormerz.graphics.Screen;
import com.so0le.wormerz.input.Keyboard;

public abstract class State {
	
	protected Screen screen;
	public Keyboard key;

	abstract void update();
	abstract void render();
	
}
