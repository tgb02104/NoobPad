package com.pad.noob;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class NoobPad extends JFrame implements INoobPad{
	private JTextArea textArea = new JTextArea();
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu menuFile = new JMenu("File");
	private JMenu menuQuestionMark = new JMenu("?");
	
	private JMenuItem itemOpen = new JMenuItem("Open");
	private JMenuItem itemSave = new JMenuItem("Save");
	private JMenuItem itemExit = new JMenuItem("Exit");
	private JMenuItem itemAbout = new JMenuItem("About");
	
	private ImageIcon logo = new ImageIcon("ressources\\NoobPad_logo.png");
	private ImageIcon logoX2 = new ImageIcon("ressources\\NoobPad_logo_x2.png");
	
	
	public NoobPad() {
		// building menus
		menuBar.add(menuFile);
		menuBar.add(menuQuestionMark);
		menuFile.add(itemOpen);
		menuFile.add(itemSave);
		menuFile.addSeparator();
		menuFile.add(itemExit);
		menuQuestionMark.add(itemAbout);

		// add a Ctrl+O shortcut to the Open menu item
		itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		// add a Ctrl+S shortcut to the Save menu item
		itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		
		//textArea.setLineWrap(true);
		//textArea.setWrapStyleWord(true);
		this.setJMenuBar(menuBar);
		this.add(new JScrollPane(textArea));
		
		// items actions
		itemOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				OpenFileWindow();
		}});
		
		itemSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				SaveFileWindow();
		}});
		
		itemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				System.exit(0);
			}
		});
		
		itemAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				AboutWindow();
			}
		});
	}
	
	@Override
	public void SaveFileWindow() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Save");
		fc.setApproveButtonText("Save");
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		fc.showOpenDialog(textArea);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fc.getSelectedFile()))) {
			String text = textArea.getText();
			bw.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void OpenFileWindow(){
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Open");
		fc.setApproveButtonText("Open");
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		fc.showOpenDialog(textArea);
		try (BufferedReader br = new BufferedReader(new FileReader(fc.getSelectedFile()))) {
			String line = null;
			while ((line = br.readLine()) != null)
				textArea.append(line + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	
	@Override
	public void AboutWindow() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		frame.setContentPane(panel);
		frame.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		frame.setIconImage(logo.getImage());
		frame.add(Box.createHorizontalStrut(15));
		frame.add(new JLabel(logoX2));
		frame.add(Box.createHorizontalStrut(35));
		frame.add(new JLabel("NoobPad v1"));
		frame.setTitle("About");
		frame.setSize(220, 125);
		frame.setLocationRelativeTo(null); 
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
}
