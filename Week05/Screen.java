import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class Screen extends JFrame implements ActionListener, MouseListener
{
	JPanel northPanel, centerPanel, southPanel, redPanel;
	JButton button1, button2;
	JLabel label;
	JTextField textfield;
	JToggleButton toggle;
	
	public Screen()
	{
		super("Lab 5");
		
		northPanel = new JPanel();
		centerPanel = new JPanel();
		southPanel = new JPanel();
		
		redPanel = new JPanel();
		redPanel.setBackground(Color.RED);
		redPanel.addMouseListener(this);
		
		button1 = new JButton("Button 1");
		button1.addActionListener(this);
		
		button2 = new JButton("Button 2");
		button2.addActionListener(this);
		
		label = new JLabel("the panel is here");
		
		toggle = new JToggleButton("Toggle button");
		
		textfield = new JTextField("Name");
		textfield.setToolTipText("Enter your name");
		textfield.addActionListener(this);
		
		setVisible(true);
		setSize(400,400);
		setLayout(new BorderLayout());
		
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		
		northPanel.add(button1);
		northPanel.add(button2);
				
		centerPanel.add(redPanel);
		redPanel.add(label);
		
		southPanel.add(textfield);
		southPanel.add(toggle);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == button1) JOptionPane.showMessageDialog(this,"You clicked the first button");
		else if (e.getSource() == button2) JOptionPane.showMessageDialog(this,"You clicked the second button");
		else if (e.getSource() == textfield) JOptionPane.showMessageDialog(this,"You entered "+textfield.getText());
	}
	
	public void mouseEntered(MouseEvent e)
	{
		label.setText("Mouse has entered the panel");
	}
	
	public void mouseExited(MouseEvent e)
	{
		label.setText("Mouse has exited the panel");
	}
	
	public void mouseClicked(MouseEvent e)
	{
		if (e.getButton() == 1) label.setText("Mouse has left clicked the panel");
		else label.setText("Mouse has right clicked the panel");
	}
	
	public void mouseReleased(MouseEvent e) { }
	public void mousePressed(MouseEvent e) { }
}