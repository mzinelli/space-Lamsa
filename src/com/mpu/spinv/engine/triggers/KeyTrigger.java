package com.mpu.spinv.engine.triggers;

import java.awt.event.KeyEvent;

import com.mpu.spinv.engine.model.KeyTriggerEvent;

/**
 * KeyTrigger.java
 * 
 * @author Brendon Pagano
 * @date 2017-09-14
 */
public class KeyTrigger {

	// ---------------- Constants ----------------

	/**
	 * The trigger will be activated when key is pressed.
	 */
	public static final int KEY_PRESSED = 0;

	/**
	 * The trigger will be activated when the key is released.
	 */
	public static final int KEY_RELEASED = 1;

	/**
	 * The trigger will be activated when the key is typed.
	 */
	public static final int KEY_TYPED = 2;

	// -------------------------------------------

	/**
	 * The code of the condition key to run the event. To get the keyCode of a key,
	 * use: {@link KeyEvent#getKeyCode()}.
	 */
	private int keyCode;

	/**
	 * The event to be run once the trigger condition has been satisfied.
	 */
	private KeyTriggerEvent event;

	/**
	 * A object that will run a given event when the determined key is pressed.
	 * 
	 * @param event
	 */
	public KeyTrigger(int keyCode, KeyTriggerEvent event) {
		this.keyCode = keyCode;
		this.event = event;
	}

	/**
	 * Called everytime a key is pressed. If all the conditions are met, the linked
	 * event is executed.
	 * 
	 * @param e
	 *            the key that was pressed.
	 * @param triggerType
	 *            the type of key event that happened. Whether pressed, released or
	 *            typed.
	 */
	public void update(KeyEvent e, int triggerType) {
		if (keyCode == e.getKeyCode())
			event.run(triggerType);
	}

	// Getters and Setters

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}

	public KeyTriggerEvent getEvent() {
		return event;
	}

	public void setEvent(KeyTriggerEvent event) {
		this.event = event;
	}

}
