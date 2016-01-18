package com.ryanb3.JRay.Controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.ryanb3.JRay.Tests.Test;

public class MainKeyControls implements KeyListener {

	
	
	public MainKeyControls() {

	}
	
	@Override
	public void keyPressed(KeyEvent e) { //Different key controls
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_A) {//Move left based on your rotation
			Test.display.currentScene.cameraLocation.xPos -= 10 * Math.cos(Test.display.currentScene.xAngle); 
			Test.display.currentScene.cameraLocation.yPos += 10 * Math.sin(Test.display.currentScene.xAngle);
		}
		
		if (key == KeyEvent.VK_D) {//Move right based on your rotation
			Test.display.currentScene.cameraLocation.xPos += 10 * Math.cos(Test.display.currentScene.xAngle);
			Test.display.currentScene.cameraLocation.yPos -= 10 * Math.sin(Test.display.currentScene.xAngle);
		}
		
		if (key == KeyEvent.VK_W) {//Move forwards based on your rotation
			//Main.cameraLocation.zPos += 10 * Math.sin(Main.yAngle);
			Test.display.currentScene.cameraLocation.xPos += 10 * Math.sin(Test.display.currentScene.xAngle);
			Test.display.currentScene.cameraLocation.yPos += 10 * Math.cos(Test.display.currentScene.xAngle);
		}
		
		if (key == KeyEvent.VK_S) {//Move backwards based on your rotation
			//Main.cameraLocation.zPos -= 10 * Math.sin(Main.yAngle);
			Test.display.currentScene.cameraLocation.xPos -= 10 * Math.sin(Test.display.currentScene.xAngle);
			Test.display.currentScene.cameraLocation.yPos -= 10 * Math.cos(Test.display.currentScene.xAngle);
		}
		
		if (key == KeyEvent.VK_E) {//Move down 
			Test.display.currentScene.cameraLocation.zPos -= 10;
		}
		
		if (key == KeyEvent.VK_Q) {//Move up 
			Test.display.currentScene.cameraLocation.zPos += 10;
		}
		
		if(key == KeyEvent.VK_ESCAPE) { //Pause and unpause the engine
			if(!Test.display.paused) {
				Test.display.paused = true;
				Test.display.display.pauseGame();
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
