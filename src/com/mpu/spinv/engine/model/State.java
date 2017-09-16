package com.mpu.spinv.engine.model;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class State {

	private final List<GameObject> gameObjects;

	private String spriteSheetUrl;

	private boolean saveResources;

	public State(boolean saveResources) {
		gameObjects = new ArrayList<GameObject>();
		this.saveResources = saveResources;
	}

	/**
	 * Main update method.
	 */
	public void update() {
		gameObjects.forEach(go -> go.update());
	}

	/**
	 * Draws every game object, if visible, on screen.
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		gameObjects.forEach(go -> {
			go.draw(g);
		});
	}

	/**
	 * This method will be called just before playing the actual state on screen. It
	 * will load all resources needed by the state.
	 * 
	 * Must be extended in each state in order to function correctly.
	 */
	public void loadResources() {
	}

	/**
	 * Kills the resources loaded by {@link State#loadResources()}, if allowed.
	 */
	public void killResources() {
		if (!saveResources)
			for (int i = gameObjects.size() - 1; i >= 0; i--)
				gameObjects.remove(i);
	}

	// Controls Handling Methods.

	public void keyPressed(KeyEvent e) {
		gameObjects.forEach(go -> {
			if (go.hasKeyTriggers())
				go.keyPressed(e);
		});
	}

	public void keyReleased(KeyEvent e) {
		gameObjects.forEach(go -> {
			if (go.hasKeyTriggers())
				go.keyReleased(e);
		});
	}

	public void keyTyped(KeyEvent e) {
		gameObjects.forEach(go -> {
			if (go.hasKeyTriggers())
				go.keyTyped(e);
		});
	}

	/**
	 * Adds a GameObject into the {@link State#gameEntities}.
	 * 
	 * @param obj
	 *            A {@link GameEntity} to be added.
	 */
	protected void addResource(GameObject obj) {
		gameObjects.add(obj);
	}

	/**
	 * Removes a {@link GameEntity} from the state resources list.
	 * 
	 * @param obj
	 *            the reference of the object to remove.
	 */
	protected void removeResource(GameObject obj) {
		gameObjects.remove(obj);
	}

	// Getters and Setters

	public boolean isSaveResources() {
		return saveResources;
	}

	public void setSaveResources(boolean saveResources) {
		this.saveResources = saveResources;
	}

	public String getSpriteSheetUrl() {
		return spriteSheetUrl;
	}

	public void setSpriteSheetUrl(String spriteSheetUrl) {
		this.spriteSheetUrl = spriteSheetUrl;
	}

}
