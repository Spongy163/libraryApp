/* Brighton Drill
 * Title: Library
 * Date: 2/24/26
 * Description: An object that simulates a library containing a database and ways to search for books
 */


package Project1;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import libraryItems.Book;
import libraryItems.LibraryItem;
import users.User;

public class Library {
	
	/* INSTANCE DATA FIELDS
	 * -database:Database = Stores all registered books, users, and transaction records.
	 */
	
	private Database database;
	
	/**
	 * CONSTRUCTOR
	 * load from unsorted input files constructor
	 * 
	 * @param itemsFilename
	 * @param usersFilename
	 * @throws IOException
	 */
	public Library(String itemsFilename, String usersFilename) {
		this.database = new Database();
		this.database.loadItemFromFile(itemsFilename);
		this.database.loadUserFromFile(usersFilename); 
	}
	
	
	//METHODS
	
	/**
	 * finds item by id
	 * 
	 * @param itemID
	 * @return Book or Null
	 */
	public LibraryItem findItemByItemID(String itemID) {
		return database.findItemByitemID(itemID);
	}
	
	/**
	 * calls Database findBooksWithKeyword()
	 *
	 * @param keyword
	 * @return an ArrayList<Book> of all matching books.
	 */
	public ArrayList<LibraryItem> searchItemsByTitle(String keyword) {
		return database.findItemsByTitle(keyword);
	}
	
	
	
	/**
	 * checks out a book to the user
	 * 
	 * @param user
	 * @param libraryItem
	 * @return boolean : check out result
	 */
	public boolean checkoutItem(User user, LibraryItem libraryItem) {
		

		/* Checks if the book and user are real
		 * Checks if the book and user do not belong to the library
		 * returns false if any of these are true
		 */
		if(user == null||libraryItem == null||database.findItemByitemID(libraryItem.getItemID()) == null||database.findUserByuserID(user.getUserID()) == null){
			return false;
		}
		
		boolean result = user.addItem(libraryItem);
		if(result) {
			database.addCheckoutLog(libraryItem, user);
		}
		return result;
	}
	
	/**
	 * returns a book from a user
	 * 
	 * @param user
	 * @param libraryItem
	 * @return
	 */
	public boolean returnItem(User user, LibraryItem libraryItem) {
		

		/* Checks if the item and user are null
		 * Checks if the item and user do not belong to the library
		 * returns false if any of these are true
		 */
		if(user == null||libraryItem == null||database.findItemByitemID(libraryItem.getItemID()) == null||database.findUserByuserID(user.getUserID()) == null) {
			return false;
		}
		
		boolean result = user.removeItem(libraryItem);
		if(result) {
			database.addReturnLog(libraryItem, user);
		}
		
		return result;
		
	}
	
	/**
	 * gets expected return date if the book is checked out
	 * 
	 * @param itemID
	 * @return
	 */
	public LocalDate getExpectedReturnDate(String itemID) {
		LibraryItem item = database.findItemByitemID(itemID);
		
		if(item != null && item.isCheckedOut()) {
			return item.getDueDate();
		}
		
		return null;
	}
	
	/**
	 * calls database findUserById method
	 * 
	 * @param user
	 * @return
	 */
	public User findUserByID(String user) {
		return database.findUserByuserID(user);
	}
	
	/**
	 * Calls database for an array of overdue books
	 * prints out the array
	 */
	public ArrayList<LibraryItem> getOverdueItems() {
		ArrayList<LibraryItem> overdue = database.findOverdueItems();
		
		return overdue;
	}
	
	/**
	 * 
	 */
	public ArrayList<LogEntry> returnAnalytics() {
		return database.returnAnalytics();
	}
	
	public String returnSummary() {
		return database.getSummary().toString();
	}
	
	
}
