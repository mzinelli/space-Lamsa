package com.mpu.spinv.engine.model;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import com.mpu.spinv.engine.triggers.KeyTrigger;
import com.mpu.spinv.utils.AdvList;
import com.mpu.spinv.utils.Constants;

/**
 * GameObject.java
 * 
 * @author Brendon Pagano
 * @date 2017-09-14
 */
public abstract class GameObject {
	
	/**
	 * The x and y position of the object in the screen.
	 */
	protected int x, y;
	
	/**
	 * The direction to update x and y axis.
	 */
	protected int dx, dy;
	
	/**
	 * The width and height of the game object.
	 */
	protected int width, height;
	
	/**
	 * Attribute used for checking. Mainly for drawing or not the object accordingly
	 * to it's visibility status.
	 */
	protected boolean visible;
	
	/**
	 * If true, the object cannot be moved out of the screen.
	 */
	protected boolean screenBound;
	
	/**
	 * A flag to determine if the game entity should pass through the state
	 * collision detection or not.
	 */
	protected boolean detectCollision;

	/**
	 * This will be true as long as the object is collided with another one.
	 */
	protected boolean collided;

	/**
	 * If true, the object will not move to a place where it will become collided.
	 */
	protected boolean cantMoveCollided;

	/**
	 * An event to be fired once the object has collided.
	 */
	protected Event onCollisionEvent;

	/**
	 * A list mapping events to keys. So the state can compare the id of the
	 * collided objects.
	 */
	protected AdvList<Event> onCollisionWithEvents;
	
	/**
	 * A list of the key triggers of the object.
	 */
	protected List<KeyTrigger> keyTriggers;
	
	public abstract void draw(Graphics g);
	
	public GameObject(int x, int y, int width, int height, boolean visible) {
		this.x = x; 
		this.y = y;
		this.width = width;
		this.height = height;
		this.visible = visible;
		
		// Default params
		this.keyTriggers = new ArrayList<KeyTrigger>();
		this.onCollisionWithEvents = new AdvList<Event>();
		this.dx = 0;
		this.dy = 0;
		this.screenBound = false;
		this.onCollisionEvent = null;
	}

	public void update() {
		if (collided && cantMoveCollided)
			return;
		
		x += dx;
		y += dy;
		
		if (screenBound) {
			if (x < 0)
				x = 0;
			else if (x + width > Constants.WINDOW_WIDTH - 4)
				x = Constants.WINDOW_WIDTH - width - 4;

			if (y < 0)
				y = 0;
			else if (y + height > Constants.WINDOW_HEIGHT - 30)
				y = Constants.WINDOW_HEIGHT - height - 30;
		}
	}
	
	// Controls Manager methods
	
	public boolean hasKeyTriggers() {
		return keyTriggers.size() > 0;
	}
	
	public void keyPressed(KeyEvent e) {
		if (keyTriggers.size() > 0)
			keyTriggers.forEach(kt -> {
				kt.update(e, KeyTrigger.KEY_PRESSED);
			});
	}
	
	public void keyReleased(KeyEvent e) {
		if (keyTriggers.size() > 0)
			keyTriggers.forEach(kt -> {
				kt.update(e, KeyTrigger.KEY_RELEASED);
			});
	}

	public void keyTyped(KeyEvent e) {
		if (keyTriggers.size() > 0)
			keyTriggers.forEach(kt -> {
				kt.update(e, KeyTrigger.KEY_TYPED);
			});
	}
	
	// Getters and Setters
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isScreenBound() {
		return screenBound;
	}

	public void setScreenBound(boolean screenBound) {
		this.screenBound = screenBound;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
}


