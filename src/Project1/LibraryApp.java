/* Brighton Drill
 * Title: Library App
 * Date: 2/24/2026
 * Description: Starts the Library application 
 */

package Project1;

import java.io.*;

public class LibraryApp {
	
	
	
	public static void main(String[] args) throws IOException {
		String booksFilename = "Bookinfo.dat";
		String usersFilename = "StudentInfo.dat";
		
		Library library = new Library(booksFilename, usersFilename);
		Interface libraryInterface = new Interface(library);
		
		libraryInterface.testCheckoutBook();
		libraryInterface.testAddBorrowedBooks();
		libraryInterface.start();
		
		
	}
}
