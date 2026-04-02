/* Brighton Drill
 * Title: Library
 * Date: 2/24/26
 * Description: An object that simulates a library containing a database and ways to search for books
 */


package Project1;

import java.io.*;
import java.util.ArrayList;

import libraryItems.Book;

import java.time.LocalDate;

public class Library {
	
	/* INSTANCE DATA FIELDS
	 * -database:Database = Stores all registered books, users, and transaction records.
	 */
	
	private Database database;
	
	/**
	 * CONSTRUCTOR
	 * load from unsorted input files constructor
	 * 
	 * @param booksFilename
	 * @param studentsFilename
	 * @throws IOException
	 */
	public Library(String booksFilename, String studentsFilename) throws IOException {
		this.database = new Database();
		this.database.loadBooksFromFile(booksFilename);
		this.database.loadStudentUsersFromFile(studentsFilename); 
	}
	
	
	//METHODS
	
	/**
	 * Calls binary search method within Database
	 * 
	 * @param isbn
	 * @return Book or Null
	 */
	public Book searchBookByISBN(String isbn) {
		return database.findBookByISBN(isbn);
	}
	
	/**
	 * calls Database findBooksWithKeyword()
	 *
	 * @param keyword
	 * @return an ArrayList<Book> of all matching books.
	 */
	public ArrayList<Book> searchBookByTitle(String keyword) {
		return database.findBooksWithKeyword(keyword);
	}
	
	/**
	 * calls Database findBooksByTitle(title);
	 * 
	 * @param title
	 * @return
	 */
	public Book searchBookByFullTitle(String title) {
		return database.findBookByTitle(title);
	}

	
	/**
	 * checks out a book to the user
	 * 
	 * @param user
	 * @param book
	 * @return boolean : check out result
	 */
	public boolean checkoutBook(StudentUser user, Book book) {
		

		/* Checks if the book and user are null
		 * Checks if the book is checked out
		 * Checks if the book and user do not belong to the library
		 * returns false if any of these are true
		 */
		if(user == null||book == null||book.isCheckedOut()||database.findBookByISBN(book.getIsbn()) == null||database.findUserById(user.getUserID()) == null) {
			return false;
		}
		
		boolean result = user.addBook(book);
		if(result) {
			database.addCheckoutLog(book, user);
		}
		return result;
	}
	
	/**
	 * returns a book from a user
	 * 
	 * @param user
	 * @param book
	 * @return
	 */
	public boolean returnBook(StudentUser user, Book book) {
		

		/* Checks if the book and user are null
		 * Checks if the book is not checked out
		 * Checks if the book and user do not belong to the library
		 * returns false if any of these are true
		 */
		if(user == null||book == null||!book.isCheckedOut()||database.findBookByISBN(book.getIsbn()) == null||database.findUserById(user.getUserID()) == null) {
			return false;
		}
		
		boolean result = user.removeBook(book);
		if(result) {
			database.addReturnLog(book, user);
		}
		
		return result;
		
	}
	
	/**
	 * gets expected return date if the book is checked out
	 * 
	 * @param isbn
	 * @return
	 */
	public LocalDate getExpectedReturnDate(String isbn) {
		Book book = database.findBookByISBN(isbn);
		
		if(book != null && book.isCheckedOut()) {
			return book.getDueDate();
		}
		
		return null;
	}
	
	/**
	 * calls database findUserById method
	 * 
	 * @param user
	 * @return
	 */
	public StudentUser findUserByID(String user) {
		return database.findUserById(user);
	}
	
	/**
	 * Calls database for an array of overdue books
	 * prints out the array
	 */
	public void printOverdueBooks() {
		ArrayList<Book> overdue = database.findOverdueBooks();
		System.out.println("Over due books: ");
		
		if(overdue == null) {
			System.out.println();
			System.out.print("There are no over due books!");
			System.out.println();
			return;
		}
		
		if (overdue.isEmpty()) {
			System.out.println();
			System.out.print("There are no over due books!");
			System.out.println();
			return;
		}
		
		for (Book book : overdue) {
			System.out.println(book);
		}
		
		System.out.println("===========================================");
	}
	
	/**
	 * 
	 */
	public ArrayList<LogEntry> returnAnalytics() {
		return database.returnAnalytics();
	}
	
	/**
	 * Calls database to print a summary
	 */
	public void printDatabaseSummary() {
		database.printSummary();
	}
}
