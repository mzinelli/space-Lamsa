package com.mpu.spinv.engine.model;

import java.awt.image.BufferedImage;

/**
 * Sprite.java
 * 
 * @author Brendon Pagano
 * @date 2017-08-08
 */
public class Sprite {

	/**
	 * The sprite image do be drawn.
	 */
	private BufferedImage sprite;

	/**
	 * Width and height information about the sprite image.
	 */
	private int width, height;

	/**
	 * Sprite's class constructor.
	 * 
	 * @param sprite
	 *            An image of the sprite to be drawn on screen.
	 */
	public Sprite(BufferedImage sprite) {
		setSprite(sprite);
	}

	// Getters and Setters

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
