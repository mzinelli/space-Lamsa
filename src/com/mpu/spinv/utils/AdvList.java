package com.mpu.spinv.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * AdvList.java
 * 
 * @author Brendon Pagano
 * @date 2017-09-20
 */
public class AdvList<T> {

	/**
	 * The list containing the ids of the values.
	 */
	private List<String> idsList;

	/**
	 * The list containing the values of the ids.
	 */
	private List<T> valuesList;

	/**
	 * The list's size.
	 */
	private int size;

	private boolean overwriteValues;

	public AdvList() {
		idsList = new ArrayList<String>();
		valuesList = new ArrayList<T>();
		size = 0;

		// Default params.
		overwriteValues = false;
	}

	/**
	 * Adds an element to the list.
	 * 
	 * @param id
	 *            The identifier of the element to be added.
	 * @param value
	 *            The object to add to the list.
	 */
	public void add(String id, T value) {
		if (overwriteValues || !idExists(id)) {
			idsList.add(id);
			valuesList.add(value);
			size++;
		}
	}

	/**
	 * Removes a object from the list.
	 * 
	 * @param id
	 *            The identifier of the object to remove from the list.
	 * @return The object removed, null otherwise.
	 */
	public T remove(String id) {
		T removed = null;

		for (int i = 0; i < idsList.size(); i++) {
			if (id.equals(idsList.get(i))) {
				removed = valuesList.get(i);
				valuesList.remove(i);
				idsList.remove(i);
				size--;
			}
		}

		return removed;
	}
	
	/**
	 * Clears the list.
	 */
	public void clear() {
		idsList.clear();
		valuesList.clear();
	}

	/**
	 * Checks if an id exists in the list.
	 * 
	 * @param id
	 *            the id to be evaluated.
	 * @return true if the id is exists within the list, false otherwise.
	 */
	private boolean idExists(String id) {
		for (int i = 0; i < idsList.size(); i++)
			if (id.equals(idsList.get(i)))
				return true;

		return false;
	}

	// Getters and Setters

	public int size() {
		return size;
	}
	
	public T get(String id) {
		T toGet = null;
		
		for (int i = 0; i < idsList.size(); i++) {
			if (id.equals(idsList.get(i))) {
				toGet = valuesList.get(i);
				break;
			}
		}
		
		return toGet;
	}

	public boolean isOverwriteValues() {
		return overwriteValues;
	}

	public void setOverwriteValues(boolean overwriteValues) {
		this.overwriteValues = overwriteValues;
	}

}
