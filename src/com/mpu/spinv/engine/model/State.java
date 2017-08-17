package com.mpu.spinv.engine.model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class State {

	private List<GameObject> gameObjects;
	
	private boolean saveResources;
	
	public State(boolean saveResources) {
		gameObjects = new ArrayList<GameObject>();
		this.saveResources = saveResources;
	}

	/**
	 * Main update method.
	 */
	public void update() {
		gameObjects.forEach(obj -> obj.update());
	}

	/**
	 * Draws every game object, if visible, on screen.
	 * @param g
	 */
	public void draw(Graphics g) {
		gameObjects.forEach(obj -> obj.draw(g));
	}

	/**
	 * This method will be called just before playing the actual state on screen. It
	 * will load all resources needed by the state.
	 */
	public void loadResources() {
		
	}
	
	/**
	 * Kills the resources loaded by {@link State#loadResources()}, if allowed.
	 */
	public void killResources() {
		for (int i = gameObjects.size()-1; i >= 0; i--)
			gameObjects.remove(i);
	}
	
	/**
	 * Adds a GameObject into the {@link State#gameObjects}.
	 * @param obj A {@link GameObject} to be added.
	 */
	protected void addResource(GameObject obj) {
		gameObjects.add(obj);
	}
	
	// Getters and Setters

	public boolean isSaveResources() {
		return saveResources;
	}

	public void setSaveResources(boolean saveResources) {
		this.saveResources = saveResources;
	}

}
