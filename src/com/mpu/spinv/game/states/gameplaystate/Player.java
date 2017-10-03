package com.mpu.spinv.game.states.gameplaystate;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.mpu.spinv.engine.StateMachine;
import com.mpu.spinv.engine.model.GameEntity;
import com.mpu.spinv.engine.model.GameObject;
import com.mpu.spinv.engine.model.Sprite;
import com.mpu.spinv.engine.triggers.CollisionEvent;
import com.mpu.spinv.engine.triggers.KeyTriggerEvent;
import com.mpu.spinv.utils.Constants;

/**
 * Player.java
 * 
 * @author Brendon Pagano
 * @date 2017-18-08
 */
public class Player extends GameEntity {

	// ---------------- Constants ----------------
	
	private static final int WIDTH = 70;
	private static final int HEIGHT = 53;
	
	private static final int INITIAL_X = Constants.WINDOW_WIDTH / 2 - WIDTH / 2;
	private static final int INITIAL_Y = Constants.WINDOW_HEIGHT - HEIGHT - 40;

	private final int VELOCITY = 5;

	private static final boolean INITIAL_VISIBILITY = true;

	// -------------------------------------------
	
	/**
	 * The player sprite.
	 */
	private Sprite sprite;
	
	/**
	 * A reference to the score class.
	 */
	private Score score;

	public Player(Score score) {
		super(INITIAL_X, INITIAL_Y, INITIAL_VISIBILITY);
		
		this.score = score;
		
		sprite = new Sprite(StateMachine.spriteSheet.getSprite(224, 928, 99, 75));

		// Setting the player image.
		setStaticSprite(sprite);		
		resizeSprite(WIDTH, HEIGHT);
		
		setScreenBound(true);
		setVelocity(VELOCITY, VELOCITY);

		/**
		 * Setting the player movements triggers.
		 */

		// Moving up
		on(new KeyTriggerEvent(KeyEvent.VK_UP, (k, t) -> {
			if (t == KeyTriggerEvent.KEY_PRESSED)
				moveUp(true);
			else if (t == KeyTriggerEvent.KEY_RELEASED)
				moveUp(false);
		}));

		// Moving down
		on(new KeyTriggerEvent(KeyEvent.VK_DOWN, (k, t) -> {
			if (t == KeyTriggerEvent.KEY_PRESSED)
				moveDown(true);
			else if (t == KeyTriggerEvent.KEY_RELEASED)
				moveDown(false);
		}));

		// Moving right
		on(new KeyTriggerEvent(KeyEvent.VK_RIGHT, (k, t) -> {
			if (t == KeyTriggerEvent.KEY_PRESSED)
				moveRight(true);
			else if (t == KeyTriggerEvent.KEY_RELEASED)
				moveRight(false);
		}));

		// Moving left
		on(new KeyTriggerEvent(KeyEvent.VK_LEFT, (k, t) -> {
			if (t == KeyTriggerEvent.KEY_PRESSED)
				moveLeft(true);
			else if (t == KeyTriggerEvent.KEY_RELEASED)
				moveLeft(false);
		}));
		
		// Shoot
		on(new KeyTriggerEvent(KeyEvent.VK_SPACE, (k, t) -> {
			if (t == KeyTriggerEvent.KEY_RELEASED) {
				Shot shot = new Shot(x + getWidth() / 2, y);
				addChild(shot);
			}
		}));
	}
	
	@Override
	public void update() {
		super.update();
		
		for (int i = getChildren().size()-1; i >= 0; i--) {
			GameObject shot = getChild(i);
			
			if (shot.getY() + shot.getHeight() < 0) {
				removeChild(i);
				continue;
			} else if(shot.isDead()) {
				removeChild(i);
				continue;
			}
		}
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}
	
	private class Shot extends GameEntity {
		
		// ---------------- Constants ----------------
		
		private static final int SHOT_VELOCITY = 9;
		private static final boolean INITIAL_VISIBILITY = true;
		
		private static final int SHOT_WIDTH = 9;
		private static final int SHOT_HEIGHT = 54;
		
		// -------------------------------------------

		/**
		 * The shot's sprite.
		 */
		private Sprite sprite;
		
		public Shot(int x, int y) {
			super(x - SHOT_WIDTH /2, y, INITIAL_VISIBILITY);
			
			sprite = new Sprite(StateMachine.spriteSheet.getSprite(858, 326, SHOT_WIDTH, SHOT_HEIGHT));
			
			setStaticSprite(sprite);
			
			setVelocityY(SHOT_VELOCITY);
			moveUp(true);
			
			on(new CollisionEvent("alien-group", (go, i) -> {
				if (!go.isDead() ) {
					go.die();
					die();
					
					score.increment(Constants.ALIEN_SCORE);
				}
			}));
		}
		
	}

}
