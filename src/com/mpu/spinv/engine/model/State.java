package com.mpu.spinv.engine.model;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

public class State {

	private final Map<String, GameObject> gameObjects;

	private boolean saveResources;

	public State(boolean saveResources) {
		gameObjects = new HashMap<String, GameObject>();
		this.saveResources = saveResources;
	}

	/**
	 * Main update method.
	 */
	public void update() {
		for (GameObject go : gameObjects.values())
			go.update();
	}

	/**
	 * Draws every game object, if visible, on screen.
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		for (GameObject go : gameObjects.values())
			go.draw(g);
	}

	/**
	 * This method will be called just before playing the actual state on
	 * screen. It will load all resources needed by the state.
	 * 
	 * Must be extended in each state in order to function correctly.
	 */
	public void loadResources() {
	}

	/**
	 * Called immediately after {@link State#loadResources()}. Calls the
	 * {@link GameObject#init()} method of every registered game object of the
	 * state.
	 */
	public void initResources() {
		for (GameObject go : gameObjects.values())
			go.init();
	}

	/**
	 * Kills the resources loaded by {@link State#loadResources()}, if allowed.
	 */
	public void killResources() {
		if (!saveResources)
			for (String key : gameObjects.keySet())
				gameObjects.remove(key);
	}

	/**
	 * Adds a GameObject into the {@link State#gameObjects}.
	 * 
	 * @param obj
	 *            A {@link GameObject} to be added.
	 */
	protected void addResource(String key, GameObject obj) {
		gameObjects.put(key, obj);
	}

	/**
	 * Removes a {@link GameObject} from the state resources list.
	 * 
	 * @param key
	 *            the identifier of the object to remove.
	 * @return the removed object.
	 */
	protected GameObject removeResource(String key) {
		return gameObjects.remove(key);
	}

	// Getters and Setters

	public boolean isSaveResources() {
		return saveResources;
	}

	public void setSaveResources(boolean saveResources) {
		this.saveResources = saveResources;
	}

}
