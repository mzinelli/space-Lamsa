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
		super(INITIAL_X, INITIAL_Y, INITIAL_VISIBILITY);
		init();
	}

	@Override
	public void init() {
		setStaticSprite(new Sprite(StateMachine.spriteSheet.getSprite(0, 941, 111, 75)));
	}

	@Override
	public void load() {
		startAnimation();
	}

}
