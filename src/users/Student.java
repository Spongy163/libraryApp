package users;

public class Student extends User {

	//----------------------------
	// Data Fields
	//----------------------------
	public static final int MAX_LIMIT = 5; // The check out limit for a Student
	public static final int LOAN_PERIOD = 30; // The loan period for a student in days
	
	private String classStanding; // Stores the student's class standing
	
	
	//----------------------------
	// Constructor
	//----------------------------
	public Student(String userID, String name, String major, String classStanding) {
		super(userID, name, major);
		this.classStanding = classStanding;
	}

	
	//----------------------------
	// Overriding Methods
	//----------------------------
	
	/**
	 * @return the checkout limit
	 */
	@Override
	public int getCheckoutLimit() {
		return Student.MAX_LIMIT;
	}

	/**
	 * @return the student loan period
	 */
	@Override
	public int getLoanPeriod() {
		return Student.LOAN_PERIOD;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return super.toString() + ", Class standing: " + classStanding;
	}
}
