package lab_16;
import javax.swing.*;
import java.awt.*;

public class ex_4 {
	public static void main(String[] args){
		ex_4 gui = new ex_4();
		gui.go();
	}
	public void go() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		
		panel.setBackground(Color.darkGray);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JButton button = new JButton("shock me");
		JButton button2 = new JButton("bliss");
		
		panel.add(button);
		panel.add(button2);
		
		frame.getContentPane().add(BorderLayout.EAST, panel);
		frame.setSize(250, 200);
		frame.setVisible(true);
	}
}
