/* Brighton Drill
 * Title: Book Class
 * Date: 2/10/2025
 * Description: A class that simulates a library book
 */

package Project1;

import java.time.LocalDate;

public class Book {

	/* STATIC DATA FIELDS
	 * bookCount:int = class variable that stores how many books are created. 
	 */
	
	private static int bookCount;
	
	/* INSTANCE DATA FIELDS
	 * -title:String = stores the title of the book as a string
	 * -isbn:String = stores the ISBN number of the book
	 * -isCheckedOut:boolean = stores whether or not the book is checked out
	 * -dueDate:LocalDate = Stores a LocalDate object representing the book's due date. 
	 */
	
	private String title;
	private String isbn;
	private boolean isCheckedOut;
	private LocalDate dueDate;
	
	
	/* CONSTRUCTOR
	 * sets title and isbn to input
	 * isCheckedOut is left as false
	 * dueDate is left as null
	 */
	public Book(String title, String isbn) {
		this.title = title;
		this.isbn = isbn;
		bookCount ++;
	}
	
	// GETTER and SETTER Methods
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**setTitle()
	 * 
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**getIsbn()
	 * 
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}
	
	/**getIsbnAsInt()
	 * returns only the number portion of the isbn as an int
	 * 
	 * @return isbnAsInt : int
	 */
	public int getIsbnAsInt() {
		int isbnAsInt = Integer.parseInt(isbn.substring(isbn.indexOf('-') + 1));
		return isbnAsInt;
	}

	/**setIsbn() *overload
	 * 
	 * @param isbn : String, the formatted isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	/**setIsbn() *overload
	 * 
	 * 
	 * @param number : int, the isbn number alone without formatting
	 */
	public void setIsbn(int number) {
		this.isbn = "ISBN-" + number;
	}

	/**isCheckedOut()
	 * 
	 * @return isCheckedOut
	 */
	public boolean isCheckedOut() {
		return isCheckedOut;
	}

	/**setCheckedOut()
	 * 
	 * @param isCheckedOut set the checkout status
	 */
	public void setCheckedOut(boolean isCheckedOut) {
		this.isCheckedOut = isCheckedOut;
	}

	/**getDueDate()
	 * 
	 * @return the dueDate
	 */
	public LocalDate getDueDate() {
		return dueDate;
	}

	/**setDueDate
	 * 
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	/**getBookCount()
	 * 
	 * @return the bookCount
	 */
	public static int getBookCount() {
		return bookCount;
	}
	
	
	/* METHODS
	 * checkOut():boolean = checks isCheckedOut status. If false sets isCheckedOut to true and checkOut() returns true. If true does nothing and checkOut() returns false.
	 * returnBook():boolean = checks isCheckedOut status. If true sets isCheckedOut to false and checkOut() returns true. If true does nothing and checkOut() returns false.
	 * isOverdue():boolean = determines whether or not a book is overdue
	 * printInfo():void = prints the toString() to the standard output
	 * toString():String = converts the most important attributes to a printable string
	 */
	
	/**checkOut()
	 * checks isCheckedOut status. If false sets isCheckedOut to true and checkOut() returns true. If true does nothing and checkOut() returns false.
	 * 
	 * @return boolean the check out was successful
	 */
	public boolean checkOut() {
		if (!isCheckedOut) {
			isCheckedOut = true;
			return true;
		}
		return false;
	}

	/**returnBook()
	 * checks isCheckedOut status. If true sets isCheckedOut to false and checkOut() returns true. If true does nothing and checkOut() returns false.
	 * 
	 * @return boolean the return was successful
	 */
	public boolean returnBook() {
		if (isCheckedOut) {
			isCheckedOut = false;
			return true;
		}
		return false;
	}
	
	/**isOverdue()
	 * determines whether or not a book is overdue
	 * 
	 * @return boolean book is overdue
	 */
	public boolean isOverdue() {
		if(dueDate.isAfter(LocalDate.now())){
			return true;
		}
		return false;
	}
	
	
	/**printInfo()
	 * prints the toString() to the standard output
	 * 
	 */
	public void printInfo() {
		System.out.println(this.toString());
	}

	/**toString()
	 * converts the Book object into string format
	 */
	@Override
	public String toString() {
		return title + " " + isbn + " DueDate: " + dueDate + " Checked out:" + isCheckedOut;
	}
}