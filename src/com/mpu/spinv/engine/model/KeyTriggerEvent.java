package com.mpu.spinv.engine.model;

/**
 * Event.java
 * 
 * An interface with a single method to be created using lambda expressions.
 * 
 * @author Brendon Pagano
 * @date 2017-09-14
 */
public interface KeyTriggerEvent {

	/**
	 * The method to be run once the event has been called.
	 */
	public void run(int triggerType);

}
