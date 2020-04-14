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

import java.util.Random;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class GUI extends JFrame
{
	JPanel mainPanel, headingPanel, leftPanel, symptomPanel, dataPanel, testPanel, selectionPanel;
	JLabel heading, tempLabel;
	JComboBox tempSelect;
	JCheckBox achesBox, coughBox, throatBox, dangerBox;
	JButton testSymptoms;
	
	public GUI()
	{
		super("COVID-19 Diagnostic Tool");
		
		System.out.println(javax.swing.UIManager.getDefaults().getFont("Label.font"));
		
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		setSize(640,360);
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
		tempLabel = new JLabel("Temperature: ");
		tempSelect = new JComboBox<>(new String[] {"Hot","Normal","Cool"});
		tempSelect.setSelectedIndex(1);
		
		
		selectionPanel = new JPanel();
		selectionPanel.setLayout(new GridLayout(3,2));
		
		achesBox = new JCheckBox("Aches");
		coughBox = new JCheckBox("Cough");
		throatBox = new JCheckBox("Sore throat");
		dangerBox = new JCheckBox("Recently travelled to danger zone");
		
		testSymptoms = new JButton("Test symptoms");
		
		selectionPanel.add(achesBox);
		selectionPanel.add(coughBox);
		selectionPanel.add(throatBox);
		selectionPanel.add(dangerBox);
		selectionPanel.add(tempLabel);
		selectionPanel.add(tempSelect);
		symptomPanel.add(selectionPanel,BorderLayout.NORTH);
		symptomPanel.add(testSymptoms,BorderLayout.SOUTH);
		
		
		setVisible(true);
	}
}