package com.mpu.spinv;

import javax.swing.JFrame;

import com.mpu.spinv.utils.Constants;

public class Launcher {

	public static void main(String[] args) {
		JFrame f = new JFrame("Space Invaders");
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		
		f.add(new Core());
		f.setVisible(true);
	}
	
}
