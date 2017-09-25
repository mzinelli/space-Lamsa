package com.mpu.spinv.game.states.gameplaystate;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import com.mpu.spinv.engine.StateMachine;
import com.mpu.spinv.engine.model.GameEntity;
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
	 * A list of the shots on screen.
	 */
	private List<Shot> shots;
	
	/**
	 * A reference to the score class.
	 */
	private Score score;

	public Player(Score score) {
		super(INITIAL_X, INITIAL_Y, INITIAL_VISIBILITY);
		
		this.score = score;
		
		sprite = new Sprite(StateMachine.spriteSheet.getSprite(224, 832, 99, 75));
		shots = new ArrayList<Shot>();

		// Setting the player image.
		setStaticSprite(sprite);		
		resizeSprite(WIDTH, HEIGHT);
		
		setScreenBound(true);

		/**
		 * Setting the player movements triggers.
		 */

		// Moving up
		on(new KeyTriggerEvent(KeyEvent.VK_UP, (k, t) -> {
			if (t == KeyTriggerEvent.KEY_PRESSED)
				dy = -VELOCITY;
			else if (t == KeyTriggerEvent.KEY_RELEASED)
				dy = 0;
		}));

		// Moving down
		on(new KeyTriggerEvent(KeyEvent.VK_DOWN, (k, t) -> {
			if (t == KeyTriggerEvent.KEY_PRESSED)
				dy = VELOCITY;
			else if (t == KeyTriggerEvent.KEY_RELEASED)
				dy = 0;
		}));

		// Moving right
		on(new KeyTriggerEvent(KeyEvent.VK_RIGHT, (k, t) -> {
			if (t == KeyTriggerEvent.KEY_PRESSED)
				dx = VELOCITY;
			else if (t == KeyTriggerEvent.KEY_RELEASED)
				dx = 0;
		}));

		// Moving left
		on(new KeyTriggerEvent(KeyEvent.VK_LEFT, (k, t) -> {
			if (t == KeyTriggerEvent.KEY_PRESSED)
				dx = -VELOCITY;
			else if (t == KeyTriggerEvent.KEY_RELEASED)
				dx = 0;
		}));
		
		// Shoot
		on(new KeyTriggerEvent(KeyEvent.VK_SPACE, (k, t) -> {
			if (t == KeyTriggerEvent.KEY_RELEASED) {
				Shot shot = new Shot(x + getWidth() / 2, y);
				shots.add(shot);
				addChild(shot);
			}
		}));
	}
	
	@Override
	public void update() {
		super.update();
		
		for (int i = shots.size()-1; i >= 0; i--) {
			Shot shot = shots.get(i);
			
			if (shot.getY() + shot.getHeight() < 0) {
				shots.remove(i);
				removeChild(i);
				continue;
			} else if(shot.isDead()) {
				shots.remove(i);
				removeChild(i);
				continue;
			}
			
			shot.update();
		}
	}
	
	@Override
	public void draw(Graphics g) {
		shots.forEach(shot -> shot.draw(g));
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
			
			sprite = new Sprite(StateMachine.spriteSheet.getSprite(858, 230, SHOT_WIDTH, SHOT_HEIGHT));
			
			setStaticSprite(sprite);
			
			dy = -SHOT_VELOCITY;
			
			on(new CollisionEvent("alien-group", (go, i) -> {
				go.die();
				die();
				
				score.increment(Constants.ALIEN_SCORE);
			}));
		}
		
	}

}
