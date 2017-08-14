package com.mpu.spinv.engine.model;

import java.awt.image.BufferedImage;

/**
 * Animation.java
 * 
 * @author Brendon Pagano
 * @date 2017-08-09
 */
public class Animation {

	/**
	 * A vector containing all the sprites for the animation.
	 */
	private Sprite[] frames;

	/**
	 * The amount of ticks required to update the animation frame.
	 */
	private int updateRate;

	/**
	 * Ticks passed since the last frame update. Used for comparinsom between
	 * this and {@link Animation#updateRate} to update the animation frame.
	 */
	private int ticksPassed;

	/**
	 * The current animation frame (aka the index used to obtain the image from
	 * {@link Animation#frames vector}
	 */
	private int currentFrame;

	/**
	 * Used to update the {@link Animation#currentFrame}. Can be positive or
	 * negative. If direction is negative, the animation will play reversed.
	 */
	private int direction;

	/**
	 * Flag to mark wether the animation should start over when it finishes or
	 * not.
	 */
	private boolean loop;

	/**
	 * Flag to mark if the animation has stopped/finished.
	 */
	private boolean stopped;

	/**
	 * Animation's constructor.
	 * 
	 * @param frames
	 *            A vector of sprites, to serve as the animation frames.
	 * @param updateRate
	 *            A number corresponding to the number of times the game should
	 *            update/tick before the animation sprite image is
	 *            updated/changed.
	 * @param loop
	 *            True if the animation should start over after finished, false
	 *            otherwise.
	 * @param forward
	 *            True if the animation should play normally, false if reversed.
	 */
	public Animation(Sprite[] frames, int updateRate, boolean loop, boolean forward) {
		this.frames = frames;
		this.updateRate = updateRate;
		this.loop = loop;
		this.ticksPassed = 0;
		this.direction = forward ? 1 : -1;
		this.currentFrame = forward ? 0 : frames.length - 1;
		this.stopped = true;
	}

	/**
	 * Plays the animation. From start of from where it was left of (if you used
	 * pause).
	 */
	public void start() {
		if (frames.length > 0)
			stopped = false;
	}

	/**
	 * Pauses the animation. For playing it again, just call
	 * {@link Animation#start()}.
	 */
	public void pause() {
		if (frames.length > 0)
			stopped = true;
	}

	/**
	 * Resets and stops the animation.
	 */
	public void reset() {
		if (frames.length > 0) {
			stopped = true;
			currentFrame = direction > 0 ? 0 : frames.length - 1;
		}
	}

	/**
	 * Restart the animation, but doesn't stop it from playing.
	 */
	public void restart() {
		if (frames.length > 0)
			currentFrame = direction > 0 ? 0 : frames.length - 1;
	}

	/**
	 * Updates the animation.
	 */
	public void update() {
		if (!stopped) {
			ticksPassed++;
			if (ticksPassed > updateRate) {
				ticksPassed = 0;
				currentFrame += direction;

				// Check if the animation has ended.
				if (currentFrame >= frames.length || currentFrame < 0) {
					if (loop)
						restart();
					else
						stopped = true;
				}
			}
		}
	}

	public BufferedImage getSprite() {
		return frames[currentFrame].getSprite();
	}

}
