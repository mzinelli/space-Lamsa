package com.mpu.spinv.game.states.gameplaystate;

import com.mpu.spinv.engine.model.GameEntity;
import com.mpu.spinv.utils.Constants;

/**
 * Boss.java
 * 
 * @author Brendon Pagano
 * @date 2017-12-15
 */
public class Boss extends GameEntity {
	
	// ---------------- Constants ----------------
	
	private static final int WIDTH = 00;
	private static final int HEIGHT = 00;
	
	private static final int INITIAL_X = Constants.WINDOW_WIDTH / 2 - WIDTH / 2;
	private static final int INITIAL_Y = Constants.WINDOW_WIDTH / 2 - WIDTH / 2;
	
	private final int VELOCITY = 5;
	
	private static final boolean INITIAL_VISIBILITY = false;
	
	public Boss() {
		super(INITIAL_X, INITIAL_Y, INITIAL_VISIBILITY);
	}

}
