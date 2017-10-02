package com.mpu.spinv.game.states.gameplaystate;

import com.mpu.spinv.engine.StateMachine;
import com.mpu.spinv.engine.model.Animation;
import com.mpu.spinv.engine.model.GameEntity;
import com.mpu.spinv.engine.model.Group;
import com.mpu.spinv.engine.model.Sprite;
import com.mpu.spinv.utils.Constants;

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
	
	private static final int VELOCITY = 1;
	
	// -------------------------------------------
	
	public AlienGroup() {
		super(X, Y, Group.LAYOUT_GRID);
		
		setGridSize(5);
		setSpacing(10, 10);
		
		for (int i = 0; i < TOTAL_ALIENS; i++)
			add(new Alien());
		
		centerBothAxis();
		
		setVelocity(VELOCITY, VELOCITY);
		moveRight(true);
		
		/* Debug code to add more aliens
		on(new KeyTriggerEvent(KeyEvent.VK_F5, (k, t) -> {
			if (t == KeyTriggerEvent.KEY_RELEASED)
				for (int i = 0; i < TOTAL_ALIENS; i++)
					add(new Alien());
		}));
		*/
	}
	
	@Override
	public void update() {
		super.update();
		
		if (x + width > Constants.WINDOW_WIDTH - 4 - 10)
			moveLeft(true);
		else if (x < 10)
			moveRight(true);
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
		private Animation destroyAnimation;
		
		public Alien() {
			// Pass 0 and 0 to x and y params because the {@link Group} will reset it anyway
			super(0, 0, INITIAL_VISIBILITY);
			
			destroyAnimation = new Animation(new Sprite[] {
					new Sprite(StateMachine.spriteSheet.getSprite(0, 0, 96, 96)),
					new Sprite(StateMachine.spriteSheet.getSprite(96, 0, 96, 96)),
					new Sprite(StateMachine.spriteSheet.getSprite(192, 0, 96, 96)),
					new Sprite(StateMachine.spriteSheet.getSprite(288, 0, 96, 96)),
					new Sprite(StateMachine.spriteSheet.getSprite(384, 0, 96, 96)),
					new Sprite(StateMachine.spriteSheet.getSprite(480, 0, 96, 96)),
					new Sprite(StateMachine.spriteSheet.getSprite(576, 0, 96, 96)),
					new Sprite(StateMachine.spriteSheet.getSprite(672, 0, 96, 96)),
					new Sprite(StateMachine.spriteSheet.getSprite(768, 0, 96, 96)),
					new Sprite(StateMachine.spriteSheet.getSprite(864, 0, 96, 96)),
					new Sprite(StateMachine.spriteSheet.getSprite(960, 0, 96, 96)),
					new Sprite(StateMachine.spriteSheet.getSprite(1056, 0, 96, 96)),
			}, 3, Animation.NO_LOOP, true);
			addAnimation("death", destroyAnimation);
			setAnimation(null);
			
			sprite = new Sprite(StateMachine.spriteSheet.getSprite(423, 825, 93, 83));
			setStaticSprite(sprite);
			resizeSprite(WIDTH, HEIGHT);
		}
		
		@Override
		public void update() {
			super.update();
			
			if (!listenCollision && getActiveAnimation().hasEnded()) {
				super.die();
			}
		}
		
		@Override
		public void die() {
			listenCollision = false;
			setAnimation("death");
			startAnimation();
		}
	
	}

}
