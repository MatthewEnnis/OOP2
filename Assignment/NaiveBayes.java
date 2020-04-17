/*
Naïve Bayes class which handles all of the machine learning.
There are a series of hashmaps which store the data for each feature.
They could probably all be stored in one, but I think having separate ones for each makes for more readable code.
The readFile method will attempt to read in a file and return true if it was successful, false if not.
It stores the entries from the file in an ArrayList of Entry objects.
The generateFrequency method will look through each of these entries and increment the appropriate frequency hashmaps.
The predict method will then use this to calculate the odds.
Matthew Ennis 17/4/20
*/

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class NaiveBayes
{
	private File file;
	private Scanner scanner;
	private ArrayList<Entry> entries;
	private double positiveResult;
	private double negativeResult;
	private float total;
	private int entryCount = 0;
	private int correctCount;
	
	//Hashmaps for each column to store the frequency of each result depending on covid19 being positive or negative	
	private HashMap<Boolean,Integer> covid19 = new HashMap<>();	
	
	private HashMap<String,Integer> temperaturePositive = new HashMap<>();
	private HashMap<Boolean,Integer> achesPositive = new HashMap<>();
	private HashMap<Boolean,Integer> coughPositive = new HashMap<>();
	private HashMap<Boolean,Integer> soreThroatPositive = new HashMap<>();
	private HashMap<Boolean,Integer> dangerZonePositive = new HashMap<>();
	
	private HashMap<String,Integer> temperatureNegative = new HashMap<>();
	private HashMap<Boolean,Integer> achesNegative = new HashMap<>();
	private HashMap<Boolean,Integer> coughNegative = new HashMap<>();
	private HashMap<Boolean,Integer> soreThroatNegative = new HashMap<>();
	private HashMap<Boolean,Integer> dangerZoneNegative = new HashMap<>();
	
	
	//Constructors
	
	public NaiveBayes()
	{
		//Nothing needs to be done in the constructor, it doesn't do anything until a file is read
	}
	
	
	//Methods
	
	public String toString()
	{
		return entryCount+" entries";
	}
	
	public boolean readFile(String filename)
	{
		entries = new ArrayList<Entry>(); //Empty the current list of entries
		try //Try to open the file
		{
			file = new File(filename);
			scanner = new Scanner(file);	
		}
		catch (Exception e)
		{
			return false; //Return false if the file can't be read
		}
		if (scanner.nextLine().split(",").length !=  6) return false; //Return false if there aren't the correct amount of columns
		while (scanner.hasNextLine()) entries.add(new Entry(scanner.nextLine().split(","))); //Add each line as new entry object in the entries arraylist
		return true; //Success
	}
	
	public int generateFrequency()
	{
		for (Entry e : entries)
		{
			incrementFrequency(covid19,e.getCovid19()); //Increment the true or false keys in the covid19 hashmap
			
			if (e.getCovid19()) //If they're positive, increment the appropriate keys in the positive hashmaps
			{
				incrementFrequency(temperaturePositive,e.getTemperature());
				incrementFrequency(achesPositive,e.getAches());
				incrementFrequency(coughPositive,e.getCough());
				incrementFrequency(soreThroatPositive,e.getSoreThroat());
				incrementFrequency(dangerZonePositive,e.getDangerZone());
			}
			else //If they're negative, increment the appropriate keys in the negative hashmaps
			{
				incrementFrequency(temperatureNegative,e.getTemperature());
				incrementFrequency(achesNegative,e.getAches());
				incrementFrequency(coughNegative,e.getCough());
				incrementFrequency(soreThroatNegative,e.getSoreThroat());
				incrementFrequency(dangerZoneNegative,e.getDangerZone());
			}
		}
		entryCount += entries.size(); //Add to the total entries which is needed for later calculations
		return entries.size(); //Return how many entries were just added
	}
	
	public double predict(Entry testCase)
	{
		//Multiply all the odds for positive
		total = covid19.get(true);
		positiveResult = temperaturePositive.get(testCase.getTemperature()) / total;
		positiveResult *= achesPositive.get(testCase.getAches()) / total;
		positiveResult *= coughPositive.get(testCase.getCough()) / total;
		positiveResult *= soreThroatPositive.get(testCase.getSoreThroat()) / total;
		positiveResult *= dangerZonePositive.get(testCase.getDangerZone()) / total;
		positiveResult *= (float) covid19.get(true) / entryCount;
		
		//Multiply all the odds for negative
		total = covid19.get(false);
		negativeResult = temperatureNegative.get(testCase.getTemperature()) / total;
		negativeResult *= achesNegative.get(testCase.getAches()) / total;
		negativeResult *= coughNegative.get(testCase.getCough()) / total;
		negativeResult *= soreThroatNegative.get(testCase.getSoreThroat()) / total;
		negativeResult *= dangerZoneNegative.get(testCase.getDangerZone()) / total;
		negativeResult *= (float) covid19.get(false) / entryCount;
		
		//Return the result of  the calculation (multiplied by 100 so it'll be a percentage)
		return positiveResult / (positiveResult+negativeResult) * 100;
	}
	
	public double testAccuracy()
	{
		correctCount = 0;
		for (Entry e : entries)
		{
			try
			{
				if ((e.getCovid19() && predict(e) > 50) || (!e.getCovid19() && predict(e) < 50)) correctCount++; //Increment the count if it got it right
			}
			catch (Exception ex)
			{
				//The classifier might not be able to give a prediction, in which case its guess should be considered incorrect
			}
		}
		return (double) correctCount / entries.size() * 100;
	}
	
	public void incrementFrequency(HashMap<String,Integer> map, String key) //The key will be a string for the temperature
	{
		if (map.get(key) != null) //If it's already there, increment it
		{
			map.put(key,map.get(key)+1);
		}
		else //If it's not already there, put it there and set it to 1
		{
			map.put(key,1);
		}
	}
	
	public void incrementFrequency(HashMap<Boolean,Integer> map, boolean key) //The key will be a boolean for all the others
	{
		if (map.get(key) != null) //If it's already there, increment it
		{
			map.put(key,map.get(key)+1);
		}
		else //If it's not already there, put it there and set it to 1
		{
			map.put(key,1);
		}
	}
	
	
	//Getters and setters (Not sure why you would need to get or set half of these but hey)

	public File getFile() {return file;}
	public void setFile(File file) {this.file = file;}

	public Scanner getScanner() {return scanner;}
	public void setScanner(Scanner scanner) {this.scanner = scanner;}

	public ArrayList<Entry> getEntries() {return entries;}
	public void setEntries(ArrayList<Entry> entries) {this.entries = entries;}

	public double getPositiveResult() {return positiveResult;}
	public void setPositiveResult(double positiveResult) {this.positiveResult = positiveResult;}

	public double getNegativeResult() {return negativeResult;}
	public void setNegativeResult(double negativeResult) {this.negativeResult = negativeResult;}

	public float getTotal() {return total;}
	public void setTotal(float total) {this.total = total;}

	public int getEntryCount() {return entryCount;}
	public void setEntryCount(int entryCount) {this.entryCount = entryCount;}

	public int getCorrectCount() {return correctCount;}
	public void setCorrectCount(int correctCount) {this.correctCount = correctCount;}
}
