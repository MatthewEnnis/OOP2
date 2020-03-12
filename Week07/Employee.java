public class Employee extends Person
{
	private Job job;
	private int personnelNumber;
	private Date startDate;
	private static int nextNumber = 0;

	//Constructors

	public Employee(String firstName, String surName, Date dateOfBirth, String gender, Job job, Date startDate)
	{
		super(firstName, surName, dateOfBirth, gender);
		setJob(job);
		this.personnelNumber = nextNumber;
		this.startDate = startDate;
		nextNumber++;
	}
	
	//Methods
	
	public String toString()
	{
		FileProcessor writer = new FileProcessor("src/output.txt","write");
		writer.write(super.toString()+" "+getJob()+" "+getPersonnelNumber());
		return super.toString()+" "+getJob()+" "+getPersonnelNumber();
	}

	//Getters and setters

	public Job getJob() {return job;}
	public void setJob(Job job) {this.job = job;}

	public int getPersonnelNumber() {return personnelNumber;}
	public void setPersonnelNumber(int personnelNumber) {this.personnelNumber = personnelNumber;}

	public Date getStartDate() {return startDate;}
	public void setStartDate(Date startDate) {this.startDate = startDate;}

	public int getNextNumber() {return nextNumber;}
	public void setNextNumber(int nextNumber) {this.nextNumber = nextNumber;}
}