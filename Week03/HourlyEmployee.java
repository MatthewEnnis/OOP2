public class HourlyEmployee extends Employee
{
	private double hoursWorked;
	private double hourlyRate;
	
	//Constructors
	
	public HourlyEmployee(String firstName, String surName, int staffNumber, double hoursWorked, double hourlyRate)
	{
		super(firstName, surName, staffNumber, 0);
		this.hoursWorked = hoursWorked;
		this.hourlyRate = hourlyRate;
	}
	
	//Methods
	
	public String toString()
	{
		return super.toString()+" but makes "+hourlyRate+" euro per hour while working "+hoursWorked+" hours per week";
	}
	
	public double calculatePay()
	{
		return hoursWorked * hourlyRate;
	}
	
	//Getters and setters
	
	public double getHoursWorked()
	{
		return hoursWorked;
	}
	
	public void setHoursWorked(double hoursWorked)
	{
		this.hoursWorked = hoursWorked;
	}
	
	public double getHourlyRate()
	{
		return hourlyRate;
	}
	
	public void setHourlyRate(double hourlyRate)
	{
		this.hourlyRate = hourlyRate;
	}
	
}