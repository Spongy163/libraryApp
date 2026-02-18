/* Brighton Drill
 * Title: User class
 * Date: 2/14/2026
 * Description: A data structure that stores information regarding library users and provides functionality as well.
 */


package Project1;

import java.util.ArrayList;

public class StudentUser {
	
	/* STATIC DATA FIELDS
	 * - defaultIDNumber:int = Creates a unique ID Number for a user created using the no argument constructor
	 */
	
	private static int defaultIDNumber = 100;
	
	/* INSTANCE DATA FIELDS
	 * - userID:String = Stores the user's ID
	 * - checkedOutBooks:ArrayList<Book> = Stores books currently checked out by the user
	 */
	
	private String userID;
	private ArrayList<Book> checkedOutBooks;
	
	/* CONSTRUCTORS
	 * + User() userID = Default constructor that uses defaultIDNumber to create a unique user ID and initializes checkedOutBooks.
	 * + User(userID:String) = A constructor that accepts and stores a custom userID and initializes checkedOutBooks.
	 */
	
	public StudentUser() {
		this.userID = "ID-" + defaultIDNumber;
		this.checkedOutBooks = new ArrayList<>();
		defaultIDNumber ++;
	}
	
	public StudentUser(String userID) {
		this.userID = userID;
		this.checkedOutBooks = new ArrayList<>();
	}

	//GETTER and SETTER methods
	
	//userID
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	//defaultIDNUMBER (Does not allow changing this number)
	public static int getDefaultIDNumber() {
		return defaultIDNumber;
	}
	
	//checkedOutBooks (Does not allow changing the reference)
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
	
	
	public boolean addBook(Book book) {
		if(!book.isCheckedOut()) { //checkOut() method automatically switches isCheckedOut to true if valid
			book.checkOut();
			this.checkedOutBooks.add(book);
			return true;
		} 
		
		return false;
	}
	
	public boolean removeBook(Book book) {
		if(book.isCheckedOut() && hasBook(book.getIsbn())) { 
			book.returnBook();
			this.checkedOutBooks.remove(book);
			return true;
		} 
		return false;
	}
	
	public int getCheckedOutCount() {
		return checkedOutBooks.size();
	}
	
	public boolean hasBook (String isbn) {
		for(Book book : checkedOutBooks) {
			if(book.getIsbn().equals(isbn)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void printInfo() {
		System.out.println(this.toString());
	}
	
	@Override
	public String toString() {
		return "User [userID=" + userID + ", checkedOutBooks=" + checkedOutBooks + "]";
	}
}