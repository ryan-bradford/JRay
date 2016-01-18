package com.ryanb3.JRay.Display;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.ryanb3.JRay.Controls.PauseKeyControls;
import com.ryanb3.JRay.Tests.Test;

public class SettingsScreen extends JPanel {

	JButton saveDebugInfo;
	JButton showOrHideDebug;
	JButton updateFPSButton;
	JSlider sensitivitySlider;
	JSlider FOVSlider;
	JTextField updateFPS;
	JTextPane title;
	PauseKeyControls keys;
	int width = 150;
	int height = 40;
	Display display;

	public SettingsScreen(Display display) {
		this.display = display;
		this.setLayout(null);
		keys = new PauseKeyControls(display);
		initDebugButtons();
		initFPSControls();
		initSliders();
		initTitle();
	}

	@Override
	public void paintComponent(Graphics g) {
		this.addKeyListener(keys);
		super.paintComponent(g);
	}
	
	private void initTitle() {
		title = new JTextPane();
		title.setEditable(false);
		//title.setOpaque(false);
		title.setText("Settings");
		title.setBounds((display.getWidth() - 120) / 2, 50, 120, 50);
		title.setFont(new Font("TimesRoman", Font.BOLD, 30));
		title.addKeyListener(keys);
		add(title);
	}
	
	private void initDebugButtons() {
		showOrHideDebug = new JButton("Hide Debug Info");
		showOrHideDebug.addActionListener(new DebugButtonListener());
		showOrHideDebug.addKeyListener(keys);
		width = 150;
		height = 40;
		showOrHideDebug.setBounds(50, display.getHeight() / 12, width, height);
		add(showOrHideDebug);
		saveDebugInfo = new JButton("Output a Debug File");
		saveDebugInfo.addActionListener(new SaveButtonListener());
		saveDebugInfo.addKeyListener(keys);
		saveDebugInfo.setBounds(50, 4 * display.getHeight() / 12, width, height);
		add(saveDebugInfo);
	}
	
	private void initFPSControls() {
		updateFPS = new JTextField("Enter Your FPS Limit: ");
		width = 150;
		height = 20;
		updateFPS.setBounds(50, (int) (2.5 * display.getHeight() / 12), width, height);
		updateFPS.addKeyListener(keys);
		add(updateFPS);
		updateFPSButton = new JButton("Press To Update Your FPS Limit");
		width = 220;
		updateFPSButton.setBounds(50, 3 * display.getHeight() / 12, width, height);
		updateFPSButton.addKeyListener(keys);
		updateFPSButton.addActionListener(new FPSLimitListener());
		add(updateFPSButton);
	}
	
	private void initSliders() {
		initSensivitySlider();
		initFOVSlider();
	}
	
	private void initFOVSlider() {
		FOVSlider = new JSlider();
		FOVSlider.setMaximum(10);
		FOVSlider.setMinimum(0);
		FOVSlider.setValue(1);
		FOVSlider.setToolTipText("Sensitivity Slider");
		FOVSlider.setMajorTickSpacing(2);
		Hashtable labelTable = new Hashtable();
		labelTable.put( 10, new JLabel("Quake Pro!") );
		labelTable.put( 0, new JLabel("Pretty Much Blind") );
		FOVSlider.setPaintLabels(true);
		FOVSlider.setLabelTable( labelTable );
		FOVSlider.setOrientation(JSlider.VERTICAL);
		FOVSlider.addKeyListener(keys);
		FOVSlider.setBounds(50, (int)(6.5 * display.getHeight() / 12), 120, 200);
		FOVSlider.setVisible(true);
		add(FOVSlider);
		JLabel myLabel = new JLabel();
		myLabel.setBounds(200, (int)(7.5 * display.getHeight() / 12), 130, 50);
		myLabel.setText("Change FOV");
		add(myLabel);
	}
	
	private void initSensivitySlider() {
		sensitivitySlider = new JSlider();
		sensitivitySlider.setMaximum(Test.sensitivityUpper);
		sensitivitySlider.setMinimum(Test.sensitivityLower);
		sensitivitySlider.setValue((int) display.mover.sensitivity);
		sensitivitySlider.setToolTipText("Sensitivity Slider");
		sensitivitySlider.setMajorTickSpacing(2);
		sensitivitySlider.setMinorTickSpacing(1);
		sensitivitySlider.setPaintTicks(true);
		sensitivitySlider.setPaintLabels(true);
		sensitivitySlider.setOrientation(JSlider.HORIZONTAL);
		sensitivitySlider.addKeyListener(keys);
		sensitivitySlider.setBounds(50, 5 * display.getHeight() / 12, 100, 50);
		sensitivitySlider.setVisible(true);
		add(sensitivitySlider);
		JLabel myLabel = new JLabel();
		myLabel.setBounds(50, (int)(5.5 * display.getHeight() / 12), 130, 50);
		myLabel.setText("Change Sensitivity");
		add(myLabel);
	}

	private class DebugButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			display.display.showDebugInfo = !display.display.showDebugInfo;
			if (display.display.showDebugInfo) {
				showOrHideDebug.setText("Hide Debug Info");
			} else {
				showOrHideDebug.setText("Show Debug Info");
			}
		}
	}
	
	private class SaveButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			display.display.saveVARs();			
		}
	}

	private class FPSLimitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String fpsText = updateFPS.getText();
			int numStart = 0;
			for (int i = 0; i < fpsText.length(); i++) {
				if (fpsText.charAt(i) == '1' || fpsText.charAt(i) == '2' || fpsText.charAt(i) == '3' || fpsText.charAt(i) == '4' || fpsText.charAt(i) == '5' || fpsText.charAt(i) == '6'
						|| fpsText.charAt(i) == '7' || fpsText.charAt(i) == '8' || fpsText.charAt(i) == '9') {
					numStart = i;
					break;
				}
			}
			int fps = Integer.parseInt(fpsText.substring(numStart, fpsText.length()).trim());
			display.displayWait = 1000 / fps - 1;
		}
	}
}
