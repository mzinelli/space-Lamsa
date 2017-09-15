package com.mpu.spinv.engine.model;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Group.java
 * 
 * @author Brendon Pagano
 * @date 2017-09-14
 */
public class Group implements GameObject {

	private List<GameEntity> gameEntities;
	
	// Rectangular shape information to make collision checks
	private int x, y,
				width, height;

	public Group() {
		gameEntities = new ArrayList<GameEntity>();
	}

	public void update() {
		gameEntities.forEach(go -> {
			go.update();
			
			// Updates the rectangular shape for collision detection of group.
			if (go.x < x)
				x = go.x;
			if (go.x + go.getWidth() > width)
				width = go.x + go.getWidth();
			if (go.y < y)
				y = go.y;
			if (go.y + go.getHeight() > height)
				height = go.y + go.getHeight();
		});
	}

	public void draw(Graphics g) {
		gameEntities.forEach(go -> {
			go.draw(g);
		});
	}

	@Override
	public void keyPressed(KeyEvent e) {
		gameEntities.forEach(g -> {
			if (g.hasKeyTriggers())
				keyPressed(e);
		});
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gameEntities.forEach(g -> {
			if (g.hasKeyTriggers())
				keyReleased(e);
		});
	}

	@Override
	public void keyTyped(KeyEvent e) {
		gameEntities.forEach(g -> {
			if (g.hasKeyTriggers())
				keyTyped(e);
		});
	}

	/**
	 * Return true if any of the GameEntities has key triggers attached to it.
	 */
	@Override
	public boolean hasKeyTriggers() {
		for (int i = 0; i < gameEntities.size(); i++)
			if (gameEntities.get(i).hasKeyTriggers())
				return true;
		return false;
	}

	/**
	 * Adds a GameEntity into the list.
	 * 
	 * @param go
	 *            the GameEntity to be added.
	 */
	public void add(GameEntity go) {
		gameEntities.add(go);
	}

	/**
	 * Removes an element from the list, given an index number.
	 * 
	 * @param i
	 *            the index number to extract the GameEntity
	 * @return the removed GameObject, or null in case of an error.
	 */
	public GameEntity removeAt(int i) {
		if (i < 0 || i > gameEntities.size() - 1)
			return null;
		else
			return gameEntities.remove(i);
	}

	/**
	 * Removes all the elements from the group.
	 */
	public void clear() {
		gameEntities.clear();
	}

	/**
	 * Retrieves the list of gameEntities.
	 * 
	 * @return the list of all the added GameEntity's of the group.
	 */
	public List<GameEntity> getGameObjects() {
		return gameEntities;
	}

}
