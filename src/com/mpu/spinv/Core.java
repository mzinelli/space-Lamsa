package com.mpu.spinv;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.mpu.spinv.engine.StateMachine;
import com.mpu.spinv.engine.model.Animation;
import com.mpu.spinv.engine.model.Sprite;
import com.mpu.spinv.engine.model.SpriteSheet;
import com.mpu.spinv.utils.Constants;

public class Core extends JPanel implements Runnable {

	// Engine wise objects
	private StateMachine stateMachine;
	
	private Thread thread;
	private boolean running = false;
	
	// Game Elements
	SpriteSheet spriteSheet = new SpriteSheet(Core.class.getResource("/resources/img/player.png"));
	private Animation animation;
	private Sprite[] sprites = {
			new Sprite(spriteSheet.getSprite(0, 0, 32, 32)),
			new Sprite(spriteSheet.getSprite(32, 0, 32, 32)),
			new Sprite(spriteSheet.getSprite(64, 0, 32, 32))
	};

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
		
		
		animation = new Animation(sprites, 30, true, true);
		animation.start();
		

		if (!running || thread == null) {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}

	private void update() {
		stateMachine.update();
		animation.update();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Clears the screen before drawing.
		g.clearRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		
		g.drawImage(animation.getSprite(), 0, 0, null);
		
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
