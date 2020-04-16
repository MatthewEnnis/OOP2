public class Control
{
	public static void main(String[] args)
	{
		GUI gui = new GUI();
		
		NaiveBayes nb = new NaiveBayes();
		nb.readFile("C:\\Users\\matte\\Downloads\\git clones\\OOP2\\Assignment\\MLdata.csv");
		nb.generateFrequency();
		System.out.println("\nYou have a "+nb.predict(new Entry("cool",false,false,true,true))+"% chance of having COVID-19");
		nb.readFile("C:\\Users\\matte\\Downloads\\git clones\\OOP2\\Assignment\\MLdata.csv");
		System.out.println(nb.testAccuracy());
	}
}
