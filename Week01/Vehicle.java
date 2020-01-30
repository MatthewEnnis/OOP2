public class Vehicle
{
	/*****************************
	* Vehicle: A class to describe a vehicle
	* Author: Matthew Ennis
	* Date: 30/01/20
	*****************************/
	
	String ownerName;
	String regNumber;
	int maxSpeed;
	String colour;
	boolean automatic;
	int wheels;
	
	public Vehicle(String ownerName, String regNumber)
	{
		this.ownerName = ownerName;
		this.regNumber = regNumber;
	}
	
	public Vehicle(String ownerName, String regNumber, int maxSpeed, String colour, boolean automatic, int wheels)
	{
		this.ownerName = ownerName;
		this.regNumber = regNumber;
		this.maxSpeed = maxSpeed;
		this.colour = colour;
		this.automatic = automatic;
		this.wheels = wheels;
	}
	
	public String toString()
	{
		String content = "The vehicle has owner name "+ownerName
						+", has a registration plate of "+regNumber.toUpperCase()
						+", is of colour "+colour
						+", has a max speed of "+maxSpeed
						+", has "+wheels+" wheels"
						+", and is ";
		if (automatic)
		{
			return content+"automatic.";
		}
		return content+"not automatic.";
	}
}