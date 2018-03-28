package com.pad.noob;

import javax.swing.JFrame;

public class MainWindow {
	private NoobPad n = new NoobPad();
	private JFrame mainWindowFrame = n.getMainWindowFrame();
	
	public MainWindow() {
		mainWindowFrame = new NoobPad();
		mainWindowFrame.setIconImage(n.getLogo().getImage());
		mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindowFrame.setTitle(n.getAppName() + " - " + "new.txt");
		mainWindowFrame.setSize(800, 650);
		mainWindowFrame.setLocationRelativeTo(null); 
		mainWindowFrame.setVisible(true);
	}

}
