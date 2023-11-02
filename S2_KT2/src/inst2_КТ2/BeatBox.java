package inst2_КТ2;
import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

public class BeatBox {
	JPanel mainPanel;
	ArrayList<JCheckBox> checkboxList;
	Sequencer sequencer;
	Sequence sequence;
	Track track;
	MyDrawPanel drawPanel;
	JFrame theFrame;
	JDialog colorFrame;
	String[] instrumentNames = 
			{"Бас бочка", "Закрытая педальная тарелка",
			"Открытая педальная тарелка", "Акустический малый барабан", "Крэш тарелка", "Ручной молоток",
			"Альт-том", "Высокий бонго", "Маракасы", "Вистл", "Низкая конга",
			"Колокольчик", "Вибрашлеп", "Тенор-том", "Высокий агого",
			"Открытая высокая конга"};
	int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};
	int[] trackList = null;
	Color[] colors = {Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, 
			Color.WHITE, Color.YELLOW, Color.BLACK, Color.DARK_GRAY, Color.GRAY, Color.LIGHT_GRAY, new Color(100,50,150), new Color(150, 100, 50), new Color(50, 150, 100)};
	public static void main(String[] args) {
		new BeatBox().buildGUI();
	}
	public void buildGUI() {
		colorFrame = new JDialog();
		colorFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		drawPanel = new MyDrawPanel(); drawPanel.setFocusable(true);
		colorFrame.getContentPane().add(BorderLayout.CENTER, drawPanel);
		colorFrame.setSize(300, 300);
		colorFrame.setVisible(true);
		
		theFrame = new JFrame("Cyber BeatBox");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		checkboxList = new ArrayList<JCheckBox>();
		Box buttonBox = new Box(BoxLayout.Y_AXIS);
		
		JButton start = new JButton("Start");
		start.addActionListener(new MyStartListener());
		buttonBox.add(start);
		
		JButton stop = new JButton("Stop");
		stop.addActionListener(new MyStopListener());
		buttonBox.add(stop);
		
		JButton upTempo = new JButton("Tempo up");
		upTempo.addActionListener(new MyUpTempoListener());
		buttonBox.add(upTempo);
		
		JButton downTempo = new JButton("Tempo Down");
		downTempo.addActionListener(new MyDownTempoListener());
		buttonBox.add(downTempo);
		
		JButton serializeIt = new JButton("serializeit");
		serializeIt.addActionListener(new MySendListener());
		buttonBox.add(serializeIt);
		
		JButton restore = new JButton("restore");
		restore.addActionListener(new MyReadListener());
		buttonBox.add(restore);
		
		Box nameBox = new Box(BoxLayout.Y_AXIS);
		for(int i = 0; i<16; i++) {
			nameBox.add(new Label(instrumentNames[i]));
		}
		
		background.add(BorderLayout.EAST, buttonBox);
		background.add(BorderLayout.WEST, nameBox);
		
		theFrame.getContentPane().add(background);
		
		GridLayout grid = new GridLayout(16,16);
		grid.setVgap(1);
		grid.setVgap(2);
		mainPanel = new JPanel(grid);
		background.add(BorderLayout.CENTER, mainPanel);
		
		
		for(int i = 0; i<256; i++) {
			JCheckBox c = new JCheckBox();
			c.setSelected(false);
			checkboxList.add(c);
			mainPanel.add(c);
			c.setBackground(colors[i/16]);
			c.setOpaque(true);
		}
		setUpMidi();
		sequencer.addControllerEventListener(drawPanel, instruments);
		theFrame.setBounds(50, 50, 300, 300);
		theFrame.pack();
		theFrame.setVisible(true);
	}
	class MyDrawPanel extends JPanel implements ControllerEventListener{
		Color c = Color.WHITE;
		public void controlChange(ShortMessage event) {
			colorFrame.requestFocusInWindow();
			drawPanel.requestFocus();
			int i = 0;
			for(; i<instruments.length; i++) 
				if(instruments[i]==event.getData1()) break;
			c = colors[i];
			try {Thread.sleep(50);} catch (InterruptedException e) {e.printStackTrace();}
			drawPanel.paintComponent(drawPanel.getGraphics());
		}
		public void paintComponent(Graphics g) {
				g.setColor(c);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
	}
	public void setUpMidi() {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequence = new Sequence(Sequence.PPQ, 4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(120);
		} catch(Exception e) {e.printStackTrace();}
	}
	public void buildTrackAndStart(){
		sequence.deleteTrack(track);
		track = sequence.createTrack();
		for(int i = 0; i<16; i++) {
			trackList = new int[16];
			int key = instruments[i];
			for(int j = 0; j<16; j++) {
				JCheckBox jc = (JCheckBox) checkboxList.get(j+(16*i));
				if(jc.isSelected()) {
					trackList[j] = key;
				} else {
					trackList[j] = 0;
				}
			}
			makeTracks(trackList);
			track.add(makeEvent(176, 1, 127, 0, 16));
		}
		track.add(makeEvent(192, 9, 1, 0, 15));
		try {
			sequencer.setSequence(sequence);
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
			sequencer.setTempoInBPM(120);
		} catch(Exception e) {e.printStackTrace();}
	}
	public class MyStartListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			buildTrackAndStart();
		}
	}
	public class MyStopListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			sequencer.stop();
		}
	}
	public class MyUpTempoListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor*1.03));
		}
	}
	public class MyDownTempoListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor*0.97));
		}
	}
	public void makeTracks(int[] list) {
		for(int i = 0; i<16; i++) {
			int key = list[i];
			if(key!=0) {
				track.add(makeEvent(176, 1, key, 0, i));
				track.add(makeEvent(144,9,key,100,i));
				track.add(makeEvent(128,9,key,100,i+1));
			}
		}
	}
	public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
		} catch(Exception e) {e.printStackTrace();}
		return event;
	}
	public class MySendListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			boolean[] checkboxState = new boolean[256];
			for(int i = 0; i<256; i++) {
				JCheckBox check = (JCheckBox)checkboxList.get(i);
				if(check.isSelected()) checkboxState[i] = true;
			}
			try {
				FileOutputStream fileStream = new FileOutputStream(new File("Checkbox.ser"));
				ObjectOutputStream os = new ObjectOutputStream(fileStream);
				os.writeObject(checkboxState);
			} catch(Exception ex) {ex.printStackTrace();}
		}
	}
	public class MyReadListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			boolean[] checkboxState = null;
			try {
				FileInputStream fileIn = new FileInputStream(new File("Checkbox.ser"));
				ObjectInputStream is = new ObjectInputStream(fileIn);
				checkboxState = (boolean[]) is.readObject();
			} catch(Exception ex) {ex.printStackTrace();}
			for(int i = 0; i<256; i++) {
				JCheckBox check = (JCheckBox) checkboxList.get(i);
				if(checkboxState[i]) check.setSelected(true);
				else check.setSelected(false);
			}
			sequencer.stop();
			buildTrackAndStart();
 		}
	}
}










































