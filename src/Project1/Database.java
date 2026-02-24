/* Brighton Drill
 * Title: Database
 * Date: 2/19/2026
 * Description: A class that simulates a library database. Keeps track of books, users,
 * and data log entries for data analytics. 
 */

package Project1;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Database {

	/* INSTANCE DATA FIELDS
	 * -books:ArrayList<Book> = Stores all registered books in the library system. 
	 * -users:ArrayList<StudentUser> = Stores all registered users in the library system.
	 * -transactionLog:ArrayList<LogEntry> = Stores all library transaction records
	 */
	
	private ArrayList<Book> books;
	private ArrayList<StudentUser> users;
	private ArrayList<LogEntry> transactionLog;
	
	/**
	 * CONSTRUCTOR
	 * Initializes all instance ArrayLists
	 * 
	 * @param booksFilename the name of the file storing the books
	 * @param usersFilename the name of the file storing the users
	 */
	public Database(String booksFilename, String usersFilename) throws IOException {
		this.books = new ArrayList<Book>();
		this.users = new ArrayList<StudentUser>();
		this.transactionLog = new ArrayList<LogEntry>();
	}
	
	/* ADD METHODS
	 * +addBook(book:Book) = Add the provided book to the associated data structure (ArrayList).
	 * +addUser(user:StudentUser) = Register the provided user to the associated data structure.
	 * +addCheckoutLog(book:Book, user:StudentUser) = Add a checkout log (LogEntry) to the transaction log list.
	 * +addReturnLog(book:Book, user:StudentUser) = Add a return log (LogEntry) to the transaction log list.
	 */
	
	/**
	 * adds a Book to books in order of isbn number (smallest to greatest) by reverse insertion sort
	 * Time complexity: max: o(n)
	 * 
	 * @param book : Book, the book to be added
	 */
	public void addBook(Book book) {
		
		//checks if the books list is empty
		if (books.isEmpty()) {
			books.add(book);
			return;
		}
		
		//reverse insert algorithm
		for(int i = books.size() - 1; i >= 0; i--) {
			if(books.get(i).getIsbnAsInt() > book.getIsbnAsInt()) {
				books.add(i + 1, book);
				return;
			}
		}
		
		//if the book isbn is smaller than all of the other books isbns it will be first
		books.add(0, book);
	}
	
	/**
	 * adds a StudentUser to users in sorted order of studentID number (smallest to greatest) using reverse insertion sort
	 * Time complexity: max: o(n) 
	 * 
	 * @param user : StudentUser the StudentUser to add
	 */
	public void addUser(StudentUser user) {
		//checks if the users list is empty
		if(users.isEmpty()) {
			users.add(user);
			return;
		}
		
		//reverse insert algorithm
		for(int i = users.size() - 1; i >= 0; i--) {
			if(users.get(i).getUserIDAsInt() > user.getUserIDAsInt()) {
				users.add(i + 1, user);
				return;
			}
		}
		
		//if the userID is smaller than all of the other userIDs it will be first
		users.add(0, user);
		
	}
	
	/**
	 * adds a "CHECKOUT" LogEntry to transactionLog
	 * 
	 * @param book
	 * @param user
	 */
	public void addCheckOutLog(Book book, StudentUser user) {
		LogEntry data = new LogEntry(book.getTitle(), user.getName(), "CHECKOUT");
		transactionLog.add(data);
	}
	
	/**
	 * adds a "RETURN" LogEntry to transactionLog
	 * 
	 * @param book
	 * @param user
	 */
	public void addReturnLog(Book book, StudentUser user) {
		LogEntry data = new LogEntry(book.getTitle(), user.getName(), "RETURN");
		transactionLog.add(data);
	}
	
	/* METHODS
	 * +loadBooksFromFile(filename: String) = Reads book information from the specified input file and stores the created Book objects to the associated data structure.
	 * +loadStudentUserFromFile(filename: String) = Reads user information from the specified input file and store the instantiated StudentUser objects to the associated data structure.
	 * +findBookByISBN(isbn:String):Book = Searches for and returns the Book object with the matching ISBN. Returns null if not found.
	 * +findUserById(userId:String):StudentUser = Searches for and returns the StudentUser object with the matching user ID. Returns null if not found.
	 * +printSummary() = Prints a summary of the database—including the total number of books, users, and transaction records—to standard output
	 */
	
	/**
	 * Counts how many lines are in a file
	 * used by loadBooksFromFile and loadStudentUsersFromFile
	 * 
	 * @param file the file being counted
	 * @return int the number of lines inside the file
	 */
	private int countFileLines(File file) throws IOException {
		Scanner lineCounter = new Scanner(file);
		int lineCount = 0;
		
		while (lineCounter.hasNextLine()) {
			lineCounter.nextLine();
			lineCount++;
		}
		
		lineCounter.close();
		return lineCount;
	}
	
	
	/**
	 * Loads books from a file in isbn number order using a reverse insertion sort (defined in addBook)
	 * When loading the first file:
	 * Time complexity for unsorted list: min: o(n) + 1 max: o(n^2) 
	 * Time complexity for a sorted list: o(n) (ie. from save file)
	 * 
	 * Advantage: Allows faster searching using binary search
	 * Disadvantage: Longer boot up time for an unsorted list
	 * 
	 * @param filename the name of the data file to load
	 * @throws IOException
	 */
	public void loadBooksFromFile(String filename) throws IOException {
		File bookStorage = new File(filename);
		int lineCount = countFileLines(bookStorage);
		
		Scanner fileReader = new Scanner(bookStorage);
		for(int i = 0; i < lineCount; i++) {
			
			//reads and splits the line by " " 
			String[] split = fileReader.nextLine().trim().split(" ");
			Book book = new Book(split[0], "ISBN-" + split[1]);
			
			addBook(book);
			
		}
	}
	
	/**
	 * Loads books from a file in numerical UserID order using a reverse insertion sort (defined in addUser)
	 * When loading the first file:
	 * Time complexity for an unsorted list: min: o(n) + 1 max: o(n^2)
	 * Time complexity for a sorted list: o(n) (ie. from save file)
	 * 
	 * @param filename the name of the data file to load
	 */
	public void loadStudentUsersFromFile(String filename) throws IOException {
		File userStorage = new File(filename);
	    int lineCount = countFileLines(userStorage);
	    
	    Scanner fileReader = new Scanner(userStorage);
	    for (int i = 0; i < lineCount; i++) {
	    	
	    	// reads and splits the line by " "
	    	String[] split = fileReader.nextLine().trim().split(" ");
	    	StudentUser user = new StudentUser(split[0], split[1], split[2]);
	    	
	    	addUser(user);
	    	
	    }
	}
	
	public void loadTransactionLogFromSaveFile(String filename) throws IOException {
		File transactionStorage = new File(filename);
		int lineCount = countFileLines(transactionStorage);
		
		Scanner fileReader = new Scanner(transactionStorage);
		for(int i = 0; i < lineCount; i++) {
			LogEntry entry;
			String[] split = fileReader.nextLine().trim().split(" ");
			
			entry = new LogEntry(split[0], split[1], split[2], split[3]);
			transactionLog.add(entry);
		}
		
	}
	
	/**
	 * Uses a binary search algorithm to return a book by ISBN
	 * Converts ISBN to an int for comparisons 
	 * comparisons are not determined by case but by '-' location
	 * Time complexity o(log n) 
	 * 
	 * @param isbn : String the ISBN to be found
	 */
	public Book findBookByISBN(String isbn) {
	
		int key = Integer.parseInt(isbn.substring(isbn.indexOf('-') + 1));
		int min = 0;
		int max = books.size() - 1;
		int mid = (max + min) / 2; //initialized to prevent syntax error "mid may not have been initialized" 
		
		while (min <= max) {
			mid = (min + max) / 2;
			int midISBN = books.get(mid).getIsbnAsInt();
			if(midISBN == key) {
				return books.get(mid);
			} else if (midISBN > key){
				max = mid - 1;
			} else if (midISBN < key) {
				min = mid + 1;
			}
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
		
		int key = Integer.parseInt(userID.substring(userID.indexOf('-') + 1));
		int min = 0;
		int max = users.size() - 1;
		int mid = (max + min) / 2;
		
		while (min <= max) {
			mid = (max + min) / 2;
			int midUserID = users.get(mid).getUserIDAsInt();
			if(midUserID == key) {
				return users.get(mid);
			} else if (midUserID > key) {
				max = mid - 1;
			} else if (midUserID < key) {
				min = mid + 1;
			}
		}
		
		return null;
	}
	
	/** 
	 * Saves the current userIDs, Books, and transaction logs to seperate files for long term storage
	 * Keeps the data in sorted order for faster boot times
	 * only one save available 
	 * replaces old save
	 */
	public void saveData() throws IOException {
		PrintWriter bookSave = new PrintWriter("bookStorage");
		PrintWriter userSave = new PrintWriter("userStorage");
		PrintWriter transactionSave = new PrintWriter("transactionStorage");
		
		for(Book book : books) {
			bookSave.println(book.getTitle() + ' ' + book.getIsbnAsInt());
		}
		for(StudentUser user : users) {
			userSave.println(user.getUserID() + ' ' + user.getName() + ' ' + user.getMajor());
		}
		for(LogEntry entry : transactionLog) {
			transactionSave.println(entry.getAction() + ' ' + entry.getTitle() + ' ' + entry.getUserName() + ' ' + entry.getTimeStamp());
		}
		
		bookSave.close();
		userSave.close();
		transactionSave.close();
	}

	@Override
	public String toString() {
		return "--- Database System Summary ---\n" +
				"Total Books: " + books.size() + "\n" +
				"Total StudentUsers: " + users.size() + "\n" +
				"Total Transactions" + transactionLog.size() + "\n" +
				"------------------------------\n";
	}
	
	public void printSummary() {
		System.out.println(this.toString());
	}
	
	
	
	
	
}
