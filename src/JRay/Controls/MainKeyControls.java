package JRay.Controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Main;

public class MainKeyControls implements KeyListener {

	
	
	public MainKeyControls() {

	}
	
	@Override
	public void keyPressed(KeyEvent e) { //Different key JRay.Controls
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_A) {//Move left based on your rotation
			Main.display.currentScene.cameraLocation.xPos -= 10 * Math.cos(Main.display.currentScene.xAngle); 
			Main.display.currentScene.cameraLocation.yPos += 10 * Math.sin(Main.display.currentScene.xAngle);
		}
		
		if (key == KeyEvent.VK_D) {//Move right based on your rotation
			Main.display.currentScene.cameraLocation.xPos += 10 * Math.cos(Main.display.currentScene.xAngle);
			Main.display.currentScene.cameraLocation.yPos -= 10 * Math.sin(Main.display.currentScene.xAngle);
		}
		
		if (key == KeyEvent.VK_W) {//Move forwards based on your rotation
			//Main.cameraLocation.zPos += 10 * Math.sin(Main.yAngle);
			Main.display.currentScene.cameraLocation.xPos += 10 * Math.sin(Main.display.currentScene.xAngle);
			Main.display.currentScene.cameraLocation.yPos += 10 * Math.cos(Main.display.currentScene.xAngle);
		}
		
		if (key == KeyEvent.VK_S) {//Move backwards based on your rotation
			//Main.cameraLocation.zPos -= 10 * Math.sin(Main.yAngle);
			Main.display.currentScene.cameraLocation.xPos -= 10 * Math.sin(Main.display.currentScene.xAngle);
			Main.display.currentScene.cameraLocation.yPos -= 10 * Math.cos(Main.display.currentScene.xAngle);
		}
		
		if (key == KeyEvent.VK_E) {//Move down 
			Main.display.currentScene.cameraLocation.zPos -= 10;
		}
		
		if (key == KeyEvent.VK_Q) {//Move up 
			Main.display.currentScene.cameraLocation.zPos += 10;
		}
		
		if(key == KeyEvent.VK_ESCAPE) { //Pause and unpause the engine
			if(!Main.display.paused) {
				Main.display.paused = true;
				Main.display.display.pauseGame();
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
