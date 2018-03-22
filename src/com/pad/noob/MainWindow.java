package com.pad.noob;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainWindow {
	public static JFrame MainWindowFrame;
	public static String appName = "NoobPad";
	private ImageIcon logo = new ImageIcon("ressources\\NoobPad_logo.png");
	
	public MainWindow() {
		MainWindowFrame = new NoobPad();
		MainWindowFrame.setIconImage(logo.getImage());
		MainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainWindowFrame.setTitle(appName + " - " + "new.txt");
		MainWindowFrame.setSize(800, 650);
		MainWindowFrame.setLocationRelativeTo(null); 
		MainWindowFrame.setVisible(true);
	}

}
