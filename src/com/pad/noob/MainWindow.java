package com.pad.noob;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainWindow {
	private ImageIcon logo = new ImageIcon("ressources\\NoobPad_logo.png");
	
	public MainWindow() {
		JFrame frame = new NoobPad();
		frame.setIconImage(logo.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("NoobPad");
		frame.setSize(800, 650);
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true);
	}

}
