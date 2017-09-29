package com.mpu.spinv.engine.model;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 * GameText.java
 * 
 * @author Brendon Pagano
 * @date 2017-09-29
 */
public class GameText extends GameObject {

	/**
	 * The text content of the Object.
	 */
	protected String text;

	/**
	 * The text color to be painted. Default is {@link Color#BLACK}.
	 */
	private Color color;

	/**
	 * A flag to determine if the content has changed or not. If it has, when the
	 * {@link GameText#draw(Graphics)} is next called, the width and height of the
	 * GameObject will be altered.
	 */
	private boolean _changed;

	public GameText(int x, int y, boolean visible) {
		this(x, y, visible, "");
	}

	public GameText(int x, int y, boolean visible, String text) {
		super(x, y, 0, 0, visible);
		this.text = text;

		// Default params
		_changed = true;
		color = Color.BLACK;
	}

	@Override
	public boolean isGroup() {
		return false;
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics g) {
		if (_changed) {
			width = g.getFontMetrics().stringWidth(text);
			height = g.getFontMetrics().getHeight();
			_changed = false;
		}

		g.setColor(color);
		g.drawString(text, x, y + height);
	}

	// Getters and Setters

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
