import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.Random;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JFrame implements ActionListener
{
	JPanel centerPanel;
	JButton generatebutton, checkbutton;
	JLabel label;
	JTextField textfield;
	Random random = new Random();
	int randomnumber = random.nextInt(100);
	int guesses = 0;
	int guess;
	
	public GUI()
	{
		super("Guessing Game");
		
		centerPanel = new JPanel();
		
		generatebutton = new JButton("Generate the random number");
		generatebutton.addActionListener(this);
		
		label = new JLabel("Then make a guess..");
		
		textfield = new JTextField("Enter your Guess");
		textfield.setToolTipText("Enter your Guess");
		textfield.addActionListener(this);
		
		checkbutton = new JButton("Check your guess");
		checkbutton.addActionListener(this);		
		
		setVisible(true);
		setSize(300,300);
		setLayout(new BorderLayout());
		
		add(centerPanel, BorderLayout.CENTER);
		
		centerPanel.add(generatebutton);
		centerPanel.add(label);
		centerPanel.add(textfield);
		centerPanel.add(checkbutton);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == generatebutton) reset();
		else
		{
			try
			{
				guess = Integer.parseInt(textfield.getText());
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(this,"You haven't entered a valid number, try again!");
				return;
			}
			
			if (guess == randomnumber)
			{
				JOptionPane.showMessageDialog(this,"Correct, the number is "+randomnumber+", it took you "+guesses+" guesses!");
				reset();
			}
			else if (guess > randomnumber)
			{
				JOptionPane.showMessageDialog(this,"Your guess of "+guess+" is too high, try again!");
				guesses += 1;
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Your guess of "+guess+" is too low, try again!");
				guesses += 1;
			}
		}
	}
	
	public void reset()
	{
		randomnumber = random.nextInt(100);
		guesses = 0;
		JOptionPane.showMessageDialog(this,"A new number has been generated!");
	}
}