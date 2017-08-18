package com.mpu.spinv.game.actors;

import com.mpu.spinv.engine.StateMachine;
import com.mpu.spinv.engine.model.Animation;
import com.mpu.spinv.engine.model.GameObject;
import com.mpu.spinv.engine.model.Sprite;

/**
 * Player.java
 * 
 * @author Brendon Pagano
 * @date 2017-18-08
 */
public class Player extends GameObject {

	// ---------------- Constants ----------------

	private static final int INITIAL_X = 0;
	private static final int INITIAL_Y = 0;

	private static final boolean INITIAL_VISIBILITY = true;

	// -------------------------------------------

	public Player() {
		super(INITIAL_X, INITIAL_Y, new Animation(new Sprite[] {
				new Sprite(StateMachine.spriteSheet.getSprite(0, 0, 32, 32)),
				new Sprite(StateMachine.spriteSheet.getSprite(32, 0, 32, 32)),
				new Sprite(StateMachine.spriteSheet.getSprite(64, 0, 32, 32))
		}, 20, Animation.ALTERNATIVE_LOOP, true), INITIAL_VISIBILITY);
	}
	
	@Override
	public void init() {
		startAnimation();
	}

}
