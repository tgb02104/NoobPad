package com.pad.noob;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

public class NoobPad extends JFrame implements INoobPad{
	private JTextArea textArea = new JTextArea();
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu menuFile = new JMenu("File");
	private JMenu menuEdit = new JMenu("Edit");
	private JMenu menuQuestionMark = new JMenu("?");
	
	private JMenuItem itemNew = new JMenuItem("New");
	private JMenuItem itemOpen = new JMenuItem("Open");
	private JMenuItem itemSave = new JMenuItem("Save");
	private JMenuItem itemExit = new JMenuItem("Exit");
	private JMenuItem itemFind = new JMenuItem("Find");
	private JMenuItem itemAbout = new JMenuItem("About");
	
	private ImageIcon logo = new ImageIcon("ressources\\NoobPad_logo.png");
	private ImageIcon logoX2 = new ImageIcon("ressources\\NoobPad_logo_x2.png");
	
	
	public NoobPad() {
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuQuestionMark);
		menuFile.add(itemNew);
		menuFile.add(itemOpen);
		menuFile.add(itemSave);
		menuFile.addSeparator();
		menuFile.add(itemExit);
		menuEdit.add(itemFind);
		menuQuestionMark.add(itemAbout);
		
		// keyboard shortcuts
		itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		itemFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
		
		//textArea.setLineWrap(true);
		//textArea.setWrapStyleWord(true);
		this.setJMenuBar(menuBar);
		this.add(new JScrollPane(textArea));
		
		itemNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				NewFile();
			}
		});
		
		itemOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				OpenFileWindow();
			}
		});
		
		itemSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				SaveFileWindow();
			}
		});
		
		itemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				System.exit(0);
			}
		});
		
		itemFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				FindWindow();
			}
		});
		
		itemAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				AboutWindow();
			}
		});
	}
	
	@Override
	public void NewFile() {
		MainWindow.MainWindowFrame.setTitle(MainWindow.appName + " - " + "new.txt");
		textArea.setText("");
	}
	
	@Override
	public void SaveFileWindow() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogType(JFileChooser.SAVE_DIALOG);
		fc.setSelectedFile(new File("new.txt"));
		fc.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		if(fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			String filename = String.valueOf(fc.getSelectedFile());
			if (!filename.toLowerCase().endsWith(".txt"))
				filename += ".txt";
			
			File file = new File(filename);			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
				String text = textArea.getText();
				bw.write(text);	
			} catch (IOException e) {
				e.printStackTrace();
			}
			MainWindow.MainWindowFrame.setTitle(MainWindow.appName + " - " + filename);
		}
	}
	
	@Override
	public void OpenFileWindow(){
		JFileChooser fc = new JFileChooser();
		fc.setDialogType(JFileChooser.OPEN_DIALOG);
		fc.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			textArea.setText("");
			try (BufferedReader br = new BufferedReader(new FileReader(fc.getSelectedFile()))) {
				String line = null;
				while ((line = br.readLine()) != null)
					textArea.append(line + "\n");					
			} catch (IOException e) {
				e.printStackTrace();
			}
			MainWindow.MainWindowFrame.setTitle(MainWindow.appName + " - " + fc.getSelectedFile());
		}
	}
	
	@Override
	public void FindWindow() {
		JFrame frame = new JFrame();
		JTextField textField = new JTextField(15);
		JButton nextBtn = new JButton("Next");
		frame.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		frame.setIconImage(logo.getImage());
		frame.add(new JLabel("Find :"));
		frame.add(textField);
		frame.add(nextBtn);
		frame.setTitle("Find");
		frame.setLocationRelativeTo(null); 
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		
		// still not 100% working
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Highlighter highlighter = textArea.getHighlighter();
			    HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.lightGray);
			    
			    // removing previous highlight
			    highlighter.removeAllHighlights();
			    
				int indexStart = textArea.getText().indexOf(textField.getText());
				int indexEnd = indexStart + textField.getText().length();
				try {
					highlighter.addHighlight(indexStart, indexEnd, painter);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	@Override
	public void AboutWindow() {
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
		frame.setIconImage(logo.getImage());
		frame.add(new JLabel(logoX2));
		frame.add(new JLabel("NoobPad v1"));
		frame.setTitle("About");
		frame.setLocationRelativeTo(null); 
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
	}
	
}
