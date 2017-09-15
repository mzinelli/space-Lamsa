package com.mpu.spinv.game.states.gameplaystate;

import java.awt.event.KeyEvent;

import com.mpu.spinv.engine.StateMachine;
import com.mpu.spinv.engine.model.GameEntity;
import com.mpu.spinv.engine.model.Sprite;
import com.mpu.spinv.engine.triggers.KeyTrigger;

/**
 * Player.java
 * 
 * @author Brendon Pagano
 * @date 2017-18-08
 */
public class Player extends GameEntity {

	// ---------------- Constants ----------------

	private static final int INITIAL_X = 0;
	private static final int INITIAL_Y = 0;

	private final int VELOCITY = 5;

	private static final boolean INITIAL_VISIBILITY = true;

	// -------------------------------------------

	public Player() {
		super(INITIAL_X, INITIAL_Y, INITIAL_VISIBILITY);

		// Setting the player image.
		setStaticSprite(new Sprite(StateMachine.spriteSheet.getSprite(224, 832, 99, 75)));
		setScreenBound(true);

		/**
		 * Setting the player movements triggers.
		 */

		// Moving up
		on(new KeyTrigger(KeyEvent.VK_UP, t -> {
			if (t == KeyTrigger.KEY_PRESSED)
				dy = -VELOCITY;
			else if (t == KeyTrigger.KEY_RELEASED)
				dy = 0;
		}));

		// Moving down
		on(new KeyTrigger(KeyEvent.VK_DOWN, t -> {
			if (t == KeyTrigger.KEY_PRESSED)
				dy = VELOCITY;
			else if (t == KeyTrigger.KEY_RELEASED)
				dy = 0;
		}));

		// Moving right
		on(new KeyTrigger(KeyEvent.VK_RIGHT, t -> {
			if (t == KeyTrigger.KEY_PRESSED)
				dx = VELOCITY;
			else if (t == KeyTrigger.KEY_RELEASED)
				dx = 0;
		}));

		// Moving left
		on(new KeyTrigger(KeyEvent.VK_LEFT, t -> {
			if (t == KeyTrigger.KEY_PRESSED)
				dx = -VELOCITY;
			else if (t == KeyTrigger.KEY_RELEASED)
				dx = 0;
		}));
	}

}
