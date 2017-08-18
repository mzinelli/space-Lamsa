package com.mpu.spinv.engine.model;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

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
	 * A list of the object's animations.
	 */
	private final Map<String, Animation> animations;

	/**
	 * The key identifier of the active animation.
	 */
	private String actAnimation;

	/**
	 * The active animation. Currently playing if {@link GameObject#visible} is
	 * true.
	 */
	private Animation animation;

	/**
	 * GameObject's size.
	 */
	private int width, height;

	/**
	 * Attribute used for checking. Mainly for drawing or not the object
	 * accordingly to it's visibility status.
	 */
	private boolean visible;

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
		this.animations = new HashMap<String, Animation>();
		this.animation = null;
		this.actAnimation = "";
	}

	public GameObject(int x, int y, Animation animation, boolean visible) {
		this.x = x;
		this.y = y;
		this.visible = visible;

		// Default params
		this.dx = 0;
		this.dy = 0;
		this.animations = new HashMap<String, Animation>();
		this.animation = null;
		this.actAnimation = "";

		// Setting the default animation
		addAnimation("default", animation);
	}

	/**
	 * This method will be called every time a state has been loaded.
	 */
	public void init() {
	}

	public void update() {
		x += dx;
		y += dy;

		if (!actAnimation.equals(""))
			animation.update();
	}

	public void draw(Graphics g) {
		if (visible && animation != null)
			g.drawImage(animation.getSprite(), x, y, null);
	}

	/**
	 * Adds an animation into the GameObject's animations list.
	 * 
	 * @param key
	 *            the identifier of the animation to add.
	 * @param animation
	 *            the animation object to be added.
	 */
	public void addAnimation(String key, Animation animation) {
		animations.put(key, animation);
		if (this.actAnimation.equals("")) {
			this.actAnimation = key;
			this.animation = animation;
		}
	}

	/**
	 * Set the active and playing animation to a new one.
	 * 
	 * @param key
	 *            the identifier of the animation to be set.
	 */
	public void setAnimation(String key) {
		if (animations.containsKey(key)) {
			actAnimation = key;
			animation = animations.get(key);
		}
	}

	/**
	 * Removes an animation from the GameObject's animation list and returns the
	 * removed animation object.
	 * 
	 * @param key
	 *            the key identifier of the animation to be removed.
	 * @return the removed animation object.
	 */
	public Animation removeAnimation(String key) {
		Animation animation = null;
		if (animations.containsKey(key)) {
			animation = animations.get(key);
			animations.remove(key);
			if (actAnimation.equals(key)) {
				this.animation = null;
				this.actAnimation = "";
			}
		}
		return animation;
	}
	
	/**
	 * Starts playing the active animation.
	 */
	public void startAnimation() {
		if (animation != null)
			animation.start();
	}
	
	/**
	 * Pauses the active animation.
	 */
	public void pauseAnimation() {
		if (animation != null)
			animation.pause();
	}
	
	/**
	 * Restarts the active animation.
	 */
	public void restartAnimation() {
		if (animation != null)
			animation.restart();
	}
	
	/**
	 * Resets the active animation.
	 */
	public void resetAnimation() {
		if (animation != null)
			animation.reset();
	}

	// Getters and Setters

	public String getAnimationKey() {
		return actAnimation;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
