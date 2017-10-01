package com.mpu.spinv.game.states.gameplaystate;

import java.awt.Color;
import java.awt.Graphics;

import com.mpu.spinv.engine.model.GameText;
import com.mpu.spinv.utils.Constants;

public class HelpText extends GameText {

	// ---------------- Constants ----------------

	private static final int X = 0;
	private static final int Y = 0;

	private static final boolean INITIAL_VISIBILITY = true;

	private static final String TEXT = "ESC - MENU" + "\n" +
									   "F1     - HELP";
	private static final Color TEXT_COLOR = Color.WHITE;

	// -------------------------------------------
	
	private boolean _firstTime = true;

	public HelpText() {
		super(X, Y, INITIAL_VISIBILITY, TEXT, TEXT_COLOR);
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		
		if (_firstTime) {
			setX(Constants.WINDOW_WIDTH - 4 - width - 10);
			setY(Constants.WINDOW_HEIGHT - 30 - height - 10);
			_firstTime = false;
		}
	}

}