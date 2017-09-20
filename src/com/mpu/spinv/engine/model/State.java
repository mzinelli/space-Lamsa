package com.mpu.spinv.engine.model;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.mpu.spinv.utils.AdvList;

public class State {

	private final AdvList<GameObject> gameObjects;

	private String spriteSheetUrl;

	private boolean saveResources;

	public State(boolean saveResources) {
		gameObjects = new AdvList<GameObject>();
		this.saveResources = saveResources;
	}

	/**
	 * Main update method.
	 */
	public void update() {
		gameObjects.forEach((k, v) -> v.update());
	}

	/**
	 * Draws every game object, if visible, on screen.
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		gameObjects.forEach((k, v) -> {
			v.draw(g);
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
			gameObjects.clear();
	}

	// Controls Handling Methods.

	public void keyPressed(KeyEvent e) {
		gameObjects.forEach((k, v) -> {
			if (v.hasKeyTriggers())
				v.keyPressed(e);
		});
	}

	public void keyReleased(KeyEvent e) {
		gameObjects.forEach((k, v) -> {
			if (v.hasKeyTriggers())
				v.keyReleased(e);
		});
	}

	public void keyTyped(KeyEvent e) {
		gameObjects.forEach((k, v) -> {
			if (v.hasKeyTriggers())
				v.keyTyped(e);
		});
	}

	/**
	 * Adds a GameObject into the {@link State#gameEntities}.
	 * 
	 * @param obj
	 *            A {@link GameEntity} to be added.
	 */
	protected void addResource(String key, GameObject obj) {
		gameObjects.add(key, obj);
	}

	/**
	 * Removes a {@link GameEntity} from the state resources list.
	 * 
	 * @param obj
	 *            the reference of the object to remove.
	 */
	protected void removeResource(String key) {
		gameObjects.remove(key);
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
