package com.ryanb3.JRay.Controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.ryanb3.JRay.Display.Display;
import com.ryanb3.JRay.Tests.Test;

public class PauseKeyControls implements KeyListener{

	Display display;
	
	public PauseKeyControls(Display display) {
		this.display = display;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE) {
			display.paused = false;
			display.display.startEngine();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
