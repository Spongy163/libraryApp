/* Brighton Drill
 * Title: Database
 * Date: 2/19/2026
 * Description: A class that simulates a library database. Keeps track of books, users,
 * and data log entries for data analytics. 
 */

package Project1;

import java.util.ArrayList;

public class Database {

	/* INSTANCE DATA FIELDS
	 * -books:ArrayList<Book> = Stores all registered books in the library system. 
	 * -users:ArrayList<StudentUser> = Stores all registered users in the library system.
	 * -transactionLog:ArrayList<LogEntry> = Stores all library transaction records
	 */
	
	private ArrayList<Book> books;
	private ArrayList<StudentUser> users;
	private ArrayList<LogEntry> transactionLog;
	
	/* CONSTRUCTOR
	 * + Database() = 
	 */
	
	public Database(ArrayList<Book> books, ArrayList<StudentUser> users) {
		this.books = books;
		this.users = users;
		this.transactionLog = new ArrayList<>();
	}
	
	/* ADD METHODS
	 * +addBook(book:Book) = Add the provided book to the associated data structure (ArrayList).
	 * +addUser(user:StudentUser) = Register the provided user to the associated data structure.
	 * +addCheckoutLog(book:Book, user:StudentUser) = Add a checkout log (LogEntry) to the transaction log list.
	 * +addReturnLog(book:Book, user:StudentUser) = Add a return log (LogEntry) to the transaction log list.
	 */
	
	public void addBook(Book book) {
		books.add(book);
	}
	
	public void addUser(StudentUser user) {
		users.add(user);
	}
	
	public void addCheckOutLog(Book book, StudentUser user) {
		//TODO add functionality once LogEntry is complete
		//LogEntry data = new LogEntry(book.getTitle(), user.getName(), "CHECKOUT");
		//transactionLog.add(data);
	}
	
	public void addReturnLog(Book book, StudentUser user) {
		//TODO add functionality once LogEntry is complete
		//LogEntry data = new LogEntry(Book.getTitle(), user.getName(), "RETURN");
	}
	
	/* METHODS
	 * +loadBooksFromFile(filename: String) = Reads book information from the specified input file and stores the created Book objects to the associated data structure.
	 * +loadStudentUserFromFile(filename: String) = Reads user information from the specified input file and store the instantiated StudentUser objects to the associated data structure.
	 * +findBookByISBN(isbn:String):Book = Searches for and returns the Book object with the matching ISBN. Returns null if not found.
	 * +findUserById(userId:String):StudentUser = Searches for and returns the StudentUser object with the matching user ID. Returns null if not found.
	 * +printSummary() = Prints a summary of the database—including the total number of books, users, and transaction records—to standard output
	 */
	
	
}
