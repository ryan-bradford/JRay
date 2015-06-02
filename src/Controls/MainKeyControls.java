package Controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Main;

public class MainKeyControls implements KeyListener {

	int myID;
	
	public MainKeyControls(int ID) {
		myID = ID;
	}
	
	@Override
	public void keyPressed(KeyEvent e) { //Different key controls
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_A) {//Move left based on your rotation
			Main.displays.get(myID).currentScene.cameraLocation.xPos -= 10 * Math.cos(Main.displays.get(myID).currentScene.xAngle); 
			Main.displays.get(myID).currentScene.cameraLocation.yPos += 10 * Math.sin(Main.displays.get(myID).currentScene.xAngle);
		}
		
		if (key == KeyEvent.VK_D) {//Move right based on your rotation
			Main.displays.get(myID).currentScene.cameraLocation.xPos += 10 * Math.cos(Main.displays.get(myID).currentScene.xAngle);
			Main.displays.get(myID).currentScene.cameraLocation.yPos -= 10 * Math.sin(Main.displays.get(myID).currentScene.xAngle);
		}
		
		if (key == KeyEvent.VK_W) {//Move forwards based on your rotation
			//Main.cameraLocation.zPos += 10 * Math.sin(Main.yAngle);
			Main.displays.get(myID).currentScene.cameraLocation.xPos += 10 * Math.sin(Main.displays.get(myID).currentScene.xAngle);
			Main.displays.get(myID).currentScene.cameraLocation.yPos += 10 * Math.cos(Main.displays.get(myID).currentScene.xAngle);
		}
		
		if (key == KeyEvent.VK_S) {//Move backwards based on your rotation
			//Main.cameraLocation.zPos -= 10 * Math.sin(Main.yAngle);
			Main.displays.get(myID).currentScene.cameraLocation.xPos -= 10 * Math.sin(Main.displays.get(myID).currentScene.xAngle);
			Main.displays.get(myID).currentScene.cameraLocation.yPos -= 10 * Math.cos(Main.displays.get(myID).currentScene.xAngle);
		}
		
		if (key == KeyEvent.VK_E) {//Move down 
			Main.displays.get(myID).currentScene.cameraLocation.zPos -= 10;
		}
		
		if (key == KeyEvent.VK_Q) {//Move up 
			Main.displays.get(myID).currentScene.cameraLocation.zPos += 10;
		}
		
		if(key == KeyEvent.VK_ESCAPE) { //Pause and unpause the engine
			if(!Main.displays.get(myID).paused) {
				Main.displays.get(myID).paused = true;
				Main.displays.get(myID).display.pauseGame();
			}
		}
		
		if(key == KeyEvent.VK_UP) { //Increase FOV
			if(Main.displays.get(myID).FOV < Math.PI * 4 / 3) {
				Main.displays.get(myID).FOV += Math.PI / 12;
			}
		}
		
		if(key == KeyEvent.VK_DOWN) {//Decrease FOV
			if(Main.displays.get(myID).FOV > Math.PI / 3) {
				Main.displays.get(myID).FOV -= Math.PI / 12;
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
