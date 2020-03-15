import java.io.File;
import java.util.Scanner;

public class NaiveBayes
{
	File file;
	Scanner scanner;
	
	public NaiveBayes(String filename)
	{
		try
		{
			file = new File(filename);
			scanner = new Scanner(file);
		}
		catch (Exception e)
		{
			System.out.println("oh no");
		}
		
		System.out.println(scanner.nextLine());
		System.out.println(scanner.nextLine());
	}
}