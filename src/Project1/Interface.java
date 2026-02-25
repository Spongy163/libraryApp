/* Brighton Drill
 * Title: Interface
 * Date: 2/24/2026
 * Description: A user interface class to interact with the library.
 */


package Project1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Interface {
	
	/* INSTANCE DATA FIELDS
	 * -library:Library = a library object the user can interact with
	 * -sc:Scanner = a scanner that can be used by the whole Interface class
	 */
	
	private Library library;
	private Scanner sc;
	
	/**
	 * CONSTRUCTOR
	 * Accepts and sets Library
	 * Initializes scanner
	 * 
	 * @param library
	 */
	public Interface(Library library) {
		this.library = library;
		sc = new Scanner(System.in);
	}
	
	//METHODS
	
	/**
	 * Pauses loop to allow user to take information in before moving on 
	 */
	private void pause() {
		System.out.println();
		System.out.println("Press Enter to continue...");
		sc.nextLine();
	}
	
	/**
	 * prompts the user to enter a valid input and returns that value, 
	 * prompts between 1 and an accepted max (inclusive), 
	 * returns -1 if invalid max
	 * 
	 * @param max
	 * @return input
	 */
	private int promptValidInput(int max) {
		int input = -1;
		
		if (max < 0) {
			return input;
		}
		
		do {
			System.out.print("Enter choice: ");
			input = sc.nextInt();
			sc.nextLine();
			if (input <= 0 || input > max) {
				System.out.println("Invalid input, Must be a number between 1 and " + max);
				System.out.println("Try Again");
			}
		} while (input <= 0 || input > max);
		
		
		return input;
	}
	
	
	/**
	 * Starts the user interface loop
	 * 
	 * @throws IOException
	 */
	public void start() throws IOException {
		boolean rantests = false;
		boolean continueLoop = true;
		do {
			System.out.println("======== LIBRARY APP ========");
			System.out.println("[1] menu");
			System.out.println("[2] credits");
			System.out.println("[3] Run tests");
			System.out.println("[4] Data Analytics");
			System.out.println("[5] exit");
			System.out.println("=============================");
			
			switch (promptValidInput(5)) {
				case 1:
					printMenu();
					break;
				case 2:
					showCredits();
					break;
				case 3:
					if(rantests) {
						System.out.println("tests have already run");
						pause();
						break;
					}
					rantests = true;
					testAddBorrowedBooks();
					testCheckoutBook();
					pause();
					break;
				case 4:
					System.out.print(library.returnAnalytics());
					pause();
					break;
				case 5:
					continueLoop = false;
					break;
			}
		} while (continueLoop);
		
		System.out.println("Exiting...");
		System.out.println("Have a good night!");
		System.out.println("[Program terminated]");
	}
	
	/**
	* Displays program credits
	*/
	public void showCredits() {

		System.out.println("======== CREDITS ========");
		System.out.println("Library Book Checkout System");
		System.out.println();
		System.out.println("Created by: Brighton Drill");
		System.out.println("Inspired by: Dr. Kim");
		System.out.println();
		System.out.println("CS16100 - Introduction to Computer Science II");
		System.out.println("=========================");
			
		pause();
	}
	
	
	/**
	 * TEST METHOD
	 * Automatically checks out several books to a user so the
	 * displayUserBorrowedBooks() feature can be tested quickly.
	 * Can test without manually inputing data
	 */
	public void testAddBorrowedBooks() {

		System.out.println("======== TEST: Adding Borrowed Books ========");

		String userId = "ID-48206"; //known valid id
		StudentUser user = library.findUserByID(userId);

		if (user == null) {
			System.out.println("Test failed: user not found.");
			return;
		}

		String[] testIsbns = {"ISBN-9781942788331","ISBN-9780136042594","ISBN-9781491924464"}; //known valid isbns
		
		for (String isbn : testIsbns) {

			Book book = library.searchBookByISBN(isbn);

			if (book == null) {
				System.out.println(isbn + " Book not found");
				continue;
			}

			boolean result = library.checkoutBook(user, book);

			if (result)
				System.out.println(isbn + " Checkout successful");
			else
				System.out.println(isbn + " Checkout failed (already checked out?)");
		}

		System.out.println("=============================================");
	}

		/**
		 * Helper method that performs a single checkout test for testCheckoutBook()
		 */
		private void runCheckoutTest(String userId, String isbn) {

			StudentUser user = library.findUserByID(userId);
			Book book = library.searchBookByISBN(isbn);

			boolean result = library.checkoutBook(user, book);

			System.out.print("User=" + userId + "  ISBN=" + isbn + " -> ");

			System.out.println(result);
		}
		
		/**
		 * Runs a lot of tests to make sure the library functions correctly. 
		 * Displays results (in helper method) next to expected results
		 */
		public void testCheckoutBook() {

			System.out.println("======== TEST CHECKOUT ========");

			runCheckoutTest("ID-74932", "ISBN-9780134685991");
			System.out.println("Expected result: true");
			System.out.println();

			runCheckoutTest("ID-74932", "ISBN-9780321349606");
			System.out.println("Expected result: true");
			System.out.println();

			runCheckoutTest("ID-71038", "ISBN-9780321637734");
			System.out.println("Expected result: true");
			System.out.println();

			runCheckoutTest("ID-71038", "ISBN-9781107077232");
			System.out.println("Expected result: true");
			System.out.println();

			runCheckoutTest("ID-AAA73", "ISBN-9781491924464");
			System.out.println("Expected result: false");
			System.out.println();

			runCheckoutTest("ID-74932", "ISBN-9781491924AAA");
			System.out.println("Expected result: false");
			System.out.println();

			runCheckoutTest("ID-74932", "ISBN-9780134685991");
			System.out.println("Expected result: false");
			System.out.println();

			runCheckoutTest("ID-74932", "ISBN-9781107077232");
			System.out.println("Expected result: false");
			System.out.println();

			System.out.println("================================");
		}

		
	
	/**
	 * Prints a Library menu show casing different features of the library app.
	 */
	public void printMenu() { 
		System.out.println("======== LIBRARY Menu ========");
		System.out.println("[1] Search book");
		System.out.println("[2] Checkout book");
		System.out.println("[3] Return book");
		System.out.println("[4] Display user borrowed books");
		System.out.println("[5] Check overdue books");
		System.out.println("[6] Print database summary");
		System.out.println("[7] Return");
		System.out.println("=============================");
		
		switch (promptValidInput(7)) {
		case 1:
			searchBook();
			break;
		case 2:
			checkOut();
			break;
		case 3:
			returnBook();
			break;
		case 4:
			displayUserBorrowedBooks();
			break;
		case 5:
			checkOverdueBooks();
			break;
		case 6:
			library.printDatabaseSummary();
			pause();
			break;
		case 7:
			return;
		}	
	}
	
	//SEARCH BOOK
	
	/**
	 * Asks the user which method they would like to search by
	 */
	public void searchBook() {

	    System.out.println("======== LIBRARY Search ========");
	    System.out.println("[1] Search book by ISBN");
	    System.out.println("[2] Search book by full title");
	    System.out.println("[3] Search books by keyword");

	    switch (promptValidInput(3)) {

	        case 1:
	            searchByISBN();
	            break;

	        case 2:
	            searchByTitle();
	            break;

	        case 3:
	            searchByKeyword();
	            break;
	    }
	}
	
	/**
	 * Allows the user to search for a book using isbn
	 */
	public void searchByISBN() {
	

		System.out.println("===[Search by ISBN selected]===");
		System.out.print("Enter book ISBN: ");

		String isbn = sc.nextLine();

		Book book = library.searchBookByISBN(isbn);

		if (book == null) {
			System.out.println("Book not found.");
			pause();
			return;
		} 
		System.out.println("Book found:");
		System.out.println(book);
		pause();
		   
	}
	
	/**
	 * Allows the user to search for a book using title
	 */
	public void searchByTitle() {
	

	    System.out.println("===[Search by Title selected]===");
	    System.out.print("Enter full book title: ");

	    String title = sc.nextLine();

	    Book book = library.searchBookByFullTitle(title);

	    if (book == null) {
	    	System.out.println("Book not found.");
			pause();
			return;
		} 
	    System.out.println("Book found:");
		System.out.println(book);
	    pause();
	}
	
	/**
	 * Allows the user to search for books using a keyword
	 */
	public void searchByKeyword() {
	
		
	    System.out.println("===[Search by Keyword selected]===");
	    System.out.print("Enter keyword: ");

	    String keyword = sc.nextLine();

	    ArrayList<Book> books = library.searchBookByTitle(keyword);

	    if (books == null || books.isEmpty()) {
	        System.out.println("No matching books found.");
	    } else {
	        System.out.println("Matching books:");
	        for (Book b : books) {
	            System.out.println(b);
	        }
	    }
	    pause();
	}
	
	//CHECKOUT
	
	/**
	 * Allows a user to check out a book by providing an ID and the ISBN of the book
	 */
	public void checkOut() {

	    System.out.println("===[Checkout Book selected]===");
	    
	    System.out.println("Format ID-1234567 (case insensitive)");
	    System.out.print("Enter Student ID: ");
	    String userId = sc.nextLine();

	    StudentUser user = library.findUserByID(userId);

	    if (user == null) {
	        System.out.println("Invalid Student ID.");
	        pause();
	        return;
	    }

	    System.out.println("Format ISBN-123456 (case insensitive)");
	    System.out.print("Enter Book ISBN: ");
	    String isbn = sc.nextLine();

	    Book book = library.searchBookByISBN(isbn);

	    if (book == null) {
	        System.out.println("Invalid ISBN.");
	        pause();
	        return;
	    }

	    boolean result = library.checkoutBook(user, book); 

	    if (result) {
	        System.out.println("Checkout successful!");
	    } else {
	        System.out.println("Checkout failed. Book may already be checked out.");
	    }
	    pause();
	}
	
	//RETURN
	
	/**
	 * Allows user to return a book by providing an ID and an ISBN
	 */
	public void returnBook() {

	    System.out.println("===[Return Book selected]===");

	    System.out.println("Format ID-1234567 (case insensitive)");
	    System.out.print("Enter Student ID: ");
	    String userId = sc.nextLine();

	    StudentUser user = library.findUserByID(userId);

	    if (user == null) {
	        System.out.println("Invalid Student ID.");
	        pause();
	        return;
	    }
	    
	    System.out.println("Format ISBN-123456 (case insensitive)");
	    System.out.print("Enter Book ISBN: ");
	    String isbn = sc.nextLine();

	    Book book = library.searchBookByISBN(isbn);

	    if (book == null) {
	        System.out.println("Invalid ISBN.");
	        pause();
	        return;
	    }

	    boolean result = library.returnBook(user, book);

	    if (result) {
	        System.out.println("Return successful!");
	    } else {
	        System.out.println("Return failed. User may not have this book.");
	    }
	    pause();
	}
	
	//BORROWED BOOKS
	
	//DISPLAY BORROWED BOOKS

	/**
	 * Prompts the user for a student ID and prints all books currently
	 * borrowed by that student to standard output.
	 */
	public void displayUserBorrowedBooks() {

		System.out.println("======== DISPLAY USER BORROWED BOOKS ========");

		System.out.println("Format ID-1234567 (case insensitive)");
		System.out.print("Enter Student ID: ");
		String userId = sc.nextLine();

		StudentUser user = library.findUserByID(userId);

		if (user == null) {
			System.out.println("Invalid Student ID.");
			pause();
			return;
		}

		ArrayList<Book> borrowed = user.getCheckedOutBooks();

		System.out.println("==============================================");
		System.out.println("User: " + user.getUserID() + "  Name: " + user.getName());
		System.out.println("==============================================");

		if (borrowed == null || borrowed.isEmpty()) {
			System.out.println("No books currently checked out.");
			System.out.println("==============================================");
			pause();
			return;
		}

		for (Book book : borrowed) {
			System.out.println(book); // uses Book.toString()
		}

		System.out.println("==============================================");
		System.out.println("Total checked out: " + borrowed.size());
		System.out.println("==============================================");
		pause();
	}

	//OVERDUE BOOKS

	/**
	 * Executes the "Show Overdue Books" operation
	 * Prints all overdue books to standard output
	 */
	public void checkOverdueBooks() {

		System.out.println("======== CHECK OVERDUE BOOKS ========");

		library.printOverdueBooks();

		System.out.println("=====================================");
		pause();
	}
	
	
	
	
	
}
