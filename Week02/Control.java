package Lab2;

import java.util.Scanner;

public class Control {
	public static void main(String [] args)
	{
		Animal a1 = new Animal("Spot","dog",4,true,"white");
		Animal a2 = new Animal("Leo","cat",100,false,"red");
		System.out.println(a1.toString());
		System.out.println(a2.toString());
		
		a2.makeNoise();
		a2.setBreed("dog");
		
		a2.makeNoise(true);
		a2.makeNoise(false);
		
		Scanner input = new Scanner(System.in);
		System.out.println("What is the name?");
		String name = input.nextLine();
		System.out.println("What is the breed?");
		String breed = input.nextLine();
		System.out.println("What is the age?");
		int age = Integer.parseInt(input.nextLine());
		System.out.println("Is the animal domestic?");
		boolean domesticAnimal = Boolean.parseBoolean(input.nextLine());
		System.out.println("What is the colour?");
		String colour = input.nextLine();
		input.close();
		
		Animal a3 = new Animal(name,breed,age,domesticAnimal,colour);
		System.out.println(a3);
	}
}
