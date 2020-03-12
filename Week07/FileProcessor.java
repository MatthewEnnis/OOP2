import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;

public class FileProcessor {
	
	File file;
	Scanner scanner;
	FileWriter writer;
	
	public FileProcessor(String filename, String type)
	{
		if (type == "write")
		{
			this.file = new File(filename);
			try
			{
				writer = new FileWriter(file);
			}
			catch (Exception e)
			{
				System.out.println("File not found");
			}
		}
		else
		{
			this.file = new File(filename);
			try
			{
				scanner = new Scanner(file);
			}
			catch (Exception e)
			{
				System.out.println("File not found");
			}
		}
	}
	
	public boolean checkRole(String role)
	{
		while (scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			if (line.contains(role))
			{
				return true;
			}
		}
		return false;
	}
	
	public void write(String text)
	{
		try
		{
			writer.write(text);
			writer.close();
		}
		catch (Exception e)
		{
			System.out.println("Error, can't write");
		}
	}
}