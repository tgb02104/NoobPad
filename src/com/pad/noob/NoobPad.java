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

public class NoobPad extends JFrame implements INoobPad, ActionListener {
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
	
	private String appName = new String("NoobPad");
	
	
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
		
		itemNew.addActionListener(this);
		itemOpen.addActionListener(this);
		itemSave.addActionListener(this);
		itemExit.addActionListener(this);
		itemFind.addActionListener(this);
		itemAbout.addActionListener(this);
	}
	
	public ImageIcon getLogo() {
		return this.logo;
	}
	
	public ImageIcon getLogoX2() {
		return this.logoX2;
	}
		
	public String getAppName() {
		return this.appName;
	}
	
	public JTextArea getTextArea() {
		return this.textArea;
	}
	
	@Override
	public void newFile() {
		this.setTitle(appName + " - " + "new.txt");
		textArea.setText("");
	}


	// a zoulouter plus tard
	@Override
	public void findWindow() {
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
			    
			    // removing previous highlight when clicking on Next button
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
	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource() == itemNew)
			newFile();
		else if (ev.getSource() == itemOpen)
			new OpenFileWindow(this);
		else if (ev.getSource() == itemSave)
			new SaveFileWindow(this);
		else if (ev.getSource() == itemAbout)
			new AboutWindow(this);
		else if (ev.getSource() == itemFind)
			findWindow();
		else 
			System.exit(0);
	}	
}

