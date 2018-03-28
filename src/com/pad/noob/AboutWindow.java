package com.pad.noob;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class AboutWindow {
	private NoobPad n = new NoobPad();
	
	public AboutWindow() {
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
		frame.setIconImage(n.getLogo().getImage());
		frame.add(new JLabel(n.getLogoX2()));
		frame.add(new JLabel("NoobPad v1"));
		frame.setTitle("About"); 
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}

}
