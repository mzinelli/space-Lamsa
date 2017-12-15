package com.mpu.spinv;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.mpu.spinv.engine.StateMachine;
import com.mpu.spinv.utils.Constants;

import jplay.Animation;
import jplay.GameImage;
import jplay.Mouse;
import jplay.Window;
import sun.font.CreatedFontTracker;

public class Launcher {


	public static void main(String[] args) {
		new Launcher();
	}

	public Launcher() {

		JFrame f = new JFrame("Space Invaders");

		// Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");

		// Set the blank cursor to the JFrame.
		f.getContentPane().setCursor(blankCursor);
		
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		f.setResizable(false);
		f.setLocationRelativeTo(null);

		f.add(new Core());
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				StateMachine.setActiveState("game-menu");
				int confirm = JOptionPane.showConfirmDialog(null, "Você deseja sair do jogo? =(", "Atenção",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

				if (confirm == JOptionPane.YES_OPTION) {
					// verifica se o usuário clicou no botão YES
					System.exit(0);

				} else {
					StateMachine.setActiveState("gameplay");
				}
				// System.exit(0);

			}
		});
	}


}