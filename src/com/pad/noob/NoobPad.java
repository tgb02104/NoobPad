package com.pad.noob;

import java.awt.BorderLayout;
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

public class NoobPad extends JFrame{
	private static JTextArea textArea = new JTextArea();
	private JMenuBar menuBar;
	private JMenu menu1, menu2;
	private JMenuItem menu1_item1, menu1_item2, menu1_item3, menu2_item1;
	private static ImageIcon logo = new ImageIcon("ressources\\NoobPad_logo.png");
	private static ImageIcon logoX2 = new ImageIcon("ressources\\NoobPad_logo_x2.png");
	
	public NoobPad() {
		// building menus
		menu1_item1 = new JMenuItem("Open");
			// add a Ctrl+O shortcut to the Save menu item
		menu1_item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		menu1_item2 = new JMenuItem("Save");
			// add a Ctrl+S shortcut to the Save menu item
		menu1_item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		menu1_item3 = new JMenuItem("Exit");
		menu1 = new JMenu("File");
		menu1.add(menu1_item1);
		menu1.add(menu1_item2);
		menu1.addSeparator();
		menu1.add(menu1_item3);

		menu2_item1 = new JMenuItem("About");
		menu2 = new JMenu("?");
		menu2.add(menu2_item1);
		
		menuBar = new JMenuBar();
		menuBar.add(menu1);
		menuBar.add(menu2);
		
		//textArea.setLineWrap(true);
		//textArea.setWrapStyleWord(true);
		this.setJMenuBar(menuBar);
		this.add(new JScrollPane(textArea));
		
		// all items actions
		menu1_item1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
					OpenFileWindow();
		}});
		
		menu1_item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				SaveFileWindow();
		}});
		
		menu1_item3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				System.exit(0);
			}
		});
		
		menu2_item1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				AboutWindow();
			}
		});
	}
	
	public static void SaveFileWindow() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Save");
		fc.setApproveButtonText("Save");
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		fc.showOpenDialog(textArea);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fc.getSelectedFile()))) {
			String text = textArea.getText();
			bw.write(text);
			System.out.println(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void OpenFileWindow(){
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

	public static void AboutWindow() {
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
	
	public static void MainWindow() {
		JFrame frame = new NoobPad();
		frame.setIconImage(logo.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("NoobPad");
		frame.setSize(800, 650);
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		MainWindow();
	}

}
