package lab_16;
import javax.swing.*;
import java.awt.*;

public class ex_1 {
	public static void main(String[] args) {
		ex_1 gui = new ex_1();
		gui.go();
	}
	public void go() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton button = new JButton("click me");
		JButton button2 = new JButton("click click click");
		Font bigFont = new Font("serif", Font.BOLD, 28);
		button.setFont(bigFont);
		frame.getContentPane().add(BorderLayout.NORTH, button);
		frame.getContentPane().add(BorderLayout.EAST, button2);
		frame.setSize(200, 200);
		frame.setVisible(true);
	}
}
