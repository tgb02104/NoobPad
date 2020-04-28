package com.pad.noob;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class NoobPad extends JFrame implements ActionListener {
	public static  JTextArea textArea = new JTextArea();
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu menuFile = new JMenu("파일");
	private JMenu menuEdit = new JMenu("편집");
	private JMenu menuFont = new JMenu("서식");
	private JMenu menuQuestionMark = new JMenu("도움말");
	private JMenuItem itemNew = new JMenuItem("새로만들기");
	private JMenuItem itemOpen = new JMenuItem("열기");
	private JMenuItem itemSave = new JMenuItem("저장");
	private JMenuItem itemExit = new JMenuItem("끝내기");
	private JMenuItem itemFind = new JMenuItem("검색");
	private JMenuItem itemAbout = new JMenuItem("정보");
	
	private JMenuItem itemFontColor = new JMenuItem("글자색");
	private JMenuItem itemBackgroundColor = new JMenuItem("배경색");
	private ImageIcon logo = new ImageIcon("ressources\\images.png");
	private ImageIcon logoX2 = new ImageIcon("ressources\\images.png");
	
	private String appName = new String("myNotePad");
	public static JScrollPane  pn = new JScrollPane(textArea);
	
	public NoobPad() {
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuFont);
		menuBar.add(menuQuestionMark);
		menuFile.add(itemNew);
		menuFile.add(itemOpen);
		menuFile.add(itemSave);
		menuFile.addSeparator();
		menuFile.add(itemExit);
		menuEdit.add(itemFind);
		menuQuestionMark.add(itemAbout);
		menuFont.add(itemFontColor);
		menuFont.add(itemBackgroundColor);
		
		// keyboard shortcuts
		itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		itemFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
		
		this.setJMenuBar(menuBar);

		this.add(pn);
		
		itemNew.addActionListener(this);
		itemOpen.addActionListener(this);
		itemSave.addActionListener(this);
		itemExit.addActionListener(this);
		itemFind.addActionListener(this);
		itemAbout.addActionListener(this);
		itemFontColor.addActionListener(this);
		itemBackgroundColor.addActionListener(this);
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
	
	
	public void newFile() {
		this.setTitle(appName + " - " + "제목없음.txt"); 
		textArea.setText("");
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
			new FindWindow(this);
		else if (ev.getSource() == itemFontColor)
			new FontWindow(this);
		else if (ev.getSource() == itemBackgroundColor)
			new BackWindow(this);
		else 
			System.exit(0);
	}	
}

