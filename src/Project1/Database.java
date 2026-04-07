/* Brighton Drill
 * Title: Database
 * Date: 2/19/2026
 * Description: A class that simulates a library database. Keeps track of books, users,
 * and data log entries for data analytics. 
 */

package Project1;

import java.util.ArrayList;

import FileHandling.ItemType;
import FileHandling.LibraryItemLoader;
import FileHandling.UserLoader;
import FileHandling.UserType;
import libraryItems.Borrowable;
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

	/**
	 * Reads library item information from an input file and creates LibraryItem classes to store in items<LibraryItem>
	 * @param filename the LibraryItem file name
	 */
	public void loadItemFromFile (String filename) {
		LibraryItemLoader databaseLoader = new LibraryItemLoader(filename);
		
		ArrayList<LibraryItem> data = databaseLoader.readData();
		
		for(LibraryItem libraryItem : data) {
			addItem(libraryItem);
		}
	}

	

	public void loadUserFromFile(String filename) {
		UserLoader databaseLoader = new UserLoader(filename);
		
		ArrayList<User> data = databaseLoader.readData();
		
		for(User user : data) {
			addUser(user);
		}
	}
	
	/**
	 * finds a library item by ID
	 * @param item to find
	 * @return the object that matches the id
	 */
	public LibraryItem findItemByitemID(String item) {
		
		for (LibraryItem libraryItem : items) {
			if(libraryItem.getItemID().equals(item)) {
				return libraryItem;
			}
		}
		
		System.out.println("Error: Item not found by ID");
		return null;
	}
	
	/**
	 * Returns an arraylist of items that match the provided keyword
	 * @param title
	 * @return an arraylist of items relating to the keyword
	 */
	public ArrayList<LibraryItem> findItemsByTitle(String title) {
		ArrayList<LibraryItem> matchingItems = new ArrayList<>();
		
		for(LibraryItem libraryItem : items) {
			if(libraryItem.getTitle().equalsIgnoreCase(title)) {
				matchingItems.add(libraryItem);
			}
		}
		
		if (matchingItems.size() > 0) {
			return matchingItems;
		}
		return null; 
	}
	
	public User findUserByuserID(String item) {
		
		for (User user : users) {
			if(user.getUserID().equals(item)) {
				return user;
			}
		}
		
		System.out.println("Error: User not found by ID");
		return null;
	}
	
	
	
	/**
	 * returns an ArrayList of books that are overdue
	 * @return ArrayList<LibraryItem> overdueBooks
	 */
	public ArrayList<LibraryItem> findOverdueItems() {
		ArrayList<LibraryItem> overdueBooks = new ArrayList<>();
		
		for(LibraryItem libraryItem : items) {
			if(!(libraryItem instanceof Borrowable)) {
				continue;
			}
			
			if(((Borrowable)libraryItem).isOverdue() && libraryItem.isCheckedOut()) {
				overdueBooks.add(libraryItem);
			}
		}
		
		if(overdueBooks.size() > 0) {
			return overdueBooks;
		}
		
		return null;
	}
	
	/**
	 * returns data transaction logs
	 * @return analytics
	 */
	public ArrayList<LogEntry> returnAnalytics() {
		ArrayList<LogEntry> analytics = new ArrayList<>();
		
		for (LogEntry data : transactionLog) {
			analytics.add(data);
		}
		
		return analytics;
	}
	
	/**
	 * prints a database system summary
	 * @return String system summary
	 */
	@Override
	public String toString() {
		return "=== Database System Summary ===\n" +
				"Total Library Items: " + items.size() + "\n" +
				"Total Users: " + users.size() + "\n" +
				"Total Transactions: " + transactionLog.size() + "\n" +
				"==============================\n";
	}
	
	/**
	 * counts each type of item in items and returns the count
	 * @return an arraylist of data on item counts
	 */
	private int[] countItemsForSummary() {
		int[] itemsCount = new int[3];
		for(LibraryItem libraryItem : items) {
			ItemType key = libraryItem.getItemType();
		
			switch (key) {
				case BOOK:
					itemsCount[0] ++;
					break;
				case PERIODICAL:
					itemsCount[1] ++;
					break;
				case RECORDINGS: 
					itemsCount[2] ++;
					break;
				default: 
					System.out.println("Error: Failed to find correct item type for summary");
					break;
			}
		}
		return itemsCount;
	}
	
	/**
	 * counts each type of user in users and returns the count
	 * @return an arraylist of data on user counts
	 */
	private int[] countUsersForSummary() {
		int[] usersCount = new int[2];
		for(User user : users) {
			UserType key = user.getUserType();
			
			switch(key) {
				case STUDENT:
					usersCount[0] ++;
					break;
				case FACULTY: 
					usersCount[1] ++;
					break;
				default:
					System.out.println("Error: Failed to find correct user type for summary");
					break;
			}
		}
		return usersCount;
	}
	
	/**
	 * goes through users and determines the different unique majors
	 * @return an arraylist of unique majors
	 */
	private ArrayList<String> getUniqueMajorsForSummary() {
		ArrayList<String> uniqueMajors = new ArrayList<>();
		
		for(User user : users) {
			boolean unique = true;
			String userMajor = user.getMajor();
			
			for(String uniqueMajor : uniqueMajors) {
				if(userMajor.equals(uniqueMajor)) {
					unique = false;
					break;
				}
			}
			
			if(unique) {
					uniqueMajors.add(userMajor);
			}
		}
		return uniqueMajors;
	}
	
	/**
	 * counts how many of each unique major there are
	 * @param uniqueMajors
	 * @return a parallel array of counts for uniqueMajors
	 */
	private int[] countMajorsForSummary(ArrayList<String> uniqueMajors) {
		int[] majorCount = new int[uniqueMajors.size()];
			
		for(User user : users) {
			for(int i = 0; i < uniqueMajors.size(); i ++) {
				if(uniqueMajors.get(i).equals(user.getMajor())) {
					majorCount[i] ++;
					break;
				}
			}
		}
		
		return majorCount;
	}
	
	
	/**
	 * prepares a summary as a StringBuilder
	 * @return StringBuilder summary
	 */
	public StringBuilder getSummary() {
		StringBuilder summary = new StringBuilder(this.toString());
		summary.append("\n");
		
		
		//itemAnalysis
		int[] LibraryItemCount = countItemsForSummary();
		
		summary.append("\n===ITEMS ANALYSIS===");
		summary.append("\nBooks: ").append(LibraryItemCount[0]);
		summary.append("\nPeriodicals: ").append(LibraryItemCount[1]);
		summary.append("\nRecordings: ").append(LibraryItemCount[2]);
		summary.append("\n\n");
		
		//user analysis
		int[] userCounts = countUsersForSummary();
		
		summary.append("\n===USERS ANALYSIS===");
		summary.append("\nTotal Students: ").append(userCounts[0]);
		summary.append("\nTotal Faculty: ").append(userCounts[1]);
		summary.append("\n\n");
		
		//major analysis 
		ArrayList<String> uniqueMajors = getUniqueMajorsForSummary();
		int[] uniqueMajorCounts = countMajorsForSummary(uniqueMajors);
		
		for(int i = 0; i < uniqueMajors.size(); i++) {
			try {
				summary.append(uniqueMajors.get(i)).append(" Majors: ").append(uniqueMajorCounts[i]);
			} catch (ArrayIndexOutOfBoundsException aiobe) {
				System.out.println("Error: array out of bounds for unique major count " + aiobe);
			}
			summary.append("\n");
		}
		
		summary.append("====================================");
	
		
		
		return summary;
	}
	
	
	
	
}
