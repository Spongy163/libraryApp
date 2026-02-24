/* Brighton Drill
 * Title: Interface
 * Date: 2/24/2026
 * Description: A user interface class to interact with the library.
 */


package Project1;

import java.io.*;
import java.util.Scanner;

public class Interface {
	/* INSTANCE DATA FIELDS
	 * -library:Library = a library object the user can interact with
	 */
	
	private Library library;
	
	/**
	 * CONSTRUCTOR
	 * sets the library
	 * 
	 * @param library
	 */
	public Interface(Library library) {
		this.library = library;
	}
	
	/**
	 * prompts the user to enter a valid input and returns that value
	 * prompts between 1 and an accepted max (inclusive)
	 * returns -1 if invalid max
	 * 
	 * @param max
	 * @return input
	 */
	private int promptValidInput(int max) {
		int input = -1;
		Scanner sc = new Scanner(System.in);
		
		if (max < 0) {
			return input;
		}
		
		do {
			System.out.println("Enter choice: ");
			input = sc.nextInt();
			if (input <= 0 || input > max) {
				System.out.println("Invalid input, Must be a number between 1 and " + max);
				System.out.println("Try Again");
			}
		} while (input <= 0 || input > max);
		
		
		return input;
	}
	
	
	/**
	 * starts the user interface loop
	 * saves the data at the end
	 * 
	 * @throws IOException
	 */
	public void start() throws IOException {
		boolean continueLoop = true;
		do {
			System.out.println("======== LIBRARY APP ========");
			System.out.println("[1] menu");
			System.out.println("[2] credits");
			System.out.println("[3] save and exit");
			System.out.println("=============================");
			
			switch (promptValidInput(3)) {
				case 1:
					//printMenu()
				case 2:
					//showCredits()
				case 3:
					continueLoop = false;
			}
		} while (continueLoop);
		
		System.out.println("Saving Database");
		library.saveDatabase();
		System.out.println("Database saved");
		System.out.println("Exiting...");
	}
	
	
	
}
