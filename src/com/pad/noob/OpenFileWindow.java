package com.pad.noob;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenFileWindow {
	NoobPad n = new NoobPad();
	
	public OpenFileWindow(){
		JFileChooser fc = new JFileChooser();
		fc.setDialogType(JFileChooser.OPEN_DIALOG);
		fc.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		if(fc.showOpenDialog(n) == JFileChooser.APPROVE_OPTION)
		{
			n.getTextArea().setText("");
			try (BufferedReader br = new BufferedReader(new FileReader(fc.getSelectedFile()))) {
				String line = null;
				while ((line = br.readLine()) != null)
					n.getTextArea().append(line + "\n");					
			} catch (IOException e) {
				e.printStackTrace();
			}
			// marche pas
			n.getMainWindowFrame().setTitle(n.getAppName() + " - " + fc.getSelectedFile());
			//n.getMainWindowFrame().repaint();
			
		}
	}

}
