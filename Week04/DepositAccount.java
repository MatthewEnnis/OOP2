public class DepositAccount extends Account
{
	private double interestRate;
	
	//Constructors
	
	public DepositAccount(String accountName, String sortCode, String branchName, boolean inCredit, double acctBalance, double interestRate)
	{
		super(accountName,sortCode,branchName,inCredit,acctBalance);
		this.interestRate = interestRate;
	}
	
	//Methods
	
	public void withdraw(double takeAmount)
	{
		System.out.println("You cannot withdraw from a deposit account!");
	}
	
	//Getters and setters
	
	public double getInterestRate()
	{
		return interestRate;
	}
	public void setInterestRate(double interestRate)
	{
		this.interestRate = interestRate;
	}
}