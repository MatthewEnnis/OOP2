public class Control
{
	public static void main(String[] args)
	{
		Employee[] myEmployees = new Employee[6];
		myEmployees[0] = new Employee("Matthew","Ennis",1,48000);
		myEmployees[1] = new HourlyEmployee("Matthew","Ennis",2,30,15);
		myEmployees[2] = new SalesEmployee("Matthew","Ennis",3,36000,2000);
		myEmployees[3] = new Employee("Matthew","Ennis",4,60000);
		myEmployees[4] = new HourlyEmployee("Matthew","Ennis",5,20,10);
		myEmployees[5] = new SalesEmployee("Matthew","Ennis",6,24000,10000);
		
		for (Employee employee : myEmployees)
		{
			System.out.println(employee);
			System.out.println("Pay = "+employee.calculatePay());
		}
	}
}