package io.thaumavor.rbradford.JRay.Controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import io.thaumavor.rbradford.JRay.Display.Display;

public class MainKeyControls implements KeyListener {

	Display display;
	
	public MainKeyControls(Display display) {
		this.display = display;
	}
	
	@Override
	public void keyPressed(KeyEvent e) { //Different key controls
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_A) {//Move left based on your rotation
			display.currentScene.cameraLocation.xPos -= 10 * Math.cos(display.currentScene.xAngle); 
			display.currentScene.cameraLocation.yPos += 10 * Math.sin(display.currentScene.xAngle);
		}
		
		if (key == KeyEvent.VK_D) {//Move right based on your rotation
			display.currentScene.cameraLocation.xPos += 10 * Math.cos(display.currentScene.xAngle);
			display.currentScene.cameraLocation.yPos -= 10 * Math.sin(display.currentScene.xAngle);
		}
		
		if (key == KeyEvent.VK_W) {//Move forwards based on your rotation
			//Main.cameraLocation.zPos += 10 * Math.sin(Main.yAngle);
			display.currentScene.cameraLocation.xPos += 10 * Math.sin(display.currentScene.xAngle);
			display.currentScene.cameraLocation.yPos += 10 * Math.cos(display.currentScene.xAngle);
		}
		
		if (key == KeyEvent.VK_S) {//Move backwards based on your rotation
			//Main.cameraLocation.zPos -= 10 * Math.sin(Main.yAngle);
			display.currentScene.cameraLocation.xPos -= 10 * Math.sin(display.currentScene.xAngle);
			display.currentScene.cameraLocation.yPos -= 10 * Math.cos(display.currentScene.xAngle);
		}
		
		if (key == KeyEvent.VK_E) {//Move down 
			display.currentScene.cameraLocation.zPos -= 10;
		}
		
		if (key == KeyEvent.VK_Q) {//Move up 
			display.currentScene.cameraLocation.zPos += 10;
		}
		
		if(key == KeyEvent.VK_ESCAPE) { //Pause and unpause the engine
			if(!display.paused) {
				display.paused = true;
				display.display.pauseGame();
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
