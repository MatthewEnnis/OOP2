public class Control
{
	public static void main(String[] args)
	{
		GUI gui = new GUI();
		
		NaiveBayes nb = new NaiveBayes("MLdata.csv");
		System.out.println("\nYou have a "+nb.predict(new Entry("normal",false,false,true,true))+"% chance of having COVID-19");
	}
}
