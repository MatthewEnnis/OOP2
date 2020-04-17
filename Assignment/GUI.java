/*
GUI class creates a GUI to allow the user to interact with the Naïve Bayes class.
The user can select which files they want to read the data from and then input their symptoms to be told their odds of having COVID-19
They can also test the accuracy of the model by inputting another data file.
There are 3 main sections to the GUI: the data input, the accuracy testing, and the symptom input and testing.
Matthew Ennis 17/4/20
*/

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class GUI extends JFrame implements ActionListener //Inherits stuff from JFrame and implements the ActionListener interface
{
	JPanel mainPanel, leftPanel, symptomPanel, dataPanel, testPanel, selectionPanel, filePanel, testFilePanel, addDataPanel, testAccuracyPanel;
	JLabel heading, tempLabel, filePrompt, testFilePrompt, dataStatus, accuracyResult, result;
	JComboBox tempSelect;
	JCheckBox achesBox, coughBox, throatBox, dangerBox;
	JButton testSymptoms, browseFiles, addData, browseTestFiles, testData;
	JTextField fileName, testFileName;
	JFileChooser fileChooser;
	NaiveBayes classifier;
	Entry testEntry;
	double prediction;
	
	public GUI()
	{
		super("COVID-19 Diagnostic Tool"); //Window name
		
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //Give it an appropriate aesthetic for the OS
		}
		catch (Exception e) //Might not work if you're using a weird OS
		{
			System.out.println("Look and feel not found"); //No harm done, it'll just look a little different
		}
		
		setSize(620,320); //Pretty small but it's all it really needs to be
		setDefaultCloseOperation(EXIT_ON_CLOSE); //End when you click X
		setResizable(false); //Don't want it resized cause it'll mess up the layout
		setLayout(new BorderLayout()); //BorderLayout so I can have the heading at the top
		
		//Heading
		heading = new JLabel("COVID-19 Diagnostic Tool", SwingConstants.CENTER); //Center aligned
		heading.setFont(new Font("Dialog", Font.PLAIN, 20)); //Big font for the heading
		
		//Main panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS)); //Essentially split it in two
		
		//Adding them to borderlayout
		add(heading,BorderLayout.NORTH);
		add(mainPanel,BorderLayout.CENTER);
		
		//Three main panels, data and test on the left and symptom on the right
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); //Splitting the left section in two as well
		
		dataPanel = new JPanel();
		dataPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Input training data"));
		
		testPanel = new JPanel();
		testPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Test classifier accuracy"));
		
		leftPanel.add(dataPanel);
		leftPanel.add(testPanel);

		symptomPanel = new JPanel();
		symptomPanel.setLayout(new BorderLayout());
		symptomPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Test symptoms"));

		mainPanel.add(leftPanel);
		mainPanel.add(symptomPanel);
		
		//Symptom panel
		selectionPanel = new JPanel();
		selectionPanel.setLayout(new GridLayout(3,2)); //3 by 2 grid for the 5 options plus 1 label
		
		tempLabel = new JLabel("Temperature: ", SwingConstants.CENTER);
		tempSelect = new JComboBox<>(new String[] {"Hot","Normal","Cool","Cold"}); //JComboBox for the temperature since there are 4 options
		tempSelect.setSelectedIndex(1);
		
		//Checkboxes for the other inputs since they're just true or false
		achesBox = new JCheckBox("Aches");
		coughBox = new JCheckBox("Cough");
		throatBox = new JCheckBox("Sore throat");
		dangerBox = new JCheckBox("Recently in danger zone");
		
		result = new JLabel("", SwingConstants.CENTER); //No result displayed by default
		//result.setFont(new Font("Dialog", Font.BOLD, 13));
		
		testSymptoms = new JButton("Test symptoms");
		testSymptoms.addActionListener(this);
		testSymptoms.setEnabled(false); //Shouldn't be able to click before data has been added
		
		selectionPanel.add(achesBox);
		selectionPanel.add(coughBox);
		selectionPanel.add(throatBox);
		selectionPanel.add(dangerBox);
		selectionPanel.add(tempLabel);
		selectionPanel.add(tempSelect);
		symptomPanel.add(selectionPanel,BorderLayout.NORTH);
		symptomPanel.add(result,BorderLayout.CENTER);
		symptomPanel.add(testSymptoms,BorderLayout.SOUTH);
		
		
		//Data Panel
		filePrompt = new JLabel("Select a CSV file to add data from");
		
		filePanel = new JPanel();
				
		fileName = new JTextField(25); //Nice and wide
		fileName.addActionListener(this);
		browseFiles = new JButton("Browse");
		browseFiles.addActionListener(this);
		
		filePanel.add(fileName);
		filePanel.add(browseFiles);
		
		addDataPanel = new JPanel();
		
		addData = new JButton("Add data from file");
		addData.addActionListener(this);
		dataStatus = new JLabel(); //Nothing displayed by default
		
		addDataPanel.add(addData);
		addDataPanel.add(dataStatus);
		
		dataPanel.setLayout(new BorderLayout());
		
		dataPanel.add(filePrompt, BorderLayout.NORTH);
		dataPanel.add(filePanel, BorderLayout.CENTER);
		dataPanel.add(addDataPanel, BorderLayout.SOUTH);
		
		
		//Test Panel (pretty much the exact same as the previous panel)
		testFilePrompt = new JLabel("Select a CSV file to test classifier accuracy with");
		
		testFilePanel = new JPanel();
		
		testFileName = new JTextField(25);
		testFileName.addActionListener(this);
		browseTestFiles = new JButton("Browse");
		browseTestFiles.addActionListener(this);
		
		testFilePanel.add(testFileName);
		testFilePanel.add(browseTestFiles);
		
		testAccuracyPanel = new JPanel();
		
		testData = new JButton("Test classifier accuracy");
		testData.addActionListener(this);
		accuracyResult = new JLabel();
		
		testAccuracyPanel.add(testData);
		testAccuracyPanel.add(accuracyResult);
		
		testPanel.setLayout(new BorderLayout());
		
		testPanel.add(testFilePrompt, BorderLayout.NORTH);
		testPanel.add(testFilePanel, BorderLayout.CENTER);
		testPanel.add(testAccuracyPanel, BorderLayout.SOUTH);
		
		
		setVisible(true);
		
		classifier = new NaiveBayes();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == browseFiles || e.getSource() == browseTestFiles) //If either of the browse buttons are clicked
		{
			fileChooser = new JFileChooser();
			fileChooser.setAcceptAllFileFilterUsed(false); //Don't want to allow just any file
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV files", "csv")); //CSVs only
			int x = fileChooser.showOpenDialog(null); //We want to give the option to open a file rather than saving one
			if (x == JFileChooser.APPROVE_OPTION) //When they click open
			{
				if (e.getSource() == browseFiles) fileName.setText(fileChooser.getSelectedFile().getAbsolutePath()); //Check if it's the browse button at the top
				else testFileName.setText(fileChooser.getSelectedFile().getAbsolutePath()); //Or the browse button at the bottom
			}
		}
		else if (e.getSource() == addData || e.getSource() == fileName) //They can either press enter in the text box or click the button
		{
			if (classifier.readFile(fileName.getText())) //If the classifier can read it (ie in the right format)
			{
				dataStatus.setText(classifier.generateFrequency()+" entries added ("+classifier.getEntryCount()+" total)"); //Return how many entries you just added and the total 
				dataStatus.setForeground(Color.BLACK); //Green would be a little hard to read at this size
				testSymptoms.setEnabled(true); //Enable the button for testing symptoms
			}
			else //File is in the wrong format
			{
				dataStatus.setText("Invalid file");
				dataStatus.setForeground(Color.RED);
			}
		}
		else if (e.getSource() == testData || e.getSource() == testFileName) //Pretty much same as above
		{
			if (classifier.readFile(testFileName.getText()))
			{
				accuracyResult.setText(String.format("%.2f%% accurate", classifier.testAccuracy())); //Only want it to 2 decimal places
				accuracyResult.setForeground(Color.BLACK);
			}
			else
			{
				accuracyResult.setText("Invalid file");
				accuracyResult.setForeground(Color.RED);
			}
		}
		else if (e.getSource() == testSymptoms)
		{
			//Make a new entry object with the information from the combobox and the checkboxes
			testEntry = new Entry((String)tempSelect.getSelectedItem(),achesBox.isSelected(),coughBox.isSelected(),throatBox.isSelected(),dangerBox.isSelected());
			try
			{
				prediction = classifier.predict(testEntry);
				//Set the result text with some nice formatting with html
				result.setText(String.format("<html><center 'style=font-size:16px'>You have a <br />%.2f%%<br />chance of having COVID-19</center></html>", prediction));
				result.setForeground(new Color((int)(50+prediction*2),(int)(200-prediction*2),0)); //Green if good, red if bad
			}
			catch (Exception ex) //The classifier might not have any information about one of the symptoms (ie no positive data for cold)
			{
				result.setText("Not enough data to accurately predict");
				result.setForeground(Color.RED);
			}
		}
	}
}