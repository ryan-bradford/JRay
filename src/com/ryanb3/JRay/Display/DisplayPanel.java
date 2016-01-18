package com.ryanb3.JRay.Display;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.swing.JPanel;

import com.ryanb3.JRay.Tests.Test;

public class DisplayPanel extends JPanel {

	int textGap = 14;
	double deltaT;
	double timePassed;
	double lastTime = System.currentTimeMillis();
	ArrayList<Integer> secondFPSs = new ArrayList<Integer>();
	public boolean showDebugInfo = true;
	public SettingsScreen settings;
	ArrayList<Double> CPULoad = new ArrayList<Double>();
	ArrayList<Double> memoryLoad = new ArrayList<Double>();
	ArrayList<Integer> FPSs = new ArrayList<Integer>();
	double startTime = System.currentTimeMillis();
	long maxMemory = Runtime.getRuntime().maxMemory();
	int averageFPS = 0;

	public DisplayPanel() {
		this.setLayout(null);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) (g);
		super.paintComponent(g2);
		displayScene(g2);
		if (showDebugInfo) {
			displaySystemInfo(g2);
		}
	}

	void displaySystemInfo(Graphics2D g2) {
		String[] stringsToDraw = new String[10];
		Runtime runtime = Runtime.getRuntime();
		long allocatedMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();
		double CPULoad1 = 0;
		try {
			CPULoad1 = getProcessCpuLoad();
		} catch (MalformedObjectNameException | InstanceNotFoundException | ReflectionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		getCurrentFPS();
		if (timePassed >= 1000) {
			memoryLoad.add((double) ((allocatedMemory - freeMemory) / 1024 / 256));
			averageFPS = getAverageFPS();
			FPSs.add(averageFPS);
			this.CPULoad.add(CPULoad1);
			timePassed = 0.0;
			secondFPSs.clear();
		}
		g2.setColor(Color.YELLOW); // Draw FPS
		stringsToDraw[0] = "FPS: " + Integer.toString(averageFPS);
		stringsToDraw[1] = ("Free Memory: " + (freeMemory / 1024 / 256));
		stringsToDraw[2] = ("Allocated Memory: " + (allocatedMemory / 1024 / 256));
		stringsToDraw[3] = ("Used Memory: " + ((allocatedMemory - freeMemory) / 1024 / 256));
		stringsToDraw[4] = ("Max Memory: " + (maxMemory / 1024 / 256));
		stringsToDraw[5] = ("Total Free Memory: " + ((freeMemory + (maxMemory - allocatedMemory)) / 1024 / 256));
		stringsToDraw[6] = "OS: " + System.getProperty("os.name");
		stringsToDraw[7] = "OS Version: " + System.getProperty("os.version");
		stringsToDraw[8] = "OS Architecture: " + System.getProperty("os.arch");
		stringsToDraw[9] = "0%";
		stringsToDraw[9] = "CPU Usage: " + CPULoad1;
		for (int i = 0; i < stringsToDraw.length; i++) {
			g2.drawString(stringsToDraw[i], 5, textGap * (i + 1));
		}
	}

	void displayScene(Graphics2D g2) {
		g2.setColor(Color.BLACK); // Sets the background color
		g2.fillRect(0, 0, Test.screenWidth, Test.screenHeight); // Draws the background
		g2.setStroke(new BasicStroke(10));
		try {
			for (int i = 0; i < Test.display.currentScene.toRender.size(); i++) {
				g2.setColor(Test.display.currentScene.toRender.get(i).myPoly.myColor);
				g2.fillPolygon(Test.display.currentScene.toRender.get(i).myPoly);// Draws the rasterized polygon
			}
		} catch (NullPointerException ex) {
			// ex.printStackTrace();
		} catch(IndexOutOfBoundsException ex) {
			
		}
	}

	public void saveVARs() {
		FileWriter write = null;
		try {
			write = new FileWriter(System.currentTimeMillis() + ".txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter print = new PrintWriter(write);
		print.println((System.currentTimeMillis() - startTime) / 1000 + " Session length in Seconds");
		print.println("CPU Load");
		for (int i = 0; i < CPULoad.size(); i++) {
			print.println(CPULoad.get(i));
		}
		print.println("Memory Load");
		for (int i = 0; i < CPULoad.size(); i++) {
			print.println(memoryLoad.get(i));
		}
		print.println("FPS");
		for (int i = 0; i < CPULoad.size(); i++) {
			print.println(FPSs.get(i));
		}
		print.close();
	}

	public void pauseGame() {
		Test.display.paused = true;
		Test.display.hideCursor(false);
		this.setFocusable(false);
		Test.display.setFocusable(false);
		settings.setFocusable(true);
		settings.setVisible(true);
		settings.requestFocus();
		settings.repaint();
		Test.display.repaint();
	}

	public void startEngine() {
		Test.display.mover.sensitivity = 11 - Test.display.display.settings.sensitivitySlider.getValue();
		Test.display.FOV = Math.PI / 3 + Math.PI * Test.display.display.settings.FOVSlider.getValue() / 10;
		Test.display.updateKeyControls();
		Test.display.paused = false;
		Test.display.hideCursor(true);
		settings.setVisible(false);
		settings.setFocusable(false);
		Test.display.setFocusable(true);
		Test.display.requestFocus();
		Test.display.repaint();
	}

	public void loadSettings() {
		settings = new SettingsScreen();
		settings.setBounds(0, 0, Test.display.getWidth(), Test.display.getHeight());
		settings.setVisible(false);
		settings.setFocusable(false);
		settings.repaint();
		add(settings);
	}

	public double getProcessCpuLoad() throws MalformedObjectNameException, ReflectionException, InstanceNotFoundException {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name = ObjectName.getInstance("java.lang:type=OperatingSystem");
		AttributeList list = mbs.getAttributes(name, new String[]{"ProcessCpuLoad"});

		if (list.isEmpty())
			return Double.NaN;

		Attribute att = (Attribute) list.get(0);
		Double value = (Double) att.getValue();

		if (value == -1.0)
			return Double.NaN; // usually takes a couple of seconds before we get real values

		return ((int) (value * 1000) / 10.0); // returns a percentage value with 1 decimal point precision
	}
	
	public int getCurrentFPS() {
		deltaT = System.currentTimeMillis() - lastTime; // Record FPS
		timePassed+=deltaT;
		lastTime = System.currentTimeMillis();
		int FPS = (int)(1000.0 / deltaT);
		System.out.println(FPS);
		secondFPSs.add(FPS);
		return FPS;
	}
	
	public int getAverageFPS() {
		int total = 0;
		for(int i = 0; i < secondFPSs.size(); i++) {
			total+= secondFPSs.get(i);
		}
		return total / 1000;
	}
}
