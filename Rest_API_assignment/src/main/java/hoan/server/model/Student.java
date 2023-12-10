package hoan.server.model;


public class Student {
	
	private int id;
	private String firstname;
	private String lastname;
	
	public Student() {
		
	}
	
	public Student(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstname = firstName;
		this.lastname = lastName;

	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}

	public String getLastName() {
		return lastname;
	}
	
	public void setLastName(String lastName) {
		this.lastname = lastName;
	}

}
