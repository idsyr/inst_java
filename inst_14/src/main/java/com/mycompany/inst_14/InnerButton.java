package com.mycompany.inst_14;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InnerButton {
	JFrame frame;
	JButton b;
	
	public static void main(String[] args)
	{
		InnerButton gui = new InnerButton();
		gui.go();
	}
	
	public void go()
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		b = new JButton("A");
		b.addActionListener(new BListener()); //add argument: new BListener()
		
		frame.getContentPane().add(BorderLayout.SOUTH, b);
		frame.setSize(200, 100);
		frame.setVisible(true);
	}
	
	class BListener implements ActionListener{ //change extents: implements
		public void actionPerformed(ActionEvent e){
			if(b.getText().equals("A"))
				b.setText("B");
			else
				b.setText("A");
		}
	}
	
}




















