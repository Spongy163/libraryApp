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
	 * -books:ArrayList<Book> = Stores all registered books in the library system in order of isbn
	 * -booksST:ArrayList<Book> = books sorted by title
	 * -users:ArrayList<StudentUser> = Stores all registered users in the library system.
	 * -transactionLog:ArrayList<LogEntry> = Stores all library transaction records
	 */
	
	//Arrays
	private ArrayList<Book> books; //sorted isbn
	private ArrayList<Book> booksST; //sorted title
	private ArrayList<StudentUser> users; 
	private ArrayList<LogEntry> transactionLog;
	
	/**
	 * CONSTRUCTOR
	 * Initializes all instance ArrayLists
	 * 
	 * @param booksFilename the name of the file storing the books
	 * @param usersFilename the name of the file storing the users
	 */
	public Database() {
		this.books = new ArrayList<>();
		this.booksST = new ArrayList<>();
		this.users = new ArrayList<>();
		this.transactionLog = new ArrayList<>();
	}
	
	/* ADD METHODS
	 * -addIsbnSortedBook(book:Book) = adds a book to books in it's sorted position by isbn
	 * -addTitleSortedBook(book:Book) = adds a book to booksST in it's sorted position by title
	 * +addBook(book:Book) = Add the provided book to the associated data structures.
	 * +addUser(user:StudentUser) = Register the provided user to the list users
	 * +addCheckoutLog(book:Book, user:StudentUser) = Add a checkout log (LogEntry) to the transaction log list.
	 * +addReturnLog(book:Book, user:StudentUser) = Add a return log (LogEntry) to the transaction log list.
	 */
	
	
	/**
	 * adds a Book to books in order of isbn number (smallest to greatest) using reversed insertion sort
	 * If a sorted list is being inputed in sorted order to addBook() the time complexity will always be o(1)
	 * Time complexity: max: o(n)
	 * 
	 * @param book
	 */
	private void addIsbnSortedBook(Book book) {
		//checks if the books list is empty
				if (books.isEmpty()) {
					books.add(book);
					return;
				}
				
				//reverse insert algorithm
				for(int i = books.size() - 1; i >= 0; i--) {
					if(books.get(i).getIsbnAsLong() <= book.getIsbnAsLong()) {
						books.add(i + 1, book);
						return;
					}
				}
				
				//if the book isbn is smaller than all of the other books isbns it will be first
				books.add(0, book);
	}
	
	private void addTitleSortedBook(Book book) {
		//Checks if the books list is empty
			if(booksST.isEmpty()) {
				booksST.add(book);
				return;
			}
			
			//reverse insert algorithm
			for(int i = booksST.size() - 1; i >= 0; i--) {
				if(book.getTitle().compareTo(booksST.get(i).getTitle()) >= 0) {
					booksST.add(i + 1, book);
					return;
				}
			}
			
			//if the book title is first in alphabetical order
			booksST.add(0, book);
	}
	
	
	/**
	 * 
	 * 
	 * @param book : Book, the book to be added
	 */
	public void addBook(Book book) {
		addIsbnSortedBook(book);
		addTitleSortedBook(book);
	}
	
	/**
	 * 
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
			if(users.get(i).getUserIDAsInt() <= user.getUserIDAsInt()) {
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
	public void addCheckoutLog(Book book, StudentUser user) {
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
	 * Time complexity for unsorted list: min: o(n) max: o(n^2) 
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
		fileReader.close();
		
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
	    
	    fileReader.close();
	}
	
	
	
	/**
	 * Uses a binary search algorithm to return a book by ISBN
	 * Converts ISBN to an long for comparisons 
	 * comparisons are not determined by case but by '-' location
	 * Will return null if there is no '-'
	 * Time complexity o(log n) 
	 * 
	 * @param isbn : String the ISBN to be found
	 */
	public Book findBookByISBN(String isbn) {

		if (isbn == null || !isbn.contains("-")) {
			return null;
		}

		long key;

		try {
			key = Long.parseLong(isbn.substring(isbn.indexOf('-') + 1));
		}
		catch (NumberFormatException e) {
			// Invalid ISBN format
			return null;
		}

		int min = 0;
		int max = books.size() - 1;
		int mid;

		while (min <= max) {
			mid = (min + max) / 2;
			long midISBN = books.get(mid).getIsbnAsLong();

			//System.out.println(mid);
			//System.out.println("midISBN " + midISBN);
			//System.out.println("key " + key);
			//System.out.println();

			if (midISBN == key) {
				return books.get(mid);
			}
			else if (midISBN > key) {
				max = mid - 1;
			}
			else {
				min = mid + 1;
			}
		}

		return null;
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
