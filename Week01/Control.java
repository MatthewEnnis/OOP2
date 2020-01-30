public class Control
{
	public static void main(String [] args)
	{
		Vehicle v1 = new Vehicle("Matthew","201-d-12345");
		Vehicle v2 = new Vehicle("Matthew","201-d-54321",100,"pink",true,4);
		
		System.out.println(v1.toString());
		System.out.println(v2.toString());
	}
}