/* Brighton Drill
 * Title: Book Class
 * Date: 2/10/2025
 * Description: A class that simulates a library book
 */

package libraryItems;

import java.time.LocalDate;
import FileHandling.ItemType;

public class Book extends LibraryItem implements Borrowable {
	//----------------------------
	// Data Fields
	//----------------------------
	final private String author; // Stores the author of the book
	final private String genre; // Stores the genre of the book
	
	
	//----------------------------
	// Constructor
	//----------------------------
	
	/**
	 * Initializes a Book object
	 * @param itemID
	 * @param publisher
	 * @param title
	 * @param author
	 * @param genre
	 */
	public Book(String itemID, String publisher, String title, String author, String genre) {
		super(itemID, publisher, title);
		this.author = author;
		this.genre = genre;
	}
	

	//----------------------------
	// Getter and Setter Methods
	//----------------------------


	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}
	
	//----------------------------
	// Borrowable Implementation
	//----------------------------
	
	/**
	 * Attempts a checkout of the book
	 * @param days allowed for borrow
	 * @return The result of the operation
	 */
	@Override
	public boolean checkOut(int days) {
		if (!isCheckedOut) {
			isCheckedOut = true;
			dueDate = LocalDate.now().plusDays(days);
			return true;
		}
		return false;
	}

	/**
	 * Attempts to return the book
	 * @return The result of the operation
	 */
	@Override
	public boolean returnItem() {
		if (isCheckedOut) {
			isCheckedOut = false;
			dueDate = null;
			return true;
		}
		return false;
	}
	
	/**
	 * Checks whether the book is overdue
	 * @return The overdue status
	 */
	@Override
	public boolean isOverdue() {
		if (dueDate == null) {return false;} // Validity check
		
		if(LocalDate.now().isAfter(dueDate)) {return true;} // Checks if it is after the due date
		
		return false; // base case
	}
	
	
	//----------------------------
	// Library Item implementation
	//----------------------------
	
	/**
	 * @return Returns an enum of the ItemType
	 */
	@Override
	public ItemType getItemType() {
		return ItemType.BOOK;
	}
	
	
	/**toString()
	 * converts the Book object into string format
	 */
	@Override
	public String toString() {
		return super.toString() + "\nAuthor: " + author + "\nGenre: " + genre + "\n"; 
	}
	
	/**
	 * returns a nicer outputString 
	 */
	@Override
	public String outputString() {
		return super.getTitle() + " " + getItemType();
	}
}