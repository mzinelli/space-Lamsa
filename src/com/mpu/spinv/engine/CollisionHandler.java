package com.mpu.spinv.engine;

import com.mpu.spinv.engine.model.GameObject;

/**
 * CollisionHandler.java
 * 
 * @author Brendon Pagano
 * @date 2017-09-20
 */
public class CollisionHandler {

	public static boolean hasCollided(GameObject obj0, GameObject obj1) {
		// Intersects on the right edge of obj0.
		if (obj0.getX() < obj1.getX() + obj1.getWidth() &&
				obj0.getX() + obj0.getWidth() > obj1.getX() &&
				obj0.getY() + obj0.getHeight() > obj1.getY() &&
				obj0.getY() < obj1.getY() + obj1.getHeight()) {
			return true;
		}
		
		return false;
	}

}
