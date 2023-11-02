package com.mycompany.inst_14;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TwoButtons {
	JFrame frame;
	JLabel label;
	
	public static void main(String[] args){
		TwoButtons gui = new TwoButtons();
		gui.go();
	}
	public void go(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton labelButton = new JButton("Change Label");
		labelButton.addActionListener(new LabelListener());
		
		JButton colorButton = new JButton("Change color");
		colorButton.addActionListener(new ColorListener());
		
		label = new JLabel("im a label");
		MyDrawPanel drawPanel = new MyDrawPanel();
		
		frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
		frame.getContentPane().add(BorderLayout.EAST, labelButton);
		frame.getContentPane().add(BorderLayout.WEST, label);
		
		frame.setSize(600, 350);
		frame.setVisible(true);
	}
	class LabelListener implements ActionListener{
		public void actionPerformed(ActionEvent event)
		{
			label.setText("ouch---->");
		}
	}
	class ColorListener implements ActionListener{
		public void actionPerformed(ActionEvent event)
		{
			frame.repaint();
		}
	}
	public class MyDrawPanel extends JPanel{
		public void paintComponent(Graphics g){
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			int red = (int)(Math.random()*255);
			int green = (int)(Math.random()*255);
			int blue = (int)(Math.random()*255);
			
			Color randColor = new Color(red, green, blue);
			g.setColor(randColor);
			g.fillOval(70, 70, 100, 100);
			randColor = new Color(green, blue, red);
			g.setColor(randColor);
			g.fillOval(200, 50, 90, 90);
			randColor = new Color(blue, red, green);
			g.setColor(randColor);
			g.fillOval(170, 170, 70, 70);
		}
	}
}



















