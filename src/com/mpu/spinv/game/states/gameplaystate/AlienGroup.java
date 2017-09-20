package com.mpu.spinv.game.states.gameplaystate;

import com.mpu.spinv.engine.StateMachine;
import com.mpu.spinv.engine.model.GameEntity;
import com.mpu.spinv.engine.model.Group;
import com.mpu.spinv.engine.model.Sprite;

/**
 * AlienGroup.java
 * 
 * @author Brendon Pagano
 * @date 2017-09-17
 */
public class AlienGroup extends Group {
	
	// ---------------- Constants ----------------
	
	private static final int X = 50;
	private static final int Y = 50;
	
	private static final int TOTAL_ALIENS = 10;
	
	// -------------------------------------------
	
	public AlienGroup() {
		super(X, Y, Group.LAYOUT_GRID);
		
		setGridSize(2, 5);
		
		for (int i = 0; i < TOTAL_ALIENS; i++)
			add(new Alien());
		
		centerBothAxis();
	}

	private class Alien extends GameEntity {
		
		// ---------------- Constants ----------------
		
		private static final int WIDTH = 60;
		private static final int HEIGHT = 54;
		
		private static final boolean INITIAL_VISIBILITY = true;
		
		// -------------------------------------------
		
		/**
		 * The alien's sprite.
		 */
		private Sprite sprite;
		
		public Alien() {
			// Pass 0 and 0 to x and y params because the {@link Group} will reset it anyway
			super(0, 0, INITIAL_VISIBILITY);
			
			sprite = new Sprite(StateMachine.spriteSheet.getSprite(423, 729, 93, 83));
			
			setStaticSprite(sprite);
			resizeSprite(WIDTH, HEIGHT);
		} 
	
	}

}
