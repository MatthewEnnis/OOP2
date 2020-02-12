public class SalesEmployee extends Employee
{
	private double commissionEarned;
	
	//Constructors
	
	public SalesEmployee(String firstName, String surName, int staffNumber, double annualSalary, double commissionEarned)
	{
		super(firstName, surName, staffNumber, annualSalary);
		this.commissionEarned = commissionEarned;
	}
	
	//Methods
	
	public String toString()
	{
		return super.toString()+" and makes "+commissionEarned+" euro from commissions";
	}
	
	public double calculatePay()
	{
		return super.calculatePay() + commissionEarned;
	}
	
	//Getters and setters
	
	public double getCommissionEarned()
	{
		return commissionEarned;
	}
	
	public void setCommissionEarned(double commissionEarned)
	{
		this.commissionEarned = commissionEarned;
	}

}