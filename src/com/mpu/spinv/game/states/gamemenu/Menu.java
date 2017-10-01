package com.mpu.spinv.game.states.gamemenu;

import java.awt.event.KeyEvent;

import com.mpu.spinv.engine.StateMachine;
import com.mpu.spinv.engine.model.Group;
import com.mpu.spinv.engine.triggers.KeyTriggerEvent;
import com.mpu.spinv.game.menu.MenuItem;

/**
 * Menu.java
 * 
 * @author Brendon Pagano
 * @date 2017-09-30
 */
public class Menu extends Group {

	// ---------------- Constants ----------------

	private static final int X = 0;
	private static final int Y = 0;
	
	private static final int LAYOUT = Group.LAYOUT_VERTICAL;

	// -------------------------------------------
	
	private int i = 0;

	public Menu() {
		super(X, Y, LAYOUT);
		
		setSpacingVertical(10);
		
		add(new MenuItem(0, 0, "CONTINUAR", (i, j) -> {
			StateMachine.setActiveState("gameplay");
		}, true));
		
		add(new MenuItem(0, 0, "AJUDA", (i, j) -> {
			// TODO
		}));
		
		add(new MenuItem(0, 0, "SAIR", (i, j) -> {
			System.exit(0);
		}));
		
		centerBothAxis();
		
		on(new KeyTriggerEvent(KeyEvent.VK_UP, (k, t) -> {
			if (t == KeyTriggerEvent.KEY_RELEASED && i > 0) {
				MenuItem sel = (MenuItem) get(i);
				sel.setSelected(false);
				i--;
				sel = (MenuItem) get(i);
				sel.setSelected(true);
			}
		}));
		
		on(new KeyTriggerEvent(KeyEvent.VK_DOWN, (k, t) -> {
			if (t == KeyTriggerEvent.KEY_RELEASED && i < getGameEntities().size() - 1) {
				MenuItem sel = (MenuItem) get(i);
				sel.setSelected(false);
				i++;
				sel = (MenuItem) get(i);
				sel.setSelected(true);
			}
		}));
	}

}
