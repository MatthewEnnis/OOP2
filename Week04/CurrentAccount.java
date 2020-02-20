public class CurrentAccount extends Account
{
	private double penaltyAmount;
	
	//Constructors
	
	public CurrentAccount(String accountName, String sortCode, String branchName, boolean inCredit, double acctBalance, double penaltyAmount)
	{
		super(accountName,sortCode,branchName,inCredit,acctBalance);
		this.penaltyAmount = penaltyAmount;
	}
	
	//Methods
	
	public void withdraw(double takeAmount)
	{
		double bal = getAcctBalance();
		if (bal - takeAmount > 0)
			setAcctBalance(bal - takeAmount);
		else
			System.out.println("Insufficient funds");
	}
	
	public String checkCredit()
	{
		if (getAcctBalance() > 0)
			return "In credit";
		return "Not in credit";
	}
	
	public String checkCredit(String warningMessage)
	{
		double bal = getAcctBalance();
		if ( 0 < bal && bal < 100)
			return warningMessage;
		return "";
	}
	
	//Getters and setters
	
	public double getPenaltyAmount()
	{
		return penaltyAmount;
	}
	public void setPenaltyAmount(double penaltyAmount)
	{
		this.penaltyAmount = penaltyAmount;
	}
}