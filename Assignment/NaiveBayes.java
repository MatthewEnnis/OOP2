import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class NaiveBayes
{
	File file;
	Scanner scanner;
	ArrayList<Entry> entries;
	double positiveResult;
	double negativeResult;
	float total;
	int entryCount = 0;
	int correctCount;
	
	//Hashmaps for each column to store the frequency of each result depending on covid19 being positive or negative	
	HashMap<Boolean,Integer> covid19 = new HashMap<>();	
	
	HashMap<String,Integer> temperaturePositive = new HashMap<>();
	HashMap<Boolean,Integer> achesPositive = new HashMap<>();
	HashMap<Boolean,Integer> coughPositive = new HashMap<>();
	HashMap<Boolean,Integer> soreThroatPositive = new HashMap<>();
	HashMap<Boolean,Integer> dangerZonePositive = new HashMap<>();
	
	HashMap<String,Integer> temperatureNegative = new HashMap<>();
	HashMap<Boolean,Integer> achesNegative = new HashMap<>();
	HashMap<Boolean,Integer> coughNegative = new HashMap<>();
	HashMap<Boolean,Integer> soreThroatNegative = new HashMap<>();
	HashMap<Boolean,Integer> dangerZoneNegative = new HashMap<>();
	
	
	//Constructors
	
	public NaiveBayes()
	{
		//Don't really need to do anything in the constructor but don't wanna leave it blank so
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
}
