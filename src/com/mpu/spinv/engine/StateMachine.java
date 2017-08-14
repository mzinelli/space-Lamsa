package com.mpu.spinv.engine;

import java.awt.Graphics;
import java.util.Hashtable;

import com.mpu.spinv.engine.model.State;

public class StateMachine {

	private Hashtable<String, State> states;
	private State state;
	private String actState;

	public StateMachine() {
		states = new Hashtable<String, State>();
		actState = "";
		state = null;
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
	 * Adds a {@link State} object into the {@link StateMachine#states}
	 * Hashtable.
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
				actState = key;
				this.state = state;
			}
		}
	}

	/**
	 * Removes a {@link State} from the Hashtable.
	 * 
	 * @param key
	 *            The key identifier of the {@link State} to be removed.
	 */
	public void removeState(String key) {
		if (states.containsKey(key)) {
			states.remove(key);
			if (actState.equals(key)) {
				state = null;
				actState = "";
			}
		}
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
