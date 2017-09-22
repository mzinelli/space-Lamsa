package com.mpu.spinv.engine.model;

import java.awt.Color;
import java.awt.Graphics;

import com.mpu.spinv.utils.AdvList;
import com.mpu.spinv.utils.Constants;

/**
 * GameEntity.java
 * 
 * @author Brendon Pagano
 * @date 2017-08-08
 */
public class GameEntity extends GameObject {

	/**
	 * A list of the object's animations.
	 */
	private final AdvList<Animation> animations;

	/**
	 * The key identifier of the active animation.
	 */
	private String actAnimation;

	/**
	 * The active animation. Currently playing if {@link GameEntity#visible} is
	 * true.
	 */
	private Animation animation;

	/**
	 * In case there is no need for the object to have an animation, use a static
	 * sprite to draw instead.
	 */
	private Sprite staticSprite;

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
	public GameEntity(int x, int y, boolean visible) {
		super(x, y, 0, 0, visible);

		// Default params
		this.animations = new AdvList<Animation>();
		this.animation = null;
		this.actAnimation = "";
		this.staticSprite = null;
	}

	public GameEntity(int x, int y, Sprite staticSprite, boolean visible) {
		super(x, y, staticSprite.getWidth(), staticSprite.getHeight(), visible);

		this.staticSprite = staticSprite;

		// Default params
		this.animations = new AdvList<Animation>();
		this.animation = null;
		this.actAnimation = "";
	}

	public GameEntity(int x, int y, Animation animation, boolean visible) {
		super(x, y, animation.getSprite().getWidth(), animation.getSprite().getHeight(), visible);

		// Default params
		this.animations = new AdvList<Animation>();
		this.animation = null;
		this.actAnimation = "";
		this.staticSprite = null;

		// Setting the default animation
		addAnimation("default", animation);
	}

	public void update() {
		super.update();

		if (!actAnimation.equals(""))
			animation.update();
	}

	public void draw(Graphics g) {
		if (visible && (animation != null || staticSprite != null)) {
			g.drawImage((staticSprite == null ? animation.getSprite() : staticSprite.getSprite()), x, y, null);
			if (Constants.SHOW_ENTITIES_BORDERS) {
				g.setColor(Color.GREEN);
				g.drawRect(x, y, width, height);
			}
		}
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
		if (!key.equals("")) {
			animations.add(key, animation);
			if (this.actAnimation.equals("")) {
				this.actAnimation = key;
				this.animation = animation;
			}
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
		} else if (key.equals("")) {
			actAnimation = key;
			animation = null;
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

	public void resizeSprite(int width, int height) {
		this.width = width;
		this.height = height;
		staticSprite.resizeSprite(width, height);
	}

	// Getters and Setters

	public String getAnimationKey() {
		return actAnimation;
	}

	public Sprite getStaticSprite() {
		return staticSprite;
	}

	public void setStaticSprite(Sprite staticSprite) {
		this.staticSprite = staticSprite;
		this.width = staticSprite.getWidth();
		this.height = staticSprite.getHeight();
	}

}
