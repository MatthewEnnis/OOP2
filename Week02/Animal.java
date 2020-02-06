package Lab2;

public class Animal {
	private String name;
	private String breed;
	private int age;
	private boolean domesticAnimal;
	private String colour;
	
	//Constructors
	
	public Animal(String name)
	{
		this.name = name;
	}
	
	public Animal(String name, String breed, int age, boolean domesticAnimal, String colour)
	{
		this.name = name;
		this.breed = breed;
		this.age = age;
		this.domesticAnimal = domesticAnimal;
		this.colour = colour;
	}
	
	//Methods
	
	public String toString()
	{
		if (domesticAnimal)
			return name+", a "+age+" year old "+breed+" who is "+colour+" and is domestic";
		else
			return name+", a "+age+" year old "+breed+" who is "+colour+" and is not domestic";
	}
	
	public void makeNoise()
	{
		switch (breed)
		{
		case "dog":
			System.out.println("Bark!");
			break;
		case "cat":
			System.out.println("Meow!");
			break;
		default:
			System.out.println("...");
		}
	}
	
	public void makeNoise(boolean old)
	{
		if (old)
			System.out.println("Quiet animal");
		else
			makeNoise();
	}
	
	//Getters and setters
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	
	public void setBreed(String breed)
	{
		this.breed = breed;
	}
	public String getBreed()
	{
		return breed;
	}
	
	public void setAge(int age)
	{
		this.age = age;
	}
	public int getAge()
	{
		return age;
	}
	
	public void setDomesticAnimal(boolean domesticAnimal)
	{
		this.domesticAnimal = domesticAnimal;
	}
	public boolean getDomesticAnimal()
	{
		return domesticAnimal;
	}
	
	public void setColour(String colour)
	{
		this.colour= colour;
	}
	public String getColour()
	{
		return colour;
	}
	
	
}
