package com.mpu.spinv.game.actors;

import com.mpu.spinv.engine.model.GameObject;

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
	}

}
