package com.mpu.spinv.game.states;

import com.mpu.spinv.engine.model.State;
import com.mpu.spinv.game.states.gamemenu.Background;

/**
 * GameMenu.java
 * 
 * @author Brendon Pagano
 * @date 2017-09-30
 */
public class GameMenu extends State {

	// ---------------- Constants ----------------

	private final static boolean SAVE_RESOURCES = false;

	public final String SPRITESHEET_URL = "/resources/img/spritesheet.png";

	// -------------------------------------------
	
	private Background background;

	public GameMenu() {
		super(SAVE_RESOURCES);
		setSpriteSheetUrl(SPRITESHEET_URL);
	}
	
	@Override
	public void loadResources() {
		background = new Background();
		
		addResource("background", background);
	}

}
