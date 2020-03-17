public class Control
{
	public static void main(String[] args)
	{
		NaiveBayes nb = new NaiveBayes("MLdata.csv");
		System.out.println("\nYou have a "+nb.predict(new Entry("normal",false,true,false,true))+"% chance of having COVID-19");
	}
}
