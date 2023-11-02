package lab_16;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Calculator3 implements ActionListener{
	ArrayList<JButton> b;
	JTextField field;
	public static void main(String[] args) {
		Calculator3 gui = new Calculator3();
		gui.go();
	}
	public void go() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel(null);
		field = new JTextField(20);
		field.setBounds(0, 0, 100, 100);
		panel.add(field);
		
		b = new ArrayList<JButton>();
		String[] sim = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "c", "+", "-", "/", "*", "="};
		for(int i = 0; i<17; i++) {
			b.add(i, new JButton(sim[i]));
			b.get(i).addActionListener(this);
			panel.add(b.get(i));
		}
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setSize(400, 300);
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