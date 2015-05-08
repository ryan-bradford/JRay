package Controls;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Other.OtherFunctions;
import main.Main;

public class KeyControls implements KeyListener {

	boolean blackOrRed = true; //True is black, false is red
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_A) {
			Main.cameraLocation.xPos -= 10 * Math.cos(Main.xAngle);
			Main.cameraLocation.yPos += 10 * Math.sin(Main.xAngle);
		}
		
		if (key == KeyEvent.VK_D) {
			Main.cameraLocation.xPos += 10 * Math.cos(Main.xAngle);
			Main.cameraLocation.yPos -= 10 * Math.sin(Main.xAngle);
		}
		
		if (key == KeyEvent.VK_W) {
			Main.cameraLocation.zPos += 10 * Math.sin(Main.yAngle);
			Main.cameraLocation.xPos += 10 * Math.sin(Main.xAngle);
			Main.cameraLocation.yPos += 10 * Math.cos(Main.xAngle);
		}
		
		if (key == KeyEvent.VK_S) {
			Main.cameraLocation.zPos -= 10 * Math.sin(Main.yAngle);
			Main.cameraLocation.xPos -= 10 * Math.sin(Main.xAngle);
			Main.cameraLocation.yPos -= 10 * Math.cos(Main.xAngle);
		}
		
		if (key == KeyEvent.VK_E) {
			Main.cameraLocation.zPos -= 10;
		}
		
		if (key == KeyEvent.VK_Q) {
			Main.cameraLocation.zPos += 10;
		}
		
		if(key == KeyEvent.VK_ESCAPE) {
			Main.paused = !Main.paused;
			if(blackOrRed) {
				Main.display.display.background = new Color(255, 0, 0);
				OtherFunctions.hideCursor(false);
				blackOrRed = false;
				Main.display.repaint();
			} else {
				Main.display.display.background = new Color(0, 0, 0);
				OtherFunctions.hideCursor(true);
				blackOrRed = true;
				Main.display.repaint();
			}
		}
		
		if(key == KeyEvent.VK_UP) {
			if(Main.FOV < Math.PI * 4 / 3) {
				Main.FOV += Math.PI / 12;
			}
		}
		
		if(key == KeyEvent.VK_DOWN) {
			if(Main.FOV > Math.PI / 3) {
				Main.FOV -= Math.PI / 12;
			}
		}
		
		if(key == KeyEvent.VK_RIGHT) {
			if(Main.sensitivity < 10) {
				Main.sensitivity++;
			}
		}
		
		if(key == KeyEvent.VK_LEFT) {
			if(Main.sensitivity > 1) {
				Main.sensitivity--;
			}
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
