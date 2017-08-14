package com.mpu.spinv.engine.model;

import java.awt.Graphics;

/**
 * GameObject.java
 * 
 * @author Brendon Pagano
 * @date 2017-08-08
 */
public class GameObject {

	/**
	 * The x and y position of the object in the screen.
	 */
	protected int x, y;

	/**
	 * The direction to update x and y axis.
	 */
	protected int dx, dy;

	/**
	 * GameObject's size.
	 */
	protected int width, height;

	/**
	 * Attribute used for checking. Mainly for drawing or not the object
	 * accordingly to it's visibility status.
	 */
	protected boolean visible;

	/**
	 * GameObject's constructor.
	 * 
	 * @param x
	 *            The x position of the object in the screen.
	 * @param y
	 *            The y position of the object in the screen.
	 * @param visible
	 *            Flag to determine if the object will begin visible or not.
	 */
	public GameObject(int x, int y, boolean visible) {
		this.x = x;
		this.y = y;
		this.visible = visible;

		// Default params
		this.dx = 0;
		this.dy = 0;
	}

	// public GameObject(int x, int y, Sprite sprite, boolean visible) {}

	public void update() {
		x += dx;
		y += dy;
	}

	public void draw(Graphics g) {

	}

}
