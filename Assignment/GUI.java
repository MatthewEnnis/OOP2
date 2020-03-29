import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.Random;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class GUI extends JFrame
{
	JTabbedPane tabs;
	JPanel trainingPanel, testingPanel;
	JLabel heading;
	
	public GUI()
	{
		super("COVID-19 Diagnostic Tool");
		
		setVisible(true);
		setSize(600,600);
		setLayout(new BorderLayout());
		
		tabs = new JTabbedPane();
		
		trainingPanel = new JPanel();
		testingPanel = new JPanel();
		
		tabs.addTab("Read data",trainingPanel);
		tabs.addTab("Test symptoms",testingPanel);
		
		heading = new JLabel("COVID-19 Diagnostic Tool");
		heading.setFont(new Font("Tahoma", Font.PLAIN, 50));
		
		add(heading,BorderLayout.NORTH);
		add(tabs,BorderLayout.CENTER);
	}
}