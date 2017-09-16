package com.mpu.spinv.engine.model;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mpu.spinv.engine.triggers.KeyTrigger;
import com.mpu.spinv.utils.Constants;

/**
 * GameObject.java
 * 
 * @author Brendon Pagano
 * @date 2017-08-08
 */
public class GameEntity implements GameObject {

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
	private int width, height;

	/**
	 * A list of the object's animations.
	 */
	private final Map<String, Animation> animations;

	/**
	 * A list of the key triggers of the object.
	 */
	private final List<KeyTrigger> keyTriggers;

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
	 * If true, the object cannot be moved out of the screen.
	 */
	private boolean screenBound;

	/**
	 * Attribute used for checking. Mainly for drawing or not the object accordingly
	 * to it's visibility status.
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
	public GameEntity(int x, int y, boolean visible) {
		this.x = x;
		this.y = y;
		this.visible = visible;

		// Default params
		this.dx = 0;
		this.dy = 0;
		this.width = 0;
		this.height = 0;
		this.animations = new HashMap<String, Animation>();
		this.keyTriggers = new ArrayList<KeyTrigger>();
		this.animation = null;
		this.actAnimation = "";
		this.staticSprite = null;
	}

	public GameEntity(int x, int y, Sprite staticSprite, boolean visible) {
		this.x = x;
		this.y = y;
		this.visible = visible;

		this.staticSprite = staticSprite;
		this.width = staticSprite.getWidth();
		this.height = staticSprite.getHeight();

		// Default params
		this.dx = 0;
		this.dy = 0;
		this.animations = new HashMap<String, Animation>();
		this.keyTriggers = new ArrayList<KeyTrigger>();
		this.animation = null;
		this.actAnimation = "";
	}

	public GameEntity(int x, int y, Animation animation, boolean visible) {
		this.x = x;
		this.y = y;
		this.visible = visible;
		
		this.width = animation.getSprite().getWidth();
		this.height = animation.getSprite().getHeight();

		// Default params
		this.dx = 0;
		this.dy = 0;
		this.animations = new HashMap<String, Animation>();
		this.keyTriggers = new ArrayList<KeyTrigger>();
		this.animation = null;
		this.actAnimation = "";
		this.staticSprite = null;

		// Setting the default animation
		addAnimation("default", animation);
	}

	public void update() {
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

		if (!actAnimation.equals(""))
			animation.update();
	}

	public void draw(Graphics g) {
		if (visible && (animation != null || staticSprite != null))
			g.drawImage((staticSprite == null ? animation.getSprite() : staticSprite.getSprite()), x, y, null);
	}

	public void on(KeyTrigger trigger) {
		keyTriggers.add(trigger);
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
			animations.put(key, animation);
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

	// Controls Handling Methods

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

	public String getAnimationKey() {
		return actAnimation;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Sprite getStaticSprite() {
		return staticSprite;
	}

	public void setStaticSprite(Sprite staticSprite) {
		this.staticSprite = staticSprite;
		this.width = staticSprite.getWidth();
		this.height = staticSprite.getHeight();
	}

	public boolean hasKeyTriggers() {
		return keyTriggers.size() > 0;
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

}
