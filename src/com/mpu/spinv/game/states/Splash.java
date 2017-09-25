package com.mpu.spinv.game.states;

import java.util.ArrayList;
import java.util.List;

import com.mpu.spinv.engine.model.State;
import com.mpu.spinv.game.menu.MenuItem;

public class Splash extends State {

	// ---------------- Constants ----------------

	private final static boolean SAVE_RESOURCES = false;

	// -------------------------------------------
	
	private List<MenuItem> list;

	public Splash() {
		super(SAVE_RESOURCES);
		setSpriteSheetUrl(null);
		list = new ArrayList<MenuItem>();
		
		list.add(new MenuItem(0, 0, "Texto", (x, y) -> {
			
		}));
	}
	
	@Override
	public void loadResources() {
		for (int i = 0; i < list.size(); i++)
			addResource("mItem-" + i, list.get(i));
	}

}
