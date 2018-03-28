package com.pad.noob;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveFileWindow {
	private NoobPad n = new NoobPad();
	
	public SaveFileWindow() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogType(JFileChooser.SAVE_DIALOG);
		fc.setSelectedFile(new File("new.txt"));
		fc.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		if(fc.showSaveDialog(n) == JFileChooser.APPROVE_OPTION)
		{
			String filename = String.valueOf(fc.getSelectedFile());
			if (!filename.toLowerCase().endsWith(".txt"))
				filename += ".txt";
			
			File file = new File(filename);			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
				String text = n.getTextArea().getText();
				bw.write(text);	
			} catch (IOException e) {
				e.printStackTrace();
			}
			// marche pas
			n.getMainWindowFrame().setTitle(n.getAppName() + " - " + filename);
		}
	}

}
