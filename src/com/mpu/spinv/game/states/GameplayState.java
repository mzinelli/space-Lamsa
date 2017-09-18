package com.mpu.spinv.game.states;

import com.mpu.spinv.engine.model.State;
import com.mpu.spinv.game.states.gameplaystate.AlienGroup;
import com.mpu.spinv.game.states.gameplaystate.Background;
import com.mpu.spinv.game.states.gameplaystate.LifeBar;
import com.mpu.spinv.game.states.gameplaystate.Player;
import com.mpu.spinv.game.states.gameplaystate.Score;

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
	
	private Background background;
	
	private LifeBar lifebar;
	private Score score;
	
	private Player player;
	private AlienGroup alienGroup;

	public GameplayState() {
		super(SAVE_RESOURCES);
		setSpriteSheetUrl(SPRITESHEET_URL);
	}

	@Override
	public void loadResources() {
		background = new Background();
		lifebar = new LifeBar();
		score = new Score();
		player = new Player();
		alienGroup = new AlienGroup();

		addResource(background);
		addResource(player);
		addResource(alienGroup);
		addResource(lifebar);
		addResource(score);
	}

}
