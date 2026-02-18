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
	
	// GETTER and SETTER
	
	//title
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	//isbn
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	//isCheckedOut
	public boolean isCheckedOut() {
		return isCheckedOut;
	}

	public void setCheckedOut(boolean isCheckedOut) {
		this.isCheckedOut = isCheckedOut;
	}
	
	//bookCount
	public static int getBookCount() {
		return bookCount;
	}
	
	//dueDate
	public LocalDate getDueDate() {
		return this.dueDate;
	}
	
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	/* METHODS
	 * checkOut():boolean = checks isCheckedOut status. If false sets isCheckedOut to true and checkOut() returns true. If true does nothing and checkOut() returns false.
	 * returnBook():boolean = checks isCheckedOut status. If true sets isCheckedOut to false and checkOut() returns true. If true does nothing and checkOut() returns false.
	 * printInfo():void = prints the toString() to the standard output
	 * toString():String = converts the most important attributes to a printable string
	 */
	
	//checkout
	public boolean checkOut() {
		if (!isCheckedOut) {
			isCheckedOut = true;
			return true;
		}
		return false;
	}
	
	//return book
	public boolean returnBook() {
		if (isCheckedOut) {
			isCheckedOut = false;
			return true;
		}
		return false;
	}
	
	//isOverdue
	
	
	//print info
	public void printInfo() {
		System.out.println(this.toString());
	}
	
	//toString() override 
	public String toString() {
		return "[Book Title: " + title + ", ISBN: " + isbn + ", Checked Out Status: " + isCheckedOut + "]";
	}
	
	
	
}