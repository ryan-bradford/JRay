package Controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Main;

public class KeyControls implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_A) {
			Main.cameraLocation.xPos -= 10;
		}
		
		if (key == KeyEvent.VK_D) {
			Main.cameraLocation.xPos += 10;
		}
		
		if (key == KeyEvent.VK_W) {
			Main.cameraLocation.yPos += 10;
		}
		
		if (key == KeyEvent.VK_S) {
			Main.cameraLocation.yPos -= 10;
		}
		
		if (key == KeyEvent.VK_E) {
			Main.cameraLocation.zPos -= 10;
		}
		
		if (key == KeyEvent.VK_Q) {
			Main.cameraLocation.zPos += 10;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
