package users;

import FileHandling.UserType;

public class Faculty extends User {
	//----------------------------
	// Data Fields
	//----------------------------
	public static final int MAX_LIMIT = 8; // max number of library item checkouts
	public static final int LOAN_PERIOD = 45; // loan period in days
	
	private String title;

	
	//----------------------------
	// Constructor
	//----------------------------
	public Faculty(String userID, String name, String major, String title) {
		super(userID, name, major);
		this.title = title;
	}
	
	/**
	 * Returns the faculty’s maximum number of borrowed items.
	 */
	@Override
	public int getCheckoutLimit() {
		return MAX_LIMIT;
	}

	/**
	 * Returns the faculty’s loan period in days.
	 */
	@Override
	public int getLoanPeriod() {
		return LOAN_PERIOD;
	}

	/**
	 * Returns the UserType enum
	 */
	@Override
	public UserType getUserType() {
		return UserType.FACULTY;
	}

	/**
	 *  returns the object as a string
	 */
	@Override
	public String toString() {
		return super.toString() + ", Title: " + title;
	}
}
