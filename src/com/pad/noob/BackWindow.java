package com.pad.noob;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BackWindow implements ActionListener{
	 private Color color[]= {Color.red,Color.BLACK,Color.gray,Color.BLUE};
	 private final static Logger LOG = Logger.getGlobal();
	    JButton[] bt = new JButton[4];
	public BackWindow(NoobPad noobPad) {
		 JFrame fr = new JFrame("배경 컬러 선택창");
		
	        JPanel pn = new JPanel();
	    

	        GridLayout gl = new GridLayout(2, 2);
	        pn.setLayout(gl);

	        for (int i = 0; i < bt.length; i++) {
	            bt[i] = new JButton();
	            bt[i].setBackground(color[i]);
	            pn.add(bt[i]);
	            bt[i].addActionListener(this);
	        }

	        pn.add(bt[0]);
	        pn.add(bt[1]);
	        pn.add(bt[2]);
	        pn.add(bt[3]);

	        
	        
	        
	        fr.setContentPane(pn);

	        fr.setSize(400, 300);
	        fr.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		//액션 리스너 재정의
		if (e.getSource().equals(bt[0]))
		{
			NoobPad.textArea.setBackground(color[0]);
		

		}
		
		else if (e.getSource().equals(bt[1]))
		{
			NoobPad.textArea.setBackground(color[1]);
		
		}
		else if (e.getSource().equals(bt[2]))
		{
			NoobPad.textArea.setBackground(color[2]);
			
		}
		
		else if (e.getSource().equals(bt[3]))
		{
			NoobPad.textArea.setBackground(color[3]);
			
		}
		
	}
}
