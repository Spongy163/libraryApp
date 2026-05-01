/* Brighton Drill
 * Title: Library
 * Date: 2/24/26
 * Description: An object that simulates a library containing a database and ways to search for books
 */


package Project1;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import FileHandling.ItemType;
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
	public LibraryItem findItemByItemID(String itemID) throws IllegalArgumentException {
		LibraryItem libraryItem = database.findItemByitemID(itemID);
		
		if(libraryItem == null) {
			throw new IllegalArgumentException();
		}
		
		return libraryItem;
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
	 * calls Database findBooksWithKeyword()
	 *
	 * @param keyword
	 * @return an ArrayList<Book> of all matching books.
	 */
	public ArrayList<LibraryItem> searchItemsByTitle(String keyword, boolean[] selectedItemsList) {
		ArrayList<LibraryItem> foundItems = database.findItemsByTitle(keyword);
		
		ArrayList<LibraryItem> selectedItems = new ArrayList<>();
		
		for(LibraryItem item : foundItems) {
			if(item.getItemType().equals(ItemType.BOOK) && selectedItemsList[0]) {
				selectedItems.add(item);
			} else if(item.getItemType().equals(ItemType.PERIODICAL) && selectedItemsList[1]) {
				selectedItems.add(item);
			} else if(item.getItemType().equals(ItemType.RECORDINGS) && selectedItemsList[2]) {
				selectedItems.add(item);
			}
		}
		
		
		return selectedItems;
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
	public User findUserByID(String userString) throws NoSuchElementException {
		User user = database.findUserByuserID(userString);
		
		if(user == null) {
			throw new NoSuchElementException();
		}
		
		return user;
	}
	
	/**
	 * Calls database for an array of overdue LibraryItems
	 * prints out the array
	 */
	public ArrayList<LibraryItem> getOverdueItems() {
		ArrayList<LibraryItem> overdue = database.findOverdueItems();
		
		return overdue;
	}
	
	/**
	 * returns analytic arraylist
	 */
	public String returnAnalytics() {
		StringBuilder analysisBuilder = new StringBuilder();
		
		//data
		ArrayList<LogEntry> analytics = database.returnAnalytics();
		
		analysisBuilder.append("Data Analysis (LogEntry records) \n\n");
		
		analysisBuilder.append("Total Transactions: " + analytics.size() + "\n");
		
		//counting checkout and return
		int checkoutCount = 0;
		int returnCount = 0;
		for(LogEntry logEntry : analytics) {
			if(logEntry.getAction().equalsIgnoreCase("checkout")) {
				checkoutCount ++;
			} 
			if (logEntry.getAction().equalsIgnoreCase("return")) {
				returnCount ++;
			}
		}
		
		analysisBuilder.append("CHECKOUT transactions: " + checkoutCount + "\n");
		analysisBuilder.append("RETURN transactions: " + returnCount + "\n\n");
		
		//Transactions per month builder
		int[] checkoutMonths = new int[12];
		int[] returnMonths = new int[12];
		
		for(LogEntry logEntry : analytics) {
			int month = (logEntry.getTimeStamp().getMonthValue()) - 1; // month as value minus one for array 
			
			if(logEntry.getAction().equalsIgnoreCase("checkout")) {
				checkoutMonths[month]++;
			} 
			if (logEntry.getAction().equalsIgnoreCase("return")) {
				returnMonths[month]++;
			}
		}
		
		analysisBuilder.append("Transactions per Month\n");
		analysisBuilder.append("Month      CHECKOUT           RETURN\n");
		analysisBuilder.append(String.format("Jan:          %02d                %02d%n", checkoutMonths[0], returnMonths[0]));
		analysisBuilder.append(String.format("Feb:          %02d                %02d%n", checkoutMonths[1], returnMonths[1]));
		analysisBuilder.append(String.format("Mar:          %02d                %02d%n", checkoutMonths[2], returnMonths[2]));
		analysisBuilder.append(String.format("Apr:          %02d                %02d%n", checkoutMonths[3], returnMonths[3]));
		analysisBuilder.append(String.format("May:          %02d                %02d%n", checkoutMonths[4], returnMonths[4]));
		analysisBuilder.append(String.format("Jun:          %02d                %02d%n", checkoutMonths[5], returnMonths[5]));
		analysisBuilder.append(String.format("Jul:          %02d                %02d%n", checkoutMonths[6], returnMonths[6]));
		analysisBuilder.append(String.format("Aug:          %02d                %02d%n", checkoutMonths[7], returnMonths[7]));
		analysisBuilder.append(String.format("Sep:          %02d                %02d%n", checkoutMonths[8], returnMonths[8]));
		analysisBuilder.append(String.format("Oct:          %02d                %02d%n", checkoutMonths[9], returnMonths[9]));
		analysisBuilder.append(String.format("Nov:          %02d                %02d%n", checkoutMonths[10], returnMonths[10]));
		analysisBuilder.append(String.format("Dec:          %02d                %02d%n", checkoutMonths[11], returnMonths[11]));
		
		//totals by item types
		analysisBuilder.append("\nCheckout Totals by Item Types\n");
		int books = 0;
		int recordings = 0;
		for(LogEntry logEntry : analytics) {
			if(logEntry.getAction().equalsIgnoreCase("checkout")) {
				if(logEntry.getTitle().startsWith("ISBN")) {
					books++;
				} 
				if (logEntry.getTitle().startsWith("IRSC")) {
					recordings++;
				}
			} 
		}
		
		analysisBuilder.append("Books (ISBN):      " + books + "\n");
		analysisBuilder.append("Recordings (IRSC): " + recordings);
		
		return analysisBuilder.toString();
	}
	
	public void addData(String type, String title, String user, LocalDateTime timeStamp) {
		
		if(type.equalsIgnoreCase("Return")) {
			database.addReturnLog(title, user, timeStamp);
		} else if (type.equalsIgnoreCase("checkout")) {
			database.addCheckoutLog(title, user, timeStamp);
		} else {
			System.out.println("failed to add checkout or return log from string");
		}
		
	}
	
	/**
	 * @return database summary as a string
	 */
	public String returnSummary() {
		return database.getSummary().toString();
	}
	
	
}
