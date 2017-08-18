package com.mpu.spinv;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.mpu.spinv.engine.StateMachine;
import com.mpu.spinv.utils.Constants;

@SuppressWarnings("serial")
public class Core extends JPanel implements Runnable {

	// Engine wise objects
	private StateMachine stateMachine;
	
	private Thread thread;
	private boolean running = false;

	public Core() {
		initUI();
		initGame();
	}

	private void initUI() {
		setFocusable(true);
		setDoubleBuffered(true);
	}

	private void initGame() {
		stateMachine = new StateMachine();

		if (!running || thread == null) {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}

	private void update() {
		stateMachine.update();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Clears the screen before drawing.
		g.clearRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		
		stateMachine.draw(g);
	}

	@Override
	public void run() {
		long beforeTime, diff, sleep;
		beforeTime = System.currentTimeMillis();
		while (running) {
			update();
			repaint();

			diff = System.currentTimeMillis() - beforeTime;
			sleep = Constants.DELAY - diff;

			if (sleep < 0)
				sleep = 2;

			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			beforeTime = System.currentTimeMillis();
		}
	}

}
