public class Control
{
	public static void main(String[] args)
	{
		Account[] accounts = new Account[3];
		
		accounts[0] = new Account("Account","12-34-56","Dublin",true,200);
		accounts[1] = new DepositAccount("Deposit","12-34-56","Dublin",true,50,20);
		accounts[2] = new CurrentAccount("Current","12-34-56","Dublin",true,100,10);
		
		for (Account account : accounts)
		{
			account.getDetails();
			account.deposit(10);
			account.withdraw(150);
			account.valuableAccount();
			System.out.println();
		}
		
		CurrentAccount testAccount = new CurrentAccount("Current","12-34-56","Dublin",true,120,50);
		System.out.println(testAccount.checkCredit());
		System.out.println(testAccount.checkCredit("Test"));
	}
}	