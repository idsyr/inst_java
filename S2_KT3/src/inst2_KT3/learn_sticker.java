package inst2_KT3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class learn_sticker {
	public static learn_sticker ls;
	
	int W=1200;
	int H=600;
	
	ArrayList<String> listPathDict = new ArrayList<String>();
	ArrayList<String> listPathSymFilt = new ArrayList<String>();
	ArrayList<String> listPathWordFilt = new ArrayList<String>();
	
	boolean stdDictionary = true;
	boolean stdBadSimFilt = true;
	boolean stdBadWordFilt = true;
	
	Font bigFont = new Font("Courier", Font.PLAIN, 14);
	Color widget_background_theme = new Color(255,171,51);
	Color widget_fground_theme = new Color(34,34,34);
	
	JDialog Wframe;
	JPanel Wpanel, layout2R;
	JTextField field1, field2, fieldinfo;
	JPanel Spanel, layout0;
	JFrame Sframe = new JFrame("learn.sticker");
	String filePathTec = "none.txt";
	JTextField fieldFilePath = new JTextField(filePathTec);
	JTextField fieldWordFilterPath = new JTextField("none.txt");
	JTextField fieldSymbolFilterPath = new JTextField("none.txt");
	JTextField fieldDictionaryPath = new JTextField("none.txt");
	
	HashSet<String> wordsWidget;
	Iterator<String> it;
	int size; int tecSize; int genSize;
	Dictionary ed;
	
	enum layout2Rtype{
		Main,
		Generating,
		Widget,
		Catalog,
		GenUniqueStatusError,
		GenUniqueStatusComplete
	}
	public layout2Rtype page_type = layout2Rtype.Main;
	String genStatus="Status: ";
	
	public static void main(String[] args) {
		ls = new learn_sticker();
		ls.build_setings();
	}
	
	void build_widget() {
		page_type=layout2Rtype.GenUniqueStatusComplete;
		if(stdDictionary) listPathDict.add("unique of english.dictionary");
		if(stdBadSimFilt) listPathSymFilt.add("bs.filter");
		if(stdBadWordFilt) listPathWordFilt.add("bw.filter");
		
		String userSymbolFilter = fieldSymbolFilterPath.getText();
		String userWordFilter = fieldWordFilterPath.getText();
		String userDictionary = fieldDictionaryPath.getText();
		
		listPathDict.add(userDictionary);
		listPathSymFilt.add(userSymbolFilter);
		listPathWordFilt.add(userWordFilter);
		
		Filter bs = new Filter(listPathSymFilt); badGen(bs.status);
		Filter bw = new Filter(listPathWordFilt); badGen(bw.status);
		ed = new Dictionary(listPathDict); badGen(ed.status);
		
		PathManager PM = new PathManager(fieldFilePath.getText());
		
		wordsWidget = new HashSet<>();
		badGen(ReadFactory.read(wordsWidget, PM.directory + PM.file, bs, bw));
		
		if(page_type==layout2Rtype.GenUniqueStatusError) {ls.build_setings(); return;}
		
		size = wordsWidget.size();
		it = wordsWidget.iterator();
		
		Wframe = new JDialog();
		Wframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Wframe.setSize(200, 150);
		Wframe.setMaximumSize(new Dimension(200, 200));
		Wframe.setLocation(1700, 20);
		Wframe.setVisible(true);
			Wpanel = new JPanel(new GridLayout(4,1,0,0));
				field1 = new JTextField("for start");
				field1.setFont(bigFont);
				field1.setBackground(widget_background_theme);
				field1.setHorizontalAlignment(JTextField.CENTER);
				field1.setForeground(widget_fground_theme);
				
				field2 = new JTextField("click next");
				field2.setFont(bigFont);
				field2.setBackground(widget_background_theme);
				field2.setHorizontalAlignment(JTextField.CENTER);
				field2.setForeground(widget_fground_theme);
				
				JPanel stroke1 = new JPanel(); stroke1.setLayout(new GridLayout());
					JButton next = new JButton("next");
					next.setFont(bigFont);
					next.setBackground(widget_background_theme);
					next.setForeground(widget_fground_theme);
					next.addActionListener(new next());
					
					JButton setings = new JButton("setings");
					setings.setFont(bigFont);
					setings.setBackground(widget_background_theme);
					setings.setForeground(widget_fground_theme);
					setings.addActionListener(new makeMainPageFromWidgetAction());
				stroke1.add(next);
				stroke1.add(setings);
				
				fieldinfo = new JTextField(Integer.toString(tecSize)+"/"+Integer.toString(size)); tecSize++;
				fieldinfo.setFont(bigFont);
				fieldinfo.setBackground(widget_background_theme);
				fieldinfo.setHorizontalAlignment(JTextField.CENTER);
				fieldinfo.setForeground(widget_fground_theme);
			Wpanel.add(field1);
			Wpanel.add(field2);
			Wpanel.add(stroke1);
			Wpanel.add(fieldinfo);
		Wframe.add(Wpanel);
	}
	
	public void build_setings() {
		Sframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			layout0 = new JPanel(new BorderLayout());
				JPanel stroke0 = new JPanel(new GridLayout());
					JButton runWidget = new JButton("run widget");
					runWidget.setFont(bigFont);
					runWidget.setBackground(new Color(255,171,51));
					runWidget.addActionListener(new startWidgetAction());
					
					JButton runUnique = new JButton("generate unique");
					runUnique.setFont(bigFont);
					runUnique.setBackground(new Color(255,171,51));
					runUnique.addActionListener(new generateUniqueAction());
				stroke0.add(runWidget);
				stroke0.add(runUnique);
				
				JPanel layout1 = new JPanel();
				layout1.setBackground(Color.BLACK);
				GridBagLayout gbl = new GridBagLayout();
				gbl.columnWeights = new double[]{1,50};
				gbl.rowWeights = new double[]{1};
				layout1.setLayout(gbl);
					JPanel layout2L = new JPanel(); layout2L.setLayout(new BoxLayout(layout2L, BoxLayout.Y_AXIS));
					layout2L.setBackground(new Color(39,39,39));
					GridBagConstraints l2L = new GridBagConstraints(); l2L.fill = GridBagConstraints.BOTH;
					
						JButton menuChoose0 = new JButton("   Main   ");
						menuChoose0.setFont(bigFont);
						menuChoose0.setAlignmentX(Component.CENTER_ALIGNMENT);
						menuChoose0.setBackground(new Color(255,171,51));
						menuChoose0.addActionListener(new makeMainPageAction());
						
						JButton menuChoose1 = new JButton("Generating");
						menuChoose1.setFont(bigFont);
						menuChoose1.setAlignmentX(Component.CENTER_ALIGNMENT);
						menuChoose1.setBackground(new Color(255,171,51));
						menuChoose1.addActionListener(new makeGeneratingPageAction());
						
						JButton menuChoose2 = new JButton("  Widget  ");
						menuChoose2.setFont(bigFont);
						menuChoose2.setAlignmentX(Component.CENTER_ALIGNMENT);
						menuChoose2.setBackground(new Color(255,171,51));
						menuChoose2.addActionListener(new makeWidgetPageAction());
						
						JButton menuChoose3 = new JButton(" Catalog  ");
						menuChoose3.setMinimumSize(new Dimension(100,100));
						menuChoose3.setFont(bigFont);
						menuChoose3.setAlignmentX(Component.CENTER_ALIGNMENT);
						menuChoose3.setBackground(new Color(255,171,51));
						menuChoose3.addActionListener(new makeCatalogPageAction());
					
					layout2L.add(Box.createVerticalStrut(20));
					layout2L.add(menuChoose0);
					layout2L.add(menuChoose1);
					layout2L.add(menuChoose2);
					layout2L.add(menuChoose3);
				GridBagConstraints l2R = new GridBagConstraints(); l2R.fill = GridBagConstraints.BOTH;
				layout1.add(layout2L, l2L);
				switch(page_type) {
				case Main: 			layout2R=makeMainPage(); 		break;
				case Generating: 	layout2R=makeGeneratingPage();	break;
				case Widget: 		layout2R=makeWidgetPage(); 		break;
				case Catalog: 		layout2R=makeCatalogPage(); 	break; 
				case GenUniqueStatusComplete: layout2R=makeCompleteGenPage(); break;
				case GenUniqueStatusError: layout2R=makeCompleteGenPage(); break;}
				layout1.add(layout2R, l2R);
			layout0.add(layout1);
			layout0.add(stroke0, "South");
		Sframe.setContentPane(layout0);
		Sframe.setSize(W, H);
		Sframe.setLocation(300, 300);
		Sframe.setVisible(true);
	}
	
	JPanel makeTestMainPage() {
		JPanel layout0 = new JPanel();layout0.setLayout(new BoxLayout(layout0, BoxLayout.Y_AXIS));
		String content = new String();
		try(FileReader reader = new FileReader("/home/ids/projects/eclipse_workspace/inst2_KT3/mainPage.txt")){
			int c;
			while((c=reader.read())!=-1){	
				content += (char)c;
		}}catch(IOException ex){}
		JEditorPane pane = new JEditorPane();
		pane.setContentType("text/html");
		pane.setText(content);
		layout0.add(pane);
		return layout0;
	}

	JPanel makeMainPage() {
		filePathTec=fieldFilePath.getText();
		String content = new String();
		try(FileReader reader = new FileReader("mainPage.txt")){
			int c;
			while((c=reader.read())!=-1){	
				content += (char)c;
		}}catch(IOException ex){}
		
		JPanel layout0 = new JPanel();layout0.setLayout(new BoxLayout(layout0, BoxLayout.Y_AXIS));
		layout0.setBackground(new Color(34,34,34));
		layout0.add(Box.createVerticalStrut(20));
			JPanel stroke1 = new JPanel(); stroke1.setLayout(new BoxLayout(stroke1, BoxLayout.X_AXIS));
			stroke1.setBackground(new Color(34,34,34));
				JTextArea field1 = new JTextArea(content);
				field1.setMaximumSize(new Dimension(435,700));
				field1.setFont(bigFont);
				field1.setLineWrap(true);
				field1.setWrapStyleWord(true);
				field1.setBackground(new Color(34,34,34));
				field1.setForeground(new Color(255,170,51));
			stroke1.add(field1);
		layout0.add(stroke1);
		
	return layout0;
	}
	
	JPanel makeGeneratingPage() {
		JPanel layout2R = new JPanel(); layout2R.setLayout(new BoxLayout(layout2R, BoxLayout.Y_AXIS));
		layout2R.setBackground(new Color(34,34,34));
			JPanel stroke1 = new JPanel(); stroke1.setLayout(new BoxLayout(stroke1, BoxLayout.X_AXIS));
				JTextField field0 = new JTextField("set path to your file:");
				field0.setMaximumSize(new Dimension(270,100));
				field0.setHorizontalAlignment(JTextField.CENTER);
				field0.setFont(bigFont);
				field0.setBackground(new Color(255,171,51));
				
				fieldFilePath = new JTextField(filePathTec);
				fieldFilePath.setFont(bigFont);
				fieldFilePath.setMaximumSize(new Dimension(300,100));
				fieldFilePath.setHorizontalAlignment(JTextField.CENTER);
				fieldFilePath.setBackground(new Color(255,171,51));
			stroke1.add(field0);	
			stroke1.add(fieldFilePath);
			
			JPanel strokeinfo1 = new JPanel(); strokeinfo1.setLayout(new BoxLayout(strokeinfo1, BoxLayout.X_AXIS));
				JLabel fieldinfo = new JLabel("your can add more then 1 filter or dictionary using the space");
				fieldinfo.setMaximumSize(new Dimension(500,100));
				fieldinfo.setHorizontalAlignment(JTextField.CENTER);
				fieldinfo.setFont(bigFont);
				fieldinfo.setOpaque(true);
				fieldinfo.setForeground(new Color(255,170,51));
				fieldinfo.setBackground(new Color(34,34,34));
			strokeinfo1.add(fieldinfo);
			
			JPanel stroke2 = new JPanel(); stroke2.setLayout(new BoxLayout(stroke2, BoxLayout.X_AXIS));
				JTextField field3 = new JTextField("set path to your word filter:");
				field3.setMaximumSize(new Dimension(270,100));
				field3.setHorizontalAlignment(JTextField.CENTER);
				field3.setFont(bigFont);
				field3.setBackground(new Color(255,171,51));
				
				//fieldWordFilterPath = new JTextField("");
				fieldWordFilterPath.setFont(bigFont);
				fieldWordFilterPath.setMaximumSize(new Dimension(300,100));
				fieldWordFilterPath.setHorizontalAlignment(JTextField.CENTER);
				fieldWordFilterPath.setBackground(new Color(255,171,51));
			stroke2.add(field3);
			stroke2.add(fieldWordFilterPath);
			
			JPanel stroke3 = new JPanel(); stroke3.setLayout(new BoxLayout(stroke3, BoxLayout.X_AXIS));
				JTextField field5 = new JTextField("set path to your symbol filter:");
				field5.setMaximumSize(new Dimension(270,100));
				field5.setHorizontalAlignment(JTextField.CENTER);
				field5.setFont(bigFont);
				field5.setBackground(new Color(255,171,51));
				
				//fieldSymbolFilterPath = new JTextField("");
				fieldSymbolFilterPath.setFont(bigFont);
				fieldSymbolFilterPath.setMaximumSize(new Dimension(300,100));
				fieldSymbolFilterPath.setHorizontalAlignment(JTextField.CENTER);
				fieldSymbolFilterPath.setBackground(new Color(255,171,51));
			stroke3.add(field5);
			stroke3.add(fieldSymbolFilterPath);
			
			JPanel stroke4 = new JPanel(); stroke4.setLayout(new BoxLayout(stroke4, BoxLayout.X_AXIS));
				JTextField field7 = new JTextField("set path to your dictionary:");
				field7.setMaximumSize(new Dimension(270,100));
				field7.setHorizontalAlignment(JTextField.CENTER);
				field7.setFont(bigFont);
				field7.setBackground(new Color(255,171,51));
				
				//fieldDictionaryPath = new JTextField("");
				fieldDictionaryPath.setFont(bigFont);
				fieldDictionaryPath.setMaximumSize(new Dimension(300,100));
				fieldDictionaryPath.setHorizontalAlignment(JTextField.CENTER);
				fieldDictionaryPath.setBackground(new Color(255,171,51));
			stroke4.add(field7);
			stroke4.add(fieldDictionaryPath);
		layout2R.add(Box.createVerticalStrut(20));	
		layout2R.add(stroke1);
		layout2R.add(Box.createVerticalStrut(10));
		layout2R.add(strokeinfo1);
		layout2R.add(Box.createVerticalStrut(10));
		layout2R.add(stroke2);
		layout2R.add(Box.createVerticalStrut(10));
		layout2R.add(stroke3);
		layout2R.add(Box.createVerticalStrut(10));
		layout2R.add(stroke4);
		layout2R.add(Box.createVerticalGlue());
	return layout2R;
	}
	
	JPanel makeWidgetPage() {
		filePathTec=fieldFilePath.getText();
		JPanel layout2R = new JPanel(); layout2R.setLayout(new BoxLayout(layout2R, BoxLayout.Y_AXIS));
		layout2R.setBackground(new Color(34,34,34));
			JPanel stroke1 = new JPanel(); stroke1.setLayout(new BoxLayout(stroke1, BoxLayout.X_AXIS));
				JButton menuChoose0 = new JButton("set dark theme");
				menuChoose0.setFont(bigFont);
				menuChoose0.setAlignmentX(Component.CENTER_ALIGNMENT);
				menuChoose0.setBackground(new Color(255,171,51));
				menuChoose0.addActionListener(new makeDarkWidgetThemeAction());
				
				JButton menuChoose1 = new JButton("set yelow theme");
				menuChoose1.setFont(bigFont);
				menuChoose1.setAlignmentX(Component.CENTER_ALIGNMENT);
				menuChoose1.setBackground(new Color(255,171,51));
				menuChoose1.addActionListener(new makeYellowWidgetThemeAction());
			stroke1.add(menuChoose0);	
			stroke1.add(menuChoose1);
		layout2R.add(Box.createVerticalStrut(20));	
		layout2R.add(stroke1);
		layout2R.add(Box.createVerticalStrut(10));
		layout2R.add(Box.createVerticalGlue());
	return layout2R;
	}
	
	JPanel makeCatalogPage() {
		filePathTec=fieldFilePath.getText();
		JPanel layout2R1 = new JPanel(); layout2R1.setLayout(new BoxLayout(layout2R1, BoxLayout.Y_AXIS));
		layout2R1.setBackground(new Color(34,34,34));
			JPanel layout2R = new JPanel(); layout2R.setLayout(new BoxLayout(layout2R, BoxLayout.Y_AXIS));
			layout2R.setBackground(new Color(34,34,34));
			JScrollPane vertical = new JScrollPane(layout2R);
			vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			vertical.getVerticalScrollBar().setBackground(new Color(34,34,34));
			vertical.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			    @Override
			    protected void configureScrollBarColors() {
			        this.thumbColor = new Color(255,171,51);
			        this.trackHighlightColor = new Color(255,171,51);
			    }
			});
				
				JPanel strokeinfo1 = new JPanel(); strokeinfo1.setLayout(new BoxLayout(strokeinfo1, BoxLayout.X_AXIS));
					JLabel fieldinfo = new JLabel("Here you will find ready-made word sets");
					fieldinfo.setMaximumSize(new Dimension(500,100));
					fieldinfo.setHorizontalAlignment(JTextField.CENTER);
					fieldinfo.setFont(bigFont);
					fieldinfo.setOpaque(true);
					fieldinfo.setForeground(new Color(255,170,51));
					fieldinfo.setBackground(new Color(34,34,34));
				strokeinfo1.add(fieldinfo);
				
				JPanel stroke1 = new JPanel(); stroke1.setLayout(new BoxLayout(stroke1, BoxLayout.X_AXIS));
					JLabel l1 = new JLabel(new ImageIcon("thunderLogo.jpg"));
					l1.setAlignmentX(Component.CENTER_ALIGNMENT);
	
					JButton menuChoose1 = new JButton("set");
					menuChoose1.setFont(bigFont);
					menuChoose1.setAlignmentX(Component.CENTER_ALIGNMENT);
					menuChoose1.setBackground(new Color(255,171,51));
					menuChoose1.addActionListener(new setBook1Action());
					JPanel buttonsgrid1 = new JPanel(new GridLayout(1,1,1,1));
					buttonsgrid1.add(menuChoose1);
					buttonsgrid1.setMaximumSize(new Dimension(50,500));
				stroke1.add(l1);	
				stroke1.add(buttonsgrid1);
				
				JPanel stroke2 = new JPanel(); stroke2.setLayout(new BoxLayout(stroke2, BoxLayout.X_AXIS));
					JLabel l2 = new JLabel(new ImageIcon("rainLogo.jpg"));
					l2.setAlignmentX(Component.CENTER_ALIGNMENT);
	
					JButton menuChoose2 = new JButton("set");
					menuChoose2.setFont(bigFont);
					menuChoose2.setAlignmentX(Component.CENTER_ALIGNMENT);
					menuChoose2.setBackground(new Color(255,171,51));
					menuChoose2.addActionListener(new setBook2Action());
					JPanel buttonsgrid2 = new JPanel(new GridLayout(1,1,1,1));
					buttonsgrid2.add(menuChoose2);
					buttonsgrid2.setMaximumSize(new Dimension(50,500));
				stroke2.add(l2);	
				stroke2.add(buttonsgrid2);
				
				JPanel stroke3 = new JPanel(); stroke3.setLayout(new BoxLayout(stroke3, BoxLayout.X_AXIS));
					JLabel l3 = new JLabel(new ImageIcon("cpuLogo.jpg"));
					l3.setAlignmentX(Component.CENTER_ALIGNMENT);
	
					JButton menuChoose3 = new JButton("set");
					menuChoose3.setFont(bigFont);
					menuChoose3.setAlignmentX(Component.CENTER_ALIGNMENT);
					menuChoose3.setBackground(new Color(255,171,51));
					menuChoose3.addActionListener(new setBook3Action());
					JPanel buttonsgrid3 = new JPanel(new GridLayout(1,1,1,1));
					buttonsgrid3.add(menuChoose3);
					buttonsgrid3.setMaximumSize(new Dimension(50,500));
				stroke3.add(l3);	
				stroke3.add(buttonsgrid3);
			
			layout2R.add(Box.createVerticalStrut(10));	
			layout2R.add(strokeinfo1);
			layout2R.add(Box.createVerticalStrut(10));	
			layout2R.add(stroke1);
			layout2R.add(Box.createVerticalStrut(10));
			layout2R.add(stroke2);
			layout2R.add(Box.createVerticalStrut(10));
			layout2R.add(stroke3);
			layout2R.add(Box.createVerticalStrut(10));
			layout2R.add(Box.createVerticalGlue());
		layout2R1.add(vertical); layout2R1.setLayout(new BoxLayout(layout2R1, BoxLayout.Y_AXIS));
	return layout2R1;
	}
	
	JPanel makeCompleteGenPage() {
		JPanel layout0 = new JPanel();layout0.setLayout(new BoxLayout(layout0, BoxLayout.Y_AXIS));
		layout0.setBackground(new Color(34,34,34));
		layout0.add(Box.createVerticalStrut(20));
			JPanel stroke1 = new JPanel(); stroke1.setLayout(new BoxLayout(stroke1, BoxLayout.X_AXIS));
			stroke1.setBackground(new Color(34,34,34));
				JTextArea field1 = new JTextArea(); String status1;
				if(page_type==layout2Rtype.GenUniqueStatusComplete) {
					status1="now you have "+Integer.toString(genSize)+" unique words";
					field1 = new JTextArea("Generation complete!\n"+status1);
				} else field1 = new JTextArea("Error: "+genStatus);
				field1.setMaximumSize(new Dimension(435,700));
				field1.setFont(bigFont);
				field1.setLineWrap(true);
				field1.setWrapStyleWord(true);
				field1.setForeground(new Color(255,170,51));
				field1.setBackground(new Color(34,34,34));
			stroke1.add(field1);
		layout0.add(stroke1);
	return layout0;
	}
	
	public void badGen(String in) {
		Boolean ok = true;
		for(int i = 0; i<in.length(); i++) {
			if(in.charAt(i)!='+') {ok=false; break;}
		}
		if(!ok) {
			page_type = layout2Rtype.GenUniqueStatusError;
			genStatus+=in;
		}
	}
	
	public class startWidgetAction implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			W=Sframe.getWidth();
			H=Sframe.getHeight();
			Sframe.dispose();
			ls.build_widget();
		}
	}
	
	public class generateUniqueAction implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			long time = System.currentTimeMillis();			
			page_type = layout2Rtype.GenUniqueStatusComplete;
			
			if(stdDictionary)
				listPathDict.add("unique of english.dictionary");
			if(stdBadSimFilt)
				listPathSymFilt.add("bs.filter");
			if(stdBadWordFilt)
				listPathWordFilt.add("bw.filter");
			
			Dictionary ed = new Dictionary(listPathDict); badGen(ed.status);
			Filter bs = new Filter(listPathSymFilt); badGen(bs.status);
			Filter bw = new Filter(listPathWordFilt); badGen(bw.status);
			
			PathManager PM = new PathManager(fieldFilePath.getText());
			
			HashSet<String> words = new HashSet<>();
			badGen(ReadFactory.read(words, PM.directory + PM.file, bs, bw));
			badGen(WriteFactory.write(words, PM.directory + "unique of " + PM.file, null));
			genSize= words.size();
			words = null;
			
			HashSet<String> words2 = new HashSet<>();
			badGen(ReadFactory.read(words2, PM.directory + "unique of " + PM.file, bs, bw));
			badGen(WriteFactory.write(words2, PM.directory + "translate of unique of " + PM.file, ed));
			words2 = null;
			
			listPathDict = new ArrayList<String>();
			listPathSymFilt = new ArrayList<String>();
			listPathWordFilt = new ArrayList<String>();
			
			System.out.println(System.currentTimeMillis()-time);
			
			build_setings();
		}
	}
	
	public class next implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			if(tecSize>size) fieldinfo.setText("finish!");
			else fieldinfo.setText(Integer.toString(tecSize)+"/"+Integer.toString(size));
			if(it.hasNext()) {String text = it.next(); field1.setText(text);tecSize++;field2.setText(WriteFactory.translate_word(ed, text));
			HashSet<String> temp = new HashSet<String>(); temp.add(text); WriteFactory.write(temp, "bw.filter", null);
			}
		}
	}
	
	public class makeMainPageFromWidgetAction implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			Wframe.dispose();
			listPathDict = new ArrayList<String>();
			listPathSymFilt = new ArrayList<String>();
			listPathWordFilt = new ArrayList<String>();
			page_type = layout2Rtype.Main;
			build_setings();
			tecSize = 0;
		}
	}
	
	public class makeMainPageAction implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			page_type = layout2Rtype.Main;
			W=Sframe.getWidth();
			H=Sframe.getHeight();
			build_setings();
		}
	}
	
	public class makeGeneratingPageAction implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			page_type = layout2Rtype.Generating;
			W=Sframe.getWidth();
			H=Sframe.getHeight();
			build_setings();
		}
	}
	
	public class makeWidgetPageAction implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			page_type = layout2Rtype.Widget;
			W=Sframe.getWidth();
			H=Sframe.getHeight();
			build_setings();
		}
	}
	
	public class makeCatalogPageAction implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			page_type = layout2Rtype.Catalog;
			W=Sframe.getWidth();
			H=Sframe.getHeight();
			build_setings();
		}
	}
	
	public class makeDarkWidgetThemeAction implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			widget_background_theme = new Color(34,34,34);
			widget_fground_theme = new Color(255,171,51);
		}
	}
	
	public class makeYellowWidgetThemeAction implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			widget_background_theme = new Color(255,171,51);
			widget_fground_theme = new Color(34,34,34);
		}
	}
	
	public class setBook1Action implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			filePathTec = "ASoundOfThunder.txt";
			fieldFilePath.setText(filePathTec);
		}
	}
	
	public class setBook2Action implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			filePathTec = "ThereWillComeSoftRains.txt";
			fieldFilePath.setText(filePathTec);
		}
	}
	
	public class setBook3Action implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			filePathTec = "CPUMemory.txt";
			fieldFilePath.setText(filePathTec);
		}
	}
}






























