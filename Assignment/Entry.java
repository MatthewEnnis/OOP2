public class Entry
{
	private String temperature;
	private boolean aches;
	private boolean cough;
	private boolean soreThroat;
	private boolean dangerZone;
	private boolean covid19;

	//Constructors

	public Entry(String temperature, boolean aches, boolean cough, boolean soreThroat, boolean dangerZone, boolean covid19) //Constructor for reading from the file takes strings, including covid19
	{
		setTemperature(temperature);
		setAches(aches);
		setCough(cough);
		setSoreThroat(soreThroat);
		setDangerZone(dangerZone);
		setCovid19(covid19);
	}
	
	public Entry(String temperature, boolean aches, boolean cough, boolean soreThroat, boolean dangerZone) //Constructor for testing entries takes booleans and a string, doesn't include covid19
	{
		setTemperature(temperature);
		setAches(aches);
		setCough(cough);
		setSoreThroat(soreThroat);
		setDangerZone(dangerZone);
	}
	
	public Entry(String[] parameters)
	{
		setTemperature(parameters[0]);
		setAches(parameters[1]);
		setCough(parameters[2]);
		setSoreThroat(parameters[3]);
		setDangerZone(parameters[4]);
		setCovid19(parameters[5]);
	}

	
	//Methods
	
	public String toString()
	{
		return "Temp: "+getTemperature()+"\nAches: "+getAches()+"\nCough: "+getCough()+"\nSore throat: "+getSoreThroat()+"\nDanger zone: "+getDangerZone()+"\nHas covid 19: "+getCovid19();
	}		
	
	
	//Getters and setters
	
	//Temperature	
	public String getTemperature() {return temperature;}
	public void setTemperature(String temperature) {this.temperature = temperature.toLowerCase();}
	
	//Aches
	public boolean getAches() {return aches;}
	public void setAches(boolean aches) {this.aches = aches;}
	
	public void setAches(String aches)
	{
		if (aches.equals("yes")) this.aches = true;
		else this.aches = false;
	}
	
	//Cough
	public boolean getCough() {return cough;}
	public void setCough(boolean cough) {this.cough = cough;}
	
	public void setCough(String cough)
	{
		if (cough.equals("yes")) this.cough = true;
		else this.cough = false;
	}

	//Sore throat
	public boolean getSoreThroat() {return soreThroat;}
	public void setSoreThroat(boolean soreThroat) {this.soreThroat = soreThroat;}
	
	public void setSoreThroat(String soreThroat)
	{
		if (soreThroat.equals("yes")) this.soreThroat = true;
		else this.aches = false;
	}
	
	//Danger zone
	public boolean getDangerZone() {return dangerZone;}
	public void setDangerZone(boolean dangerZone) {this.dangerZone = dangerZone;}
	
	public void setDangerZone(String dangerZone)
	{
		if (dangerZone.equals("yes")) this.dangerZone = true;
		else this.dangerZone = false;
	}
	
	//Has Covid-19
	public boolean getCovid19() {return covid19;}
	public void setCovid19(boolean covid19) {this.covid19 = covid19;}
	
	public void setCovid19(String covid19)
	{
		if (covid19.equals("yes")) this.covid19 = true;
		else this.covid19 = false;
	}
}