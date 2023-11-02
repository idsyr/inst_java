package lab_16;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Calc_CV extends KeyAdapter implements ActionListener{
	JFrame frame;
	JPanel panel, north;
	JTextField field;
	ArrayList<JButton> b;

	public static void main(String[] args){
		Calc_CV gui = new Calc_CV();
		gui.go();
	}
	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		north = new JPanel(new GridLayout(1,2,5,5));
		panel = new JPanel();
		field = new JTextField();
		field.setFocusable(false);

		Font bigFont = new Font("inconsolata", Font.BOLD, 28);
		Color randColor = new Color(255, 170, 51);
		b = new ArrayList<JButton>();
		String[] sim = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "c", "+", "-", "/", "*", "(", ")", "<-", "="};
		for(int i = 0; i<20; i++) {
			b.add(i, new JButton(sim[i]));
			b.get(i).addActionListener(this);
			b.get(i).setBackground(randColor);
			b.get(i).setFont(bigFont);
			b.get(i).addKeyListener(this);
			
		}
		field.setFont(bigFont);
		GridLayout layout = new GridLayout(5,4,5,5);
		panel.setLayout(layout);

		north.add(field); 
		panel.add(b.get(1));  panel.add(b.get(2));  panel.add(b.get(3));  panel.add(b.get(12)); 
		panel.add(b.get(4));  panel.add(b.get(5));  panel.add(b.get(6));  panel.add(b.get(13)); 
		panel.add(b.get(7));  panel.add(b.get(8));  panel.add(b.get(9));  panel.add(b.get(14)); 
		panel.add(b.get(10)); panel.add(b.get(0));  panel.add(b.get(11)); panel.add(b.get(15));
		panel.add(b.get(16)); panel.add(b.get(17)); panel.add(b.get(18)); panel.add(b.get(19));
		
		JPanel east = new JPanel(); 
		JPanel west = new JPanel(); 
		JPanel south = new JPanel(); 

		Box vert = Box.createVerticalBox();
		vert.add(north); 
		vert.add(Box.createRigidArea(new Dimension(5,5)));
		vert.add(panel);

		north.setPreferredSize(new Dimension(300,72));
		north.setMaximumSize(new Dimension(1920, 400));
		panel.setPreferredSize(new Dimension(300, 375));
		
		frame.getContentPane().add(BorderLayout.EAST, east);
		frame.getContentPane().add(BorderLayout.WEST, west);
		frame.getContentPane().add(BorderLayout.SOUTH, south);
		frame.getContentPane().add(BorderLayout.CENTER, vert);
		frame.getContentPane().add(BorderLayout.NORTH, new JPanel());
		
		frame.setSize(320, 505);
		frame.setVisible(true);
	}
	public void keyReleased(KeyEvent e) {
		char s = e.getKeyChar();
		if(Character.isDigit(s) || s=='*' || s=='/' || s=='+' || s=='-' || s=='(' || s==')' )
        	field.setText(field.getText()+s);
        if(s=='=') calc();
        if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE) {field.setText(field.getText().substring(0, field.getText().length()-1));}
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
		if(event.getSource()==b.get(12)) {field.setText(field.getText()+"+");}
		if(event.getSource()==b.get(13)) {field.setText(field.getText()+"-");}
		if(event.getSource()==b.get(14)) {field.setText(field.getText()+"/");}
		if(event.getSource()==b.get(15)) {field.setText(field.getText()+"*");}
		if(event.getSource()==b.get(16)) {field.setText(field.getText()+"(");}
		if(event.getSource()==b.get(17)) {field.setText(field.getText()+")");}
		if(event.getSource()==b.get(18)) {field.setText(field.getText().substring(0, field.getText().length()-1));}
		if(event.getSource()==b.get(19)) {	calc();	}
	}
	public void calc() {
		String prog = field.getText();
		prog = "("+prog+")";
		int cl1 = 0;
		int cl2 = 0;
		ArrayList<Integer> lays = new ArrayList<Integer>();
		for(int i = 0; i<prog.length(); i++) {
			if(prog.charAt(i)=='(') {cl1++; lays.add(i);}
			if(prog.charAt(i)==')') {cl2++;}
		}
		if(cl1!=cl2) {field.setText("Error: layout problem in input"); return;}
		
		for(int il = lays.size()-1;il>-1; il--) {
		  while(true) {
			String a = ""; String b = ""; char move = '!'; double a1 = 0; double a2 = 0; int tec1 = lays.get(il)+1; int tec2 = 0;boolean amom = true;
			for(int i = lays.get(il)+1;prog.charAt(i)!=')';i++) {
				if(amom) {
					if(prog.charAt(i)=='+'||(prog.charAt(i)=='-'&&a!="")) {a = ""; tec1 = i+1; continue;}
					if(prog.charAt(i)=='*'||prog.charAt(i)=='/') {amom = false; move = prog.charAt(i); continue;}
					a+=prog.charAt(i);
				}
				else {
					if(prog.charAt(i)=='+'||(prog.charAt(i)=='-'&&b!="")||prog.charAt(i)=='*'||prog.charAt(i)=='/') { break;}
					b+=prog.charAt(i);tec2 = i;
				}
			}
			if(move=='!') {field.setText(prog); break;}
			if(a==""||b=="") {field.setText("Error: missing number in input"); return;}
			a1 = Double.parseDouble(a);
			a2 = Double.parseDouble(b);
			double answer;
			if(move=='*') answer = a1*a2;
			else answer = a1/a2;
			prog = prog.substring(0, tec1) + Double.toString(answer) + prog.substring(tec2+1, prog.length());
		  }
		  while(true) {
				String a = ""; String b = ""; char move = '!'; double a1 = 0; double a2 = 0; int tec1 = lays.get(il)+1; int tec2 = 0;boolean amom = true;
				for(int i = lays.get(il)+1;prog.charAt(i)!=')';i++) {
					if(amom) {
						if(prog.charAt(i)=='+'||(prog.charAt(i)=='-'&&a!="")) {amom = false; move = prog.charAt(i); continue;}
						a+=prog.charAt(i);tec2 = i;
					}
					else {
						if(prog.charAt(i)=='+'||(prog.charAt(i)=='-'&&b!="")) { break;}
						b+=prog.charAt(i);tec2 = i;
					}
				}
				String s1 = prog.substring(0, tec1-1);
				String s2 = prog.substring(tec1, tec2+1);
				String s3 = prog.substring(tec2+2, prog.length());
				if(move=='!') {prog = s1+s2+s3;field.setText(prog); break;}
				if(a==""||b=="") {field.setText("Error: missing number in input"); return;}
				a1 = Double.parseDouble(a);
				a2 = Double.parseDouble(b);
				double answer;
				if(move=='+') answer = a1+a2;
				else answer = a1-a2;
				prog = prog.substring(0, tec1) + Double.toString(answer) + prog.substring(tec2+1, prog.length());
			  }
		}

	}

}


















