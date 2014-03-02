package com.so0le.wormerz.entity;

import java.util.Random;

import com.so0le.wormerz.level.Level;


public abstract class Entity {
	
	public int x, y;
	protected Level level;
	protected Random rnd = new Random();
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
}