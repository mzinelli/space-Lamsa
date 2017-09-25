package com.mpu.spinv.game.menu;

import java.awt.Color;
import java.awt.Graphics;

import com.mpu.spinv.engine.model.Event;
import com.mpu.spinv.engine.model.GameEntity;

public class MenuItem extends GameEntity {
	
	// ---------------- Constants ----------------
	
	public static final int WIDTH = 400;
	public static final int HEIGHT = 40;
	
	// -------------------------------------------
	
	private int x, y;

	/**
	 * The text that the MenuItem displays.
	 */
	private String content;

	/**
	 * An event to be run once the MenuItem has been selected and activated.
	 */
	private Event<Integer> event;

	public MenuItem(int x, int y, String content, Event<Integer> event) {
		super(x, y, true);
		this.content = content;
		this.event = event;
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, WIDTH, HEIGHT);
		
		g.setColor(Color.BLACK);
		g.drawString(content, x + (WIDTH / 2 - g.getFontMetrics().stringWidth(content) / 2), y + HEIGHT / 2 + g.getFontMetrics().getHeight() / 4);
	}

}
