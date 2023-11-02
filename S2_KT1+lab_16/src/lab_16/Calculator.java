package lab_16;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Calculator implements ActionListener{
	JFrame frame;
	JPanel panel, p1, p2, p3, p4, north;
	JTextField field;
	JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, bp, bc, bplus, bminus, bdel, bmnoj, bfin;
	ArrayList<JButton> b;
	double a1, a2;
	String move = "";
	int pos;
	public static void main(String[] args){
		Calculator gui = new Calculator();
		gui.go();
	}
	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		field = new JTextField(12);
		p1 = new JPanel(); p2 = new JPanel(); p3 = new JPanel(); p4 = new JPanel();
		north = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
		p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
		p4.setLayout(new BoxLayout(p4, BoxLayout.Y_AXIS));
		
		b = new ArrayList<JButton>();
		String[] sim = {"   0   ", "   1   ", "   2   ", "   3   ", "   4   ", "   5   ",
				"   6   ", "   7   ", "   8   ", "   9   ", "   .    ", "   C   ", "   +   ", "   -    ", "   /   ", "   *   ", "   =   "};
		for(int i = 0; i<17; i++) {
			b.add(i, new JButton(sim[i]));
			b.get(i).addActionListener(this);
			b.get(i).setSize(300,300);
		}
		north.add(field);
		p1.add(b.get(1)); p1.add(b.get(4)); p1.add(b.get(7)); p1.add(b.get(10));
		p2.add(b.get(2)); p2.add(b.get(5)); p2.add(b.get(8)); p2.add(b.get(0));
		p3.add(b.get(3)); p3.add(b.get(6)); p3.add(b.get(9)); p3.add(b.get(11));
		north.add(b.get(16)); p4.add(b.get(12)); p4.add(b.get(13)); p4.add(b.get(14)); p4.add(b.get(15));
		//panel.add(field);
		panel.add(p1); panel.add(p2); panel.add(p3); panel.add(p4);
		
//		for(JButton a : b)
//		a.setPreferredSize(new Dimension(100,20));
		
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.getContentPane().add(BorderLayout.NORTH, north);
		
		frame.setSize(350, 250);
		frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource()==b.get(0)) {field.setText(field.getText()+"0");}
		if(event.getSource()==b.get(1)) {field.setText(field.getText()+"1");}
		if(event.getSource()==b.get(2)) {field.setText(field.getText()+"2");}
		if(event.getSource()==b.get(3)) {field.setText(field.getText()+"3");}
		if(event.getSource()==b.get(4)) {field.setText(field.getText()+"4");}
		if(event.getSource()==b.get(5)) {field.setText(field.getText()+"5");}
		if(event.getSource()==b.get(6)) {field.setText(field.getText()+"6");}
		if(event.getSource()==b.get(7)) {field.setText(field.getText()+"7");}
		if(event.getSource()==b.get(8)) {field.setText(field.getText()+"8");}
		if(event.getSource()==b.get(9)) {field.setText(field.getText()+"9");}
		if(event.getSource()==b.get(10)) {field.setText(field.getText()+".");}
		if(event.getSource()==b.get(11)) {field.setText("");}
		if(event.getSource()==b.get(12))
			{a1 = Double.parseDouble(field.getText()); field.setText(field.getText()+"+"); move = "+"; pos=field.getText().length();}
		if(event.getSource()==b.get(13))
			{a1 = Double.parseDouble(field.getText()); field.setText(field.getText()+"-"); move = "-"; pos=field.getText().length();}
		if(event.getSource()==b.get(14))
			{a1 = Double.parseDouble(field.getText()); field.setText(field.getText()+"/"); move = "/"; pos=field.getText().length();}
		if(event.getSource()==b.get(15))
			{a1 = Double.parseDouble(field.getText()); field.setText(field.getText()+"*"); move = "*"; pos=field.getText().length();}
		if(event.getSource()==b.get(16)) {	
			a2 = Double.parseDouble(field.getText().substring(pos));
			switch(move){
			case"+": 
				field.setText(field.getText()+"="+(a1+a2));
				break;
			case"-": 
				field.setText(field.getText()+"="+(a1-a2));
				break;
			case"/": 
				field.setText(field.getText()+"="+(a1/a2));
				break;
			case"*": 
				field.setText(field.getText()+"="+(a1*a2));
				break;
			}
		}
	}
}
















