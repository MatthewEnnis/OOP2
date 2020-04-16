import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.SwingConstants;

import java.util.Random;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class GUI extends JFrame implements ActionListener
{
	JPanel mainPanel, headingPanel, leftPanel, symptomPanel, dataPanel, testPanel, selectionPanel, filePanel, testFilePanel, addDataPanel, testAccuracyPanel;
	JLabel heading, tempLabel, filePrompt, testFilePrompt, dataStatus, accuracyResult, result;
	JComboBox tempSelect;
	JCheckBox achesBox, coughBox, throatBox, dangerBox;
	JButton testSymptoms, browseFiles, addData, browseTestFiles, testData;
	JTextField fileName, testFileName;
	JFileChooser fileChooser;
	NaiveBayes classifier;
	Entry testEntry;
	
	public GUI()
	{
		super("COVID-19 Diagnostic Tool");
		
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //Give it an appropriate aesthetic for the OS
		}
		catch (Exception e)
		{
			System.out.println("Look and feel not found");
		}
		
		setSize(620,320);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout()); //BorderLayout so I can have the heading at the top
		
		//Heading
		headingPanel = new JPanel();
		
		heading = new JLabel("COVID-19 Diagnostic Tool");
		heading.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		headingPanel.add(heading);
		
		//Main panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		
		//Adding them to borderlayout
		add(headingPanel,BorderLayout.NORTH);
		add(mainPanel,BorderLayout.CENTER);
		
		//Three main panels, data and test on the left and symptom on the right
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
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
		tempLabel = new JLabel("Temperature: ", SwingConstants.CENTER);
		tempSelect = new JComboBox<>(new String[] {"Hot","Normal","Cool","Cold"});
		tempSelect.setSelectedIndex(1);
		
		
		selectionPanel = new JPanel();
		selectionPanel.setLayout(new GridLayout(3,2));
		
		achesBox = new JCheckBox("Aches");
		coughBox = new JCheckBox("Cough");
		throatBox = new JCheckBox("Sore throat");
		dangerBox = new JCheckBox("Recently in danger zone");
		
		result = new JLabel("", SwingConstants.CENTER);
		
		testSymptoms = new JButton("Test symptoms");
		testSymptoms.addActionListener(this);
		testSymptoms.setEnabled(false);
		
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
		fileName = new JTextField(25);
		fileName.addActionListener(this);
		browseFiles = new JButton("Browse");
		filePanel = new JPanel();
		filePanel.add(fileName);
		filePanel.add(browseFiles);
		browseFiles.addActionListener(this);
		addData = new JButton("Add data from file");
		addData.addActionListener(this);
		dataStatus = new JLabel();
		addDataPanel = new JPanel();
		addDataPanel.add(addData);
		addDataPanel.add(dataStatus);
		
		dataPanel.setLayout(new BorderLayout());
		
		dataPanel.add(filePrompt, BorderLayout.NORTH);
		dataPanel.add(filePanel, BorderLayout.CENTER);
		dataPanel.add(addDataPanel, BorderLayout.SOUTH);
		
		
		//Test Panel
		testFilePrompt = new JLabel("Select a CSV file to test classifier accuracy with");
		testFileName = new JTextField();
		browseTestFiles = new JButton("Browse files");
		browseTestFiles.addActionListener(this);
		testData = new JButton("Add data from file");
		
		testPanel.setLayout(new GridLayout(4,1));
		
		testPanel.add(testFilePrompt);
		testPanel.add(testFileName);
		testPanel.add(browseTestFiles);
		testPanel.add(testData);
		
		
		testFilePrompt = new JLabel("Select a CSV file to test classifier accuracy with");
		testFileName = new JTextField(25);
		browseTestFiles = new JButton("Browse");
		testFilePanel = new JPanel();
		testFilePanel.add(testFileName);
		testFilePanel.add(browseTestFiles);
		browseTestFiles.addActionListener(this);
		testData = new JButton("Test classifier accuracy");
		testData.addActionListener(this);
		accuracyResult = new JLabel();
		testAccuracyPanel = new JPanel();
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
		if (e.getSource() == browseFiles || e.getSource() == browseTestFiles) //If either browse button is clicked
		{
			dataStatus.setText("");
			fileChooser = new JFileChooser();
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV files", "csv"));
			int x = fileChooser.showOpenDialog(null);
			if (x == JFileChooser.APPROVE_OPTION)
			{
				if (e.getSource() == browseFiles) fileName.setText(fileChooser.getSelectedFile().getAbsolutePath());
				else testFileName.setText(fileChooser.getSelectedFile().getAbsolutePath());
			}
		}
		else if (e.getSource() == addData || e.getSource() == fileName)
		{
			if (classifier.readFile(fileName.getText()))
			{
				dataStatus.setText(classifier.generateFrequency()+" entries added successfully");
				testSymptoms.setEnabled(true);
			}
			else
			{
				dataStatus.setText("Invalid file");
			}
		}
		else if (e.getSource() == testData || e.getSource() == testFileName)
		{
			if (classifier.readFile(testFileName.getText()))
			{
				accuracyResult.setText(String.format("%.2f%% accurate", classifier.testAccuracy()));
			}
			else
			{
				accuracyResult.setText("Invalid file");
			}
		}
		else if (e.getSource() == testSymptoms)
		{
			testEntry = new Entry((String)tempSelect.getSelectedItem(),achesBox.isSelected(),coughBox.isSelected(),throatBox.isSelected(),dangerBox.isSelected());
			try
			{
				result.setText(String.format("You have a %.2f%% chance of having COVID-19", classifier.predict(testEntry)));
			}
			catch (Exception ex)
			{
				result.setText("Not enough data to accurately predict");
			}
		}
	}
}