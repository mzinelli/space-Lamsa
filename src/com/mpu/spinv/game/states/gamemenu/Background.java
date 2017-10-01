package com.mpu.spinv.game.states.gamemenu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.KeyEvent;

import com.mpu.spinv.engine.StateMachine;
import com.mpu.spinv.engine.model.GameEntity;
import com.mpu.spinv.engine.model.Sprite;
import com.mpu.spinv.engine.triggers.KeyTriggerEvent;
import com.mpu.spinv.utils.Constants;

/**
 * Background.java
 * 
 * @author Brendon Pagano
 * @date 2017-09-30
 */
public class Background extends GameEntity {

	// ---------------- Constants ----------------

	private static final int INITIAL_X = 0;
	private static final int INITIAL_Y = 0;

	private static final boolean INITIAL_VISIBILITY = true;

	// -------------------------------------------
	
	private TexturePaint pattern;
	private Sprite sprite;
	
	public Background() {
		super(INITIAL_X, INITIAL_Y, INITIAL_VISIBILITY);
		
		sprite = new Sprite(StateMachine.spriteSheet.getSprite(874, 97, 56, 56));
		pattern = new TexturePaint(sprite.getSprite(), new Rectangle(INITIAL_X, INITIAL_Y, sprite.getWidth(), sprite.getHeight()));
		
		on(new KeyTriggerEvent(KeyEvent.VK_ESCAPE, (k, t) -> {
			if (t == KeyTriggerEvent.KEY_RELEASED)
				StateMachine.setActiveState("gameplay");
		}));
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(pattern);
		g2d.fill(new Rectangle(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
	}

}
