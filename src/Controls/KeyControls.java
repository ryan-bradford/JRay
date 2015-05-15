package Controls;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Other.OtherFunctions;
import main.Main;

public class KeyControls implements KeyListener {

	boolean blackOrRed = true; //True is black, false is red
	
	@Override
	public void keyPressed(KeyEvent e) { //Different key controls
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_A) {//Move left based on your rotation
			Main.cameraLocation.xPos -= 10 * Math.cos(Main.xAngle); 
			Main.cameraLocation.yPos += 10 * Math.sin(Main.xAngle);
		}
		
		if (key == KeyEvent.VK_D) {//Move right based on your rotation
			Main.cameraLocation.xPos += 10 * Math.cos(Main.xAngle);
			Main.cameraLocation.yPos -= 10 * Math.sin(Main.xAngle);
		}
		
		if (key == KeyEvent.VK_W) {//Move forwards based on your rotation
			Main.cameraLocation.zPos += 10 * Math.sin(Main.yAngle);
			Main.cameraLocation.xPos += 10 * Math.sin(Main.xAngle);
			Main.cameraLocation.yPos += 10 * Math.cos(Main.xAngle);
		}
		
		if (key == KeyEvent.VK_S) {//Move backwards based on your rotation
			Main.cameraLocation.zPos -= 10 * Math.sin(Main.yAngle);
			Main.cameraLocation.xPos -= 10 * Math.sin(Main.xAngle);
			Main.cameraLocation.yPos -= 10 * Math.cos(Main.xAngle);
		}
		
		if (key == KeyEvent.VK_E) {//Move down 
			Main.cameraLocation.zPos -= 10;
		}
		
		if (key == KeyEvent.VK_Q) {//Move up 
			Main.cameraLocation.zPos += 10;
		}
		
		if(key == KeyEvent.VK_ESCAPE) { //Pause and unpause the engine
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
		
		if(key == KeyEvent.VK_UP) { //Increase FOV
			if(Main.FOV < Math.PI * 4 / 3) {
				Main.FOV += Math.PI / 12;
			}
		}
		
		if(key == KeyEvent.VK_DOWN) {//Decrease FOV
			if(Main.FOV > Math.PI / 3) {
				Main.FOV -= Math.PI / 12;
			}
		}
		
		if(key == KeyEvent.VK_RIGHT) {//Increase sensitivity
			if(Main.sensitivity < 10) {
				Main.sensitivity++;
			}
		}
		
		if(key == KeyEvent.VK_LEFT) {//Decrease sensivity
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
