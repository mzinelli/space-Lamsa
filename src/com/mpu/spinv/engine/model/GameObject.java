package com.mpu.spinv.engine.model;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * GameObject.java
 * 
 * @author Brendon Pagano
 * @date 2017-09-14
 */
public interface GameObject {

	public void update();
	
	public void draw(Graphics g);
	
	public boolean hasKeyTriggers();
	
	public void keyPressed(KeyEvent e);
	public void keyReleased(KeyEvent e);
	public void keyTyped(KeyEvent e);
	
	public boolean checkCollision(GameObject go);
	
	public int getX();
	public int getY();
	
	public int getWidth();
	public int getHeight();
	
}


