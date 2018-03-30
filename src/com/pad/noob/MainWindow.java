package com.pad.noob;

import javax.swing.JFrame;

public class MainWindow {

	public MainWindow() {
		NoobPad noobPad = new NoobPad();
		noobPad.setIconImage(noobPad.getLogo().getImage());
		noobPad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		noobPad.setTitle(noobPad.getAppName() + " - " + "new.txt");
		noobPad.setSize(800, 650);
		noobPad.setLocationRelativeTo(null); 
		noobPad.setVisible(true);
	}

}
