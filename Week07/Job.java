public class Job
{
	private double salary;
	private String role;
	private int jobID;

	//Constructors

	public Job(double salary, String role, int jobID)
	{
		this.salary = salary;
		this.role = role;
		this.jobID = jobID;
	}
	
	//Methods
	
	public String toString()
	{
		return getRole();
	}

	//Getters and setters

	public double getSalary() {return salary;}
	public void setSalary(double salary) {this.salary = salary;}

	public String getRole() {return role;}
	public void setRole(String role) {this.role = role;}

	public int getJobID() {return jobID;}
	public void setJobID(int jobID) {this.jobID = jobID;}
}