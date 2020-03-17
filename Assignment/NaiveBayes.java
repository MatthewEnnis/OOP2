import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class NaiveBayes
{
	File file;
	Scanner scanner;
	ArrayList<Entry> entries = new ArrayList<Entry>();
	double positiveResult;
	double negativeResult;
	float total;
	
	HashMap<String,Integer> temperature = new HashMap<>();
	HashMap<Boolean,Integer> aches = new HashMap<>();
	HashMap<Boolean,Integer> cough = new HashMap<>();
	HashMap<Boolean,Integer> soreThroat = new HashMap<>();
	HashMap<Boolean,Integer> dangerZone = new HashMap<>();
	HashMap<Boolean,Integer> covid19 = new HashMap<>();
	
	HashMap<String,Integer> temperatureGivenCovid19 = new HashMap<>();
	HashMap<Boolean,Integer> achesGivenCovid19 = new HashMap<>();
	HashMap<Boolean,Integer> coughGivenCovid19 = new HashMap<>();
	HashMap<Boolean,Integer> soreThroatGivenCovid19 = new HashMap<>();
	HashMap<Boolean,Integer> dangerZoneGivenCovid19 = new HashMap<>();
	
	public NaiveBayes(String filename)
	{
		readFile(filename);
		findFrequency();
	}
	
	public boolean readFile(String filename)
	{
		try
		{
			file = new File(filename);
			scanner = new Scanner(file);
		}
		catch (Exception e)
		{
			System.out.println("Invalid file");
			return false;
		}
		scanner.nextLine();
		while (scanner.hasNextLine()) entries.add(new Entry(scanner.nextLine().split(",")));
		return true;
	}
	
	public void findFrequency()
	{
		for (Entry e : entries)
		{
			incrementFrequency(temperature,e.getTemperature());
			incrementFrequency(aches,e.getAches());
			incrementFrequency(cough,e.getCough());
			incrementFrequency(soreThroat,e.getSoreThroat());
			incrementFrequency(dangerZone,e.getDangerZone());
			incrementFrequency(covid19,e.getCovid19());
			
			if (e.getCovid19())
			{
				incrementFrequency(temperatureGivenCovid19,e.getTemperature());
				incrementFrequency(achesGivenCovid19,e.getAches());
				incrementFrequency(coughGivenCovid19,e.getCough());
				incrementFrequency(soreThroatGivenCovid19,e.getSoreThroat());
				incrementFrequency(dangerZoneGivenCovid19,e.getDangerZone());
			}
		}
	}
	
	public double predict(Entry testCase)
	{
		total = covid19.get(true);
		positiveResult = temperatureGivenCovid19.get(testCase.getTemperature()) / total;
		positiveResult *= achesGivenCovid19.get(testCase.getAches()) / total;
		positiveResult *= coughGivenCovid19.get(testCase.getCough()) / total;
		positiveResult *= soreThroatGivenCovid19.get(testCase.getSoreThroat()) / total;
		positiveResult *= dangerZoneGivenCovid19.get(testCase.getDangerZone()) / total;
		positiveResult *= (float) covid19.get(true) / entries.size();
		
		total = covid19.get(false);
		negativeResult = (temperature.get(testCase.getTemperature()) - temperatureGivenCovid19.get(testCase.getTemperature())) / total;
		negativeResult *= (aches.get(testCase.getAches()) - achesGivenCovid19.get(testCase.getAches())) / total;
		negativeResult *= (cough.get(testCase.getCough()) -coughGivenCovid19.get(testCase.getCough())) / total;
		negativeResult *= (soreThroat.get(testCase.getSoreThroat()) - soreThroatGivenCovid19.get(testCase.getSoreThroat())) / total;
		negativeResult *= (dangerZone.get(testCase.getDangerZone()) - dangerZoneGivenCovid19.get(testCase.getDangerZone())) / total;
		negativeResult *= (float) covid19.get(false) / entries.size();

		return positiveResult / (positiveResult+negativeResult) * 100;
	}
	
	public void incrementFrequency(HashMap<String,Integer> map, String key)
	{
		if (map.get(key) != null)
		{
			map.put(key,map.get(key)+1);
		}
		else
		{
			map.put(key,1);
		}
	}
	
	public void incrementFrequency(HashMap<Boolean,Integer> map, boolean key)
	{
		if (map.get(key) != null)
		{
			map.put(key,map.get(key)+1);
		}
		else
		{
			map.put(key,1);
		}
	}
}
