//This is just for fun to see how short I could make it. This is not my real assignment, although it is fully functional
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
public class NaiveBayes extends JFrame implements ActionListener{
	Scanner scanner;
	HashMap<String,Integer> data = new HashMap<>();
	JTextField field = new JTextField("enter your symptoms in the format 'coughyes,acheno,dangeryes,throatno,temphot'");
	public static void main(String[] args) {NaiveBayes nb = 	new NaiveBayes(); }
	public NaiveBayes() {
		super("enter your symptoms");
		try { scanner = new Scanner(new File("MLdata.csv")); }
		catch (Exception e) {System.out.println("File error"); }
		while (scanner.hasNextLine()) {
			for (String s : getData(scanner.nextLine().toLowerCase().split(","))) {
				int count = data.containsKey(s) ? data.get(s) : 0;
				data.put(s, count+1); }}
		setSize(620,320);
		field.addActionListener(this);
		add(field);
		setVisible(true); }
	public void actionPerformed(ActionEvent e) {
		String[] in = field.getText().split(",");
		double pos = (double) data.get("yes") / (data.get("yes")+data.get("no"));
		for (String i : in) { pos *= (double) data.get("yes"+i) / data.get("yes"); }
		double neg = (double) data.get("no") / (data.get("yes")+data.get("no"));
		for (String i : in) { neg *= (double) data.get("no"+i) / data.get("no"); }
		field.setText(String.format("You have a %.2f%% chance of having COVID-19", pos / (pos + neg) * 100)); }
	public String[] getData(String[] d) {return new String[] {d[5]+"temp"+d[0],d[5]+"ache"+d[1],d[5]+"cough"+d[2],d[5]+"throat"+d[3],d[5]+"danger"+d[4],d[5]}; }}
