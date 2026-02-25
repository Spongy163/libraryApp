/* Brighton Drill
 * Title: StudentUser class
 * Date: 2/14/2026
 * Description: A data structure that stores information regarding library users and provides functionality as well.
 */


package Project1;

import java.util.ArrayList;

public class StudentUser {
	/* STATIC DATA FIELDS
	 * - userCount:int = counts how many users are created. Useful for data analytics
	 */
	
	private static int userCount = 0;
	
	/* INSTANCE DATA FIELDS
	 * - userID:String = Stores the user's ID
	 * - name:String = stores the student's name
	 * - major:String = stores the student's major
	 * - checkedOutBooks:ArrayList<Book> = Stores books currently checked out by the student. 
	 */
	
	private String userID;
	private String name;
	private String major;
	private ArrayList<Book> checkedOutBooks;

	/**
	 * CONSTRUCTOR
	 * Initializes a StudentUser object
	 * Accepts and stores userID and name
	 * Stores "CS" as the student's major
	 * Initializes checkedOutBooks as an empty ArrayList<Book>
	 * 
	 * @param userID sets user ID
	 * @param name sets user name
	 */
	
	public StudentUser(String userId, String name) {
		this.userID = userId;
		this.name = name;
		this.major = "CS";
		this.checkedOutBooks =  new ArrayList<>();
		userCount ++;
	}

	/**
	 * CONSTRUCTOR
	 * Initializes a StudentUser object
	 * Accepts and stores userID, name, and major
	 * Initializes checkedOutBooks as an empty ArrayList<Book>
	 * 
	 * @param userID sets user ID
	 * @param name sets user name
	 * @param major sets user major
	 */
	public StudentUser(String userId, String name, String major) {
		this(userId, name);
		this.major = major;
	}

	//GETTER and SETTER methods
	
	/**getUserCount()
	 * returns the number of users initialized
	 * 
	 * @return int the number of Users initialized 
	 */
	public static int getUserCount() {
		return userCount;
	}
	
	/**getUserID()
	 * returns the Student user's ID
	 * 
	 * @return userID
	 */
	public String getUserID() {
		return userID;
	}
	
	/**getUserIDAsInt()
	 * returns the Student user's ID as an int value
	 * useful for insertion sorting and binary search algorithms
	 * 
	 * @return userIDAsInt : int 
	 */
	public int getUserIDAsInt() {
		int userIDAsInt = Integer.parseInt(userID.substring(userID.indexOf('-') + 1));
		
		return userIDAsInt;
	}

	/**setUserID() *Overload
	 * sets the usersID to the parameter 
	 * 
	 * @param userID : String
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	/**setUserID() *Overload
	 * sets the userID to the parameter
	 * 
	 * @param userID : int
	 */
	public void setUserID(int userIDAsInt) {
		this.userID = "ID-" + userIDAsInt;
	}

	/**getName()
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**setName()
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**getMajor()
	 * 
	 * @return the major
	 */
	public String getMajor() {
		return major;
	}

	/**setMajor()
	 * 
	 * @param major the major to set
	 */
	public void setMajor(String major) {
		this.major = major;
	}

	/**getCheckedOutBooks()
	 * returns the Student's checked out books
	 * 
	 * @return checkedOutBooks
	 */
	public ArrayList<Book> getCheckedOutBooks() {
		return checkedOutBooks;
	}
	
	
	/* METHODS
	 * + addBook(book:Book):boolean = Will attempt to add a book to checkedOutBooks. Returns true if successful and false if unsuccessful.
	 * + removeBook(book:Book):boolean = Will attempt to remove a book from checkedOutBooks. Returns true if successful and false if unsuccessful.
	 * + getCheckedOutCount():int = Returns the number of books in checkedOutBooks.
	 * + hasBook(isbn:String):boolean = returns true if the student has a checked-out book with the inputed isbn number.
	 * + printInfo():void = prints the user attributes to the standard output.
	 * + toString():String = returns the user attributes as a string. overrides java toString() method.
	 */
	
	/**addBook() 
	 * Attempts to add a book to the User's checked out books
	 * 
	 * @param book
	 * @return boolean = whether the book addition was successful or unsuccessful
	 */
	public boolean addBook(Book book) {
		if(!book.isCheckedOut()) { //checkOut() method automatically switches isCheckedOut to true if valid
			book.checkOut();
			this.checkedOutBooks.add(book);
			return true;
		} 
		
		return false;
	}
	
	/**removeBook()
	 * Attempts to remove a book from the User's checked out books
	 * 
	 * @param book
	 * @return boolean = whether the book removal was successful or unsuccessful
	 */
	public boolean removeBook(Book book) {
		if(book.isCheckedOut() && hasBook(book.getIsbn())) { 
			book.returnBook();
			this.checkedOutBooks.remove(book);
			return true;
		} 
		return false;
	}
	
	/**getCheckedOutCount()
	 * returns how many books a Student has checked out
	 * 
	 * @return int = checkedOutBooks.size();
	 */
	public int getCheckedOutCount() {
		return checkedOutBooks.size();
	}
	
	/**hasBook() 
	 * Searches checkedOutBooks for a book based on isbn
	 * 
	 * @param isbn
	 * @return boolean: whether the book exists or not
	 */
	public boolean hasBook (String isbn) {
		for(Book book : checkedOutBooks) {
			if(book.getIsbn().equals(isbn)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**printInfo()
	 * prints the toString() method to the standard output
	 */
	public void printInfo() {
		System.out.println(this.toString());
	}
	
	/** toString()
	 * Overrides java toString() converts StudentUser to a string. 
	 * 
	 * @return Student User attributes as a string
	 */
	@Override
	public String toString() {
		return "User [userID=" + userID + ", checkedOutBooks=" + checkedOutBooks + "]";
	}

	
}