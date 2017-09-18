package com.mpu.spinv.engine.model;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import com.mpu.spinv.utils.Constants;

/**
 * Group.java
 * 
 * @author Brendon Pagano
 * @date 2017-09-14
 */
public class Group implements GameObject {
	
	// ---------------- Constants ----------------
	
	/**
	 * Every element of the Group will be displayed horizontally.
	 */
	public static final int LAYOUT_HORIZONTAL = 0;
	
	/**
	 * Every element of the Group will be displayed vertically.
	 */
	public static final int LAYOUT_VERTICAL = 1;
	
	// -------------------------------------------

	/**
	 * The list of GameEntity's that the Group contains.
	 */
	private List<GameEntity> gameEntities;

	/**
	 * Coordinates of the pivot element of the Group.
	 */
	private int x, y;

	/**
	 * The width and height of the Group.
	 */
	private int width, height;

	/**
	 * The margin applied to every {@link GameEntity} of the Group.
	 */
	private int spacingHorizontal, spacingVertical;
	
	/**
	 * The layout to display the group.
	 */
	private int layout;

	public Group(int x, int y, int layout) {
		this.x = x;
		this.y = y;
		this.layout = layout;
		
		// Default params
		this.gameEntities = new ArrayList<GameEntity>();
		this.spacingHorizontal = 0;
		this.spacingVertical = 0;
		width = 0;
		height = 0;
	}

	public void update() {
		gameEntities.forEach(go -> {
			go.update();
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
	 * Adds a GameEntity into the list.
	 * 
	 * @param go
	 *            the GameEntity to be added.
	 */
	public void add(GameEntity go) {
		// Configurates the coordinates of the element to add.
		if (gameEntities.size() == 0) {
			go.x = x + spacingHorizontal;
			go.y = y + spacingVertical;
		} else {
			GameEntity lastElem = gameEntities.get(gameEntities.size()-1);
			go.x = lastElem.x + (layout == Group.LAYOUT_HORIZONTAL ? lastElem.getWidth() : 0) + spacingHorizontal;
			go.y = lastElem.y + (layout == Group.LAYOUT_VERTICAL ? lastElem.getHeight() : 0) + spacingVertical;
		}
		
		if (layout == Group.LAYOUT_HORIZONTAL || width == 0)
			width += go.getWidth() + spacingHorizontal;
		
		if (layout == Group.LAYOUT_VERTICAL || height == 0)
			height += go.getHeight() + spacingVertical;
		
		gameEntities.add(go);
	}

	/**
	 * Removes an element from the list, given an index number.
	 * 
	 * @param i
	 *            the index number to extract the GameEntity
	 * @return the removed GameEntity, or null in case of an error.
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
	 * Center the Group on screen horizontally. 
	 */
	public void centerHorizontalAxis() {
		x = Constants.WINDOW_WIDTH / 2 - width / 2 - 4;
		resetCoordinates();
	}
	
	/**
	 * Center the Group on screen vertically.
	 */
	public void centerVerticalAxis() {
		y = Constants.WINDOW_HEIGHT / 2 - height / 2 - 30;
		resetCoordinates();
	}
	
	public void centerBothAxis() {
		x = Constants.WINDOW_WIDTH / 2 - width / 2 - 4;
		y = Constants.WINDOW_HEIGHT / 2 - height / 2 - 30;
		resetCoordinates();
	}
	
	private void resetCoordinates() {
		width = 0;
		height = 0;
		for (int i = 0; i < gameEntities.size(); i++) {
			if (i == 0) {
				gameEntities.get(i).x = x + spacingHorizontal;
				gameEntities.get(i).y = y + spacingVertical;
			} else {
				GameEntity lastElem = gameEntities.get(i-1);
				gameEntities.get(i).x = lastElem.x + (layout == Group.LAYOUT_HORIZONTAL ? lastElem.getWidth() : 0) + spacingHorizontal;
				gameEntities.get(i).y = lastElem.y + (layout == Group.LAYOUT_VERTICAL ? lastElem.getHeight() : 0) + spacingVertical;
			}
			
			if (layout == Group.LAYOUT_HORIZONTAL)
				width += gameEntities.get(i).getWidth() + spacingHorizontal;
			else if (layout == Group.LAYOUT_VERTICAL)
				height += gameEntities.get(i).getHeight() + spacingVertical;
		}
		
		if (width == 0 && !(layout == Group.LAYOUT_HORIZONTAL) && gameEntities.size() > 0)
			width = gameEntities.get(0).getWidth();
		
		if (height == 0 && !(layout == Group.LAYOUT_VERTICAL) && gameEntities.size() > 0)
			height = gameEntities.get(0).getHeight();
	}

	// Getters and Setters

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		resetCoordinates();
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		resetCoordinates();
	}

	/**
	 * A shorthand for setting up all the elements spacing.
	 * 
	 * @param top
	 *            the margin from the top.
	 * @param right
	 *            the margin from the right.
	 * @param bottom
	 *            the margin from the bottom.
	 * @param left
	 *            the margin from the left.
	 */
	public void setSpacing(int horizontal, int vertical) {
		this.spacingHorizontal = horizontal;
		this.spacingVertical = vertical;
		
		resetCoordinates();
	}

	public int getSpacingHorizontal() {
		return spacingHorizontal;
	}

	public void setSpacingHorizontal(int spacingHorizontal) {
		this.spacingHorizontal = spacingHorizontal;
		resetCoordinates();
	}

	public int getSpacingVertical() {
		return spacingVertical;
	}

	public void setSpacingVertical(int spacingVertical) {
		this.spacingVertical = spacingVertical;
		resetCoordinates();
	}

	/**
	 * Retrieves the list of gameEntities.
	 * 
	 * @return the list of all the added GameEntity's of the group.
	 */
	public List<GameEntity> getGameEntities() {
		return gameEntities;
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

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
