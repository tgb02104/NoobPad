package com.pad.noob;

import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class AboutWindow {
	
	public AboutWindow(NoobPad noobPad) {
		JDialog frame = new JDialog();
		frame.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
		frame.setIconImage(noobPad.getLogo().getImage());
		frame.add(new JLabel(noobPad.getLogoX2()));
		
		frame.add(new JLabel("<html><body>제작자 : babibux<br>깃허브 : <a href=\" https://github.com/babibux/NoobPad\">https://github.com/babibux/NoobPad</a><br>수정자 : 최민우<br>수정내용 : 아이콘변경, 한글화, 정보란 수정, 서식툴(배경색상, 글자색상 변경) </body></html>"));
		frame.setTitle("정보"); 
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}

}
