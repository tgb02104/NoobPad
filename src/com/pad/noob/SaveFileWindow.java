package com.pad.noob;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveFileWindow {
	
	public SaveFileWindow(NoobPad noobPad) {
		JFileChooser fc = new JFileChooser();
		fc.setDialogType(JFileChooser.SAVE_DIALOG);
		fc.setSelectedFile(new File("제목없음.txt"));
		fc.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		if(fc.showSaveDialog(noobPad) == JFileChooser.APPROVE_OPTION)
		{
			String filename = String.valueOf(fc.getSelectedFile());
			if (!filename.toLowerCase().endsWith(".txt"))
				filename += ".txt";
			
			File file = new File(filename);			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
				String text = noobPad.getTextArea().getText();
				bw.write(text);	
			} catch (IOException e) {
				e.printStackTrace();
			}
			noobPad.setTitle(noobPad.getAppName() + " - " + filename);
		}
	}

}
