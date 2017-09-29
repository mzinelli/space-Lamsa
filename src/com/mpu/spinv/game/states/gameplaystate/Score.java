package com.mpu.spinv.game.states.gameplaystate;

import java.awt.Color;
import java.awt.Graphics;

import com.mpu.spinv.engine.model.GameText;
import com.mpu.spinv.utils.Constants;

public class Score extends GameText {
	
	// ---------------- Constants ----------------
	
	private static final int X = Constants.WINDOW_WIDTH - 4 - 10;
	private static final int Y = 4;
	
	private static final boolean INITIAL_VISIBILITY = true;
	
	private static final String SCORE_TEXT = "SCORE: 0000";
	
	// -------------------------------------------
	
	/**
	 * The player's score.
	 */
	private int score = 0;
	
	private boolean _firstTime = true;
	
	public Score() {
		super(X, Y, INITIAL_VISIBILITY, SCORE_TEXT);
		
		setColor(Color.WHITE);
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		
		if (_firstTime) {
			setX(Constants.WINDOW_WIDTH - 4 - width - 10);
			_firstTime = false;
		}
	}
	
	// Getters and Setters
	
	public void increment(int n) {
		score += n;
		setText(SCORE_TEXT + score);
	}
	
	public void decrement(int n) {
		score -= n;
		setText(SCORE_TEXT + score);
	}
	
	public int getScore() {
		return score;
	}

}
