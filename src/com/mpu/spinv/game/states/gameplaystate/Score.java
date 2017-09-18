package com.mpu.spinv.game.states.gameplaystate;

import java.awt.Color;
import java.awt.Graphics;

import com.mpu.spinv.engine.model.GameEntity;
import com.mpu.spinv.utils.Constants;

public class Score extends GameEntity {
	
	// ---------------- Constants ----------------
	
	private static final int X = Constants.WINDOW_WIDTH - 4 - 10;
	private static final int Y = 10;
	
	private static final boolean INITIAL_VISIBILITY = true;
	
	private static final String SCORE_TEXT = "SCORE: ";
	
	// -------------------------------------------
	
	/**
	 * The player's score.
	 */
	private int score = 10;
	
	/**
	 * The width and height of the score string.
	 */
	private int width, height;
	
	private boolean _scoreChanged = false;
	
	public Score() {
		super(X, Y, INITIAL_VISIBILITY);
	}
	
	@Override
	public void update() {}
	
	@Override
	public void draw(Graphics g) {
		if (width == 0 || _scoreChanged) {
			width = g.getFontMetrics().stringWidth(SCORE_TEXT + score);
			x = X - width;
		}
		if (height == 0) {
			height = g.getFontMetrics().getHeight();
			y = Y + height;
		}
		
		g.setColor(Color.WHITE);
		g.drawString(SCORE_TEXT + score, x, y);
	}
	
	// Getters and Setters
	
	public void setScore(int score) {
		this.score = score;
		_scoreChanged = true;
	}
	
	public int getScore() {
		return score;
	}

}
