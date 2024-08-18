package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	public boolean upPressed, downPressed, leftPressed, rightPressed;

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		// return the key pressed as an integer
		int code = e.getKeyCode();

		// if user press w
		if (code == KeyEvent.VK_W) {
			upPressed = true;
		}
		// if user press s
		if (code == KeyEvent.VK_S) {
			downPressed = true;
		}
		// if user press a
		if (code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		// if user press d
		if (code == KeyEvent.VK_D) {
			rightPressed = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		// if user release w
		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		// if user release s
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		// if user release a
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		// if user release d
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}

	}

}
