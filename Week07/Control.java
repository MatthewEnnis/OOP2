public class Control
{
	public static void main(String[] args)
	{
		Date date1 = new Date(10,2,2000);
		Date date2 = new Date(25,12,2020);
		Job job = new Job(1000,"Astronaut",555);
		Employee matthew = new Employee("Matthew","Ennis",date1,"M",job,date2);
		System.out.println(matthew);
	}
}