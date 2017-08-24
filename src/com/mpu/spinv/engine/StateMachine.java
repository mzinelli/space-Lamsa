package com.mpu.spinv.engine;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import com.mpu.spinv.engine.model.SpriteSheet;
import com.mpu.spinv.engine.model.State;
import com.mpu.spinv.game.states.GameplayState;

public class StateMachine {

	private final Map<String, State> states;
	private State state;
	private String actState;

	public static SpriteSheet spriteSheet;

	public StateMachine() {
		states = new HashMap<String, State>();
		actState = "";
		state = null;

		spriteSheet = new SpriteSheet();

		addState("gameplay", new GameplayState());
	}

	public void update() {
		if (state != null)
			state.update();
	}

	public void draw(Graphics g) {
		if (state != null)
			state.draw(g);
	}

	/**
	 * Adds a {@link State} object into the {@link StateMachine#states} Hashmap.
	 * 
	 * @param key
	 *            The key identifier of the {@link State} to be added.
	 * @param state
	 *            The {@link State} to be added.
	 */
	public void addState(String key, State state) {
		if (!states.containsKey(key)) {
			states.put(key, state);

			// If no state has been added yet, make it active.
			if (actState.equals("")) {
				setActiveState(key);
			}
		}
	}

	/**
	 * Removes a {@link State} from the Hashmap.
	 * 
	 * @param key
	 *            The key identifier of the {@link State} to be removed.
	 */
	public State removeState(String key) {
		State state = null;
		if (states.containsKey(key)) {
			state = states.get(key);
			states.remove(key);
			if (actState.equals(key)) {
				this.state = null;
				this.actState = "";
			}
		}
		return state;
	}

	/**
	 * Sets the active {@link State} of the game.
	 * 
	 * @param key
	 *            The state's key identifier.
	 */
	public void setActiveState(String key) {
		if (states.containsKey(key)) {
			actState = key;
			state = states.get(key);

			// Sets the active spritesheet.
			spriteSheet.setSpriteSheetImage(StateMachine.class.getResource(state.getSpriteSheetUrl()));

			state.loadResources();
			state.initResources();
		}
	}

	/**
	 * Return the active {@link State}.
	 * 
	 * @return The active {@link State} object.
	 */
	public State getActiveState() {
		if (!actState.equalsIgnoreCase(""))
			return state;
		else
			return null;
	}

	/**
	 * Retrieves a given {@link State} by his key identifier.
	 * 
	 * @param key
	 *            The key identifier of the {@link State} to be retrieved.
	 * @return A {@link State} if the passed key exists, null otherwise.
	 */
	public State getState(String key) {
		if (states.containsKey(key))
			return states.get(key);
		else
			return null;
	}

}
