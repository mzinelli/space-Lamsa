package com.mpu.spinv.engine.model;

import java.awt.Color;
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
public class Group extends GameObject {

	// ---------------- Constants ----------------

	/**
	 * Every element of the Group will be displayed horizontally.
	 */
	public static final int LAYOUT_HORIZONTAL = 0;

	/**
	 * Every element of the Group will be displayed vertically.
	 */
	public static final int LAYOUT_VERTICAL = 1;

	/**
	 * Every element of the Group will be displayed in a grid of N rows by N
	 * columns. You must execute
	 */
	public static final int LAYOUT_GRID = 2;

	// -------------------------------------------

	/**
	 * The list of GameEntity's that the Group contains.
	 */
	private List<GameEntity> gameEntities;

	/**
	 * The margin applied to every {@link GameEntity} of the Group.
	 */
	private int spacingHorizontal, spacingVertical;

	/**
	 * The layout to display the group.
	 */
	private int layout;

	/**
	 * The number of rows the {@link Group#LAYOUT_GRID} will display.
	 */
	private int gridRows;

	/**
	 * The number of columns the {@link Group#LAYOUT_GRID} will display.
	 */
	private int gridCols;

	public Group(int x, int y, int layout) {
		super(x, y, 0, 0, true);

		this.layout = layout;

		// Default params
		this.gameEntities = new ArrayList<GameEntity>();
		this.spacingHorizontal = 0;
		this.spacingVertical = 0;
		this.gridRows = 0;
		this.gridCols = 0;
	}

	public void update() {
		int previousX = x, previousY = y;

		super.update();

		if (previousX != x || previousY != y) {
			int diffX = x - previousX;
			int diffY = y - previousY;
			
			gameEntities.forEach(go -> {
				go.setX(go.getX() + diffX);
				go.setY(go.getY() + diffY);
			});
		}
		
		gameEntities.forEach(go -> {
			go.update();
		});
	}

	public void draw(Graphics g) {
		if (visible) {
			gameEntities.forEach(go -> {
				go.draw(g);
			});
	
			if (Constants.SHOW_ENTITIES_BORDERS) {
				g.setColor(Color.GREEN);
				g.drawRect(x, y, width, height);
			}
		}
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

	@Override
	public void keyPressed(KeyEvent e) {
		gameEntities.forEach(g -> {
			if (g.hasKeyTriggers())
				g.keyPressed(e);
		});
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gameEntities.forEach(g -> {
			if (g.hasKeyTriggers())
				g.keyReleased(e);
		});
	}

	@Override
	public void keyTyped(KeyEvent e) {
		gameEntities.forEach(g -> {
			if (g.hasKeyTriggers())
				g.keyTyped(e);
		});
	}

	/**
	 * Adds a GameEntity into the list.
	 * 
	 * @param go
	 *            the GameEntity to be added.
	 */
	public void add(GameEntity go) {
		gameEntities.add(go);
		resetCoordinates();
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
	 * Set the size of the Group's grid. Only executes if layout is set to
	 * {@link Group#LAYOUT_GRID}.
	 * 
	 * @param rows
	 *            how many rows should the grid have.
	 * @param cols
	 *            how many columns should the grid have.
	 */
	public void setGridSize(int rows, int cols) {
		if (layout == Group.LAYOUT_GRID && rows > 1 && cols > 1) {
			gridRows = rows;
			gridCols = cols;
			resetCoordinates();
		}
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

	/**
	 * Center the Group both horizontally and vertically on screen.
	 */
	public void centerBothAxis() {
		x = Constants.WINDOW_WIDTH / 2 - width / 2 - 4;
		y = Constants.WINDOW_HEIGHT / 2 - height / 2 - 30;
		resetCoordinates();
	}

	private void resetCoordinates() {
		if (gameEntities.size() == 0)
			return;

		boolean isGrid = (layout == Group.LAYOUT_GRID);

		int highestWidth = 0, highestHeight = 0;
		int pivotX = x, pivotY = y;

		int highestColWidth = 0;

		width = 0;
		height = 0;

		for (int i = 0; i < gameEntities.size(); i++) {
			if (isGrid && i % gridCols == 0 && i > 0) {
				pivotY += highestHeight + (i > 0 ? spacingVertical : 0);
			}

			GameEntity actElem = gameEntities.get(i);
			int actWidth = actElem.getWidth();
			int actHeight = actElem.getHeight();

			if (i == 0 || (isGrid && i % gridCols == 0 && i > 0)) {
				actElem.x = pivotX;
				actElem.y = pivotY;

				highestWidth = actWidth;
				highestHeight = actHeight;
			} else {
				GameEntity lastElem = gameEntities.get(i - 1);
				actElem.x = lastElem.x
						+ ((layout == Group.LAYOUT_HORIZONTAL || isGrid) ? lastElem.getWidth() + spacingHorizontal : 0);
				actElem.y = lastElem.y + (layout == Group.LAYOUT_VERTICAL ? lastElem.getHeight() + spacingVertical : 0);

				if (actWidth > highestWidth)
					highestWidth = actWidth;
				if (actHeight > highestHeight)
					highestHeight = actHeight;
			}

			if (layout == Group.LAYOUT_HORIZONTAL)
				width += actWidth + (i > 0 ? spacingHorizontal : 0);
			else if (layout == Group.LAYOUT_VERTICAL)
				height += actHeight + spacingVertical;
			else if (layout == Group.LAYOUT_GRID) {
				width += actWidth + (i > 0 ? spacingHorizontal : 0);
				if (i % gridCols == 0) {
					height += highestHeight + spacingVertical;
					if (highestColWidth == 0)
						highestColWidth = width;
					else
						highestColWidth = (width > highestColWidth) ? width : highestColWidth;
					width = 0;
				}
			}

		}

		if (isGrid) {
			width = highestColWidth - spacingHorizontal;
			height -= spacingVertical;
		}

		if (width == 0 && layout == Group.LAYOUT_VERTICAL)
			width = highestWidth;

		if (height == 0 && layout == Group.LAYOUT_HORIZONTAL)
			height = highestHeight;
	}

	// Getters and Setters

	public void setX(int x) {
		this.x = x;
		resetCoordinates();
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

	public int getGridRows() {
		return gridRows;
	}

	public int getGridCols() {
		return gridCols;
	}

}
