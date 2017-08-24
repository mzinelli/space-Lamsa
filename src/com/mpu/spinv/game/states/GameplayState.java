package com.mpu.spinv.game.states;

import java.awt.Graphics;

import com.mpu.spinv.engine.model.State;
import com.mpu.spinv.game.actors.Player;

/**
 * GameplayState.java
 * 
 * @author Brendon Pagano
 * @date 2017-18-08
 */
public class GameplayState extends State {

	// ---------------- Constants ----------------

	private final static boolean SAVE_RESOURCES = true;

	public final String SPRITESHEET_URL = "/resources/img/spritesheet.png";

	// -------------------------------------------

	public GameplayState() {
		super(SAVE_RESOURCES);
		setSpriteSheetUrl(SPRITESHEET_URL);
	}

	@Override
	public void loadResources() {
		addResource("player", new Player());
	}

}
