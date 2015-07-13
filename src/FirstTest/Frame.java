package FirstTest;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.*;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import jxl.*;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class Frame implements ActionListener {

	double a;
	private JLabel label1;
	private JLabel label2;
	private JButton but;
	JTextField textfield1;
	JTextField textfield2;
	JTextField textfield3;
	JTextField textfield4;
	JTextField textfield5;
	JTextField textfield6;
	JTextField textfield7;
	JPanel pan;
	JPanel pan2;
	JPanel pan3;
	JPanel pan1a2;
	JLabel saveLab;
	JFrame f;
	XYLineChart_AWT chart;
	ChartPanel chartPanel;
	JButton butSave;
	File file;
	JPanel savePanel;
	JTextField saveText;
	JButton saveButton;
	
	int i=0;
	double data[][]=
		 {{ 1, 3, 6, 2, 9, 5, 4, 7, 0, 8 },
		  { 1, 5, 3, 9, 4, 2, 7, 5, 9, 6 },
		  { 0.7, 0.6, 0.9, 0.8, 0.7, 0.7, 0.8, 0.7, 0.6, 0.8 },
		  { 9.0, 8.7, 8.9, 9.3, 9.1, 9.2, 8.8, 9.0, 9.1, 8.9 }
		};
	
	Timer timer = new Timer(500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt2) {
        	textfield3.setText(getDate());
        	
        	textfield4.setText(String.valueOf(data[0][i]));
        	textfield5.setText(String.valueOf(data[1][i]));
        	textfield6.setText(String.valueOf(data[2][i]));
        	textfield7.setText(String.valueOf(data[3][i]));
        	i++;
        	if (i==9) i=0;
        	
        	
        	
        }
    });

	public Frame() {

		// Frame
		f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLayout(new FlowLayout());

		// Labels & textfields
		label1 = new JLabel("y = exp(ax)");
		label2 = new JLabel("a = ");
		saveLab = new JLabel("Name = ");
		textfield1 = new JTextField(4);
		textfield2 = new JTextField(4);
		saveText = new JTextField(5);
		saveText.setText("Smash");

		// Buttons
		saveButton = new JButton("Save");
		saveButton.addActionListener((ActionListener) this);
		but = new JButton("Calculate");
		but.addActionListener((ActionListener) this);

		// Chart
		chart = new XYLineChart_AWT("Interface", "SMASH", 1);
		chartPanel = chart.getChart();

		// Panel
		savePanel = new JPanel();
		pan = new JPanel();
		pan2 = new JPanel();

		this.createPanels(); // create the panels

		// Add into the frame
		f.add(pan1a2);
		f.add(pan3);
		f.pack();
		
		timer.setRepeats(true);
	    timer.setCoalesce(true);
	    timer.setInitialDelay(0);
	    timer.start();
	    

	}

	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss  MM/dd/yy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	private void setInfo() {
		
	    	  textfield3.setText(getDate());  
		
	}

	private void createPanels() {
		this.createPanelInfo(); // create the panel with the info (voltage,...)

		pan2.setLayout(new FlowLayout());
		pan2.add(label1);
		pan2.add(but);
		pan2.add(label2);
		pan2.add(textfield1);

		pan.add(chartPanel, BorderLayout.EAST);

		pan1a2 = new JPanel(new GridLayout(1, 1));
		pan1a2.add(pan2);
		pan1a2.add(pan);
		pan1a2.setLayout(new BoxLayout(pan1a2, BoxLayout.Y_AXIS));

		savePanel.add(saveButton);
		savePanel.add(saveLab);
		savePanel.add(saveText);

		pan3.add(savePanel);
	}

	private void createPanelInfo() {
		JLabel label3 = new JLabel("TIME");
		JLabel label4 = new JLabel("CONTROL");
		JLabel label5 = new JLabel("DELTA T");
		JLabel label6 = new JLabel("CURRENT [A]");
		JLabel label7 = new JLabel("VOLTAGE [V]");

		textfield3 = new JTextField(11);
		textfield4 = new JTextField(2);
		textfield5 = new JTextField(2);
		textfield6 = new JTextField(2);
		textfield7 = new JTextField(2);

		textfield3.setEditable(false);
		textfield4.setEditable(false);
		textfield5.setEditable(false);
		textfield6.setEditable(false);
		textfield7.setEditable(false);

		setInfo();

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();

		p1.add(label3);
		p1.add(textfield3);
		p2.add(label4);
		p2.add(textfield4);
		p3.add(label5);
		p3.add(textfield5);
		p4.add(label6);
		p4.add(textfield6);
		p5.add(label7);
		p5.add(textfield7);

		pan3 = new JPanel(new GridLayout(1, 1));
		pan3.setLayout(new BoxLayout(pan3, BoxLayout.Y_AXIS));
		pan3.add(p1);
		pan3.add(p2);
		pan3.add(p3);
		pan3.add(p4);
		pan3.add(p5);

	}

	public ArrayList<String> openExcel(String inputFile)
			throws IOException {

		ArrayList<String> resultSet = new ArrayList<String>();

		File inputWorkbook = new File(inputFile);

		if (inputWorkbook.exists()) {
			Workbook w;
			try {
				w = Workbook.getWorkbook(inputWorkbook);
				// Get the first sheet
				Sheet sheet = w.getSheet(0);
				// Loop over column and lines
				for (int j = 0; j < sheet.getRows(); j++) {
					Cell cell = sheet.getCell(0, j);
					
						for (int i = 0; i < sheet.getColumns(); i++) {
							Cell cel = sheet.getCell(i, j);
							resultSet.add(cel.getContents());
						}
					
					continue;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			resultSet.add("File not found..!");
		}
		if (resultSet.size() == 0) {
			resultSet.add("Data not found..!");
		}
		return resultSet;
	}

	public void actionPerformed(ActionEvent evt) {

		if (evt.getSource() == but) {

			pan.remove(chartPanel);
			a = Double.parseDouble(textfield1.getText());

			chart = new XYLineChart_AWT("Interface", "SMASH", a);

			chartPanel = chart.getChart();

			pan.add(chartPanel, BorderLayout.EAST);

			f.pack();

		}

		if (evt.getSource() == saveButton) {

			// if the file name doesnt exist
			chart.saveFile(saveText.getText());
		}

	}
	

    


}
