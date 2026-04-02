/* Brighton Drill
 * Title: Database
 * Date: 2/19/2026
 * Description: A class that simulates a library database. Keeps track of books, users,
 * and data log entries for data analytics. 
 */

package Project1;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import libraryItems.Book;
import libraryItems.LibraryItem;
import users.User;

public class Database {
	
	//----------------------------
	// Data Storage
	//----------------------------
	private ArrayList<LibraryItem> items; // Stores library items
	private ArrayList<User> users; // Stores library users
	private ArrayList<LogEntry> transactionLog; // Stores transaction data for data analytics 
	
	
	//----------------------------
	// Constructor
	//----------------------------
	
	/**
	 * Initializes all ArrayLists
	 */
	public Database() {
		this.items = new ArrayList<>();
		this.users = new ArrayList<>();
		this.transactionLog = new ArrayList<>();
	}
	
	
	//----------------------------
	// Add methods
	//----------------------------
	
	/**
	 * @param the item to add
	 */
	public void addItem(LibraryItem item) {
		items.add(item);
	}
	
	/**
	 * @param the user to add
	 */
	public void addUser(User user) {
		users.add(user);
	}
	
	
	//----------------------------
	// LogEntry Methods
	//---------------------------- 
	
	/**
	 * Adds a checkout log to transactionLog
	 * @param item 
	 * @param user
	 */
	public void addCheckoutLog(LibraryItem item, User user) {
		LogEntry data = new LogEntry("CHECKOUT", item.getTitle(), user.getName());
		transactionLog.add(data);
	}
	
	/**
	 * Adds a return log to transactionLog
	 * @param book
	 * @param user
	 */
	public void addReturnLog(LibraryItem item, User user) {
		LogEntry data = new LogEntry("RETURN", item.getTitle(), user.getName());
		transactionLog.add(data);
	}

	
	//----------------------------
	// Load Data
	//----------------------------

	public void loadUserFromFile() {
		  
	}
	
	
	/**
	 * 
	 * 
	 * 
	 * @param keyword
	 * @return
	 */
	public ArrayList<Book> findBooksWithKeyword(String keyword) {
		ArrayList<Book> matchingBooks = new ArrayList<>();
		
		for(Book book : books) {
			if(book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
				matchingBooks.add(book);
			}
			
		}
		
		if (matchingBooks.size() > 0) {
			return matchingBooks;
		}
		return null; 
	}
	
	/**
	 * Uses a binary search algorithm to return a user by userID
	 * comparisons are not determined by case but by '-' location
	 * Converts userID to int for processing 
	 * 
	 * @param userID : String formatted as ID-12345 (case insensitive)
	 * @return StudentUser = null if not found in users
	 */
	public StudentUser findUserById(String userID) {

		if (userID == null || !userID.contains("-")) {
			return null;
		}

		int key;

		try {
			key = Integer.parseInt(userID.substring(userID.indexOf('-') + 1));
		}
		catch (NumberFormatException | StringIndexOutOfBoundsException e) {
			// Invalid ID format
			return null;
		}

		int min = 0;
		int max = users.size() - 1;
		int mid;

		while (min <= max) {
			mid = (min + max) / 2;
			int midUserID = users.get(mid).getUserIDAsInt();

			if (midUserID == key) {
				return users.get(mid);
			}
			else if (midUserID > key) {
				max = mid - 1;
			}
			else {
				min = mid + 1;
			}
		}

		return null;
	}
	
	/**
	 * returns an ArrayList of books that are overdue
	 * 
	 * @return an ArrayList of books that are overdue
	 */
	public ArrayList<Book> findOverdueBooks() {
		ArrayList<Book> overdueBooks = new ArrayList<>();
		
		for(Book book : books) {
			if(book.isOverdue() && book.isCheckedOut()) {
				overdueBooks.add(book);
			}
		}
		
		if(overdueBooks.size() > 0) {
			return overdueBooks;
		}
		
		return null;
	}
	
	/**
	 * returns data transaction logs
	 * 
	 * @return analytics
	 */
	public ArrayList<LogEntry> returnAnalytics() {
		ArrayList<LogEntry> analytics = new ArrayList<>();
		
		for (LogEntry data : transactionLog) {
			analytics.add(data);
		}
		
		return analytics;
	}
	
	@Override
	public String toString() {
		return "=== Database System Summary ===\n" +
				"Total Books: " + books.size() + "\n" +
				"Total StudentUsers: " + users.size() + "\n" +
				"Total Transactions: " + transactionLog.size() + "\n" +
				"==============================\n";
	}
	
	public void printSummary() {
		System.out.println(this.toString());
	}
	
	
	
	
	
}
