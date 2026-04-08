package FileHandling;

import java.util.ArrayList;

import users.Faculty;
import users.Student;
import users.User;

public class UserLoader extends FileLoader<User, UserType> {
	//----------------------------
	// Data Fields
	//----------------------------
	final public int ITEM_LINES = 4;
	
	//----------------------------
	// Constructor
	//----------------------------
	public UserLoader(String filename) {
		super(filename);
	}
	
	//----------------------------
	// readData()
	//----------------------------
	
	/**
	 * Reads data from the file and returns an ArrayList of the type <User>
	 * @return an ArrayList<User> of data
	 */
	@Override
	public ArrayList<User> readData() {
		
		output = new ArrayList<User>(); // Creates a new return ArrayList for the class
		
		// Data reading and LibraryItem adding
		while (dataReader.hasNextLine()) {
			
			// Reading data from file
			String[] itemData = new String[ITEM_LINES];
			
			for(int i = 0; i < ITEM_LINES; i++) {
				if (!dataReader.hasNextLine()) {
					findItemError(i);
					break;
				}
				itemData[i] = dataReader.nextLine();
			}
			
			if(itemData[3] == null) {break;} // Checks the for loop operation success
			
			processData(itemData);
			
		} // End of while loop
		
		dataReader.close(); // closing scanner
		return output; // Returning Array
	}
	
	
	/**
	 * Takes an array of data, processes it and adds it to the output ArrayList
	 * Utilizes and connects checkType and addBook
	 * @param itemData
	 */
	private void processData(String[] itemData) {
		
		switch (checkType(itemData[0]))	{
			case STUDENT:
				addStudent(itemData);
				break;
			case FACULTY: 
				addFaculty(itemData);
				break;
			default:
				System.out.println("Error: Unable to determine libraryItem type " + itemData[0]);
				break;
		}
		
	}
	
	
	//----------------------------
	// Helper Methods
	//----------------------------
	
	/**
	 * Takes an index and prints an error message
	 * helper method for readData
	 * Helps determine what data is missing when the scanner does not have
	 * a next line, and a next line is expected
	 * @param dataIndex the index the scanner has no next line on
	 */
	@Override
	protected void findItemError(int errorIndex) {
		String[] errorMessages = new String[ITEM_LINES];
		
		try {
			errorMessages[0] = "Error: Missing userID";
			errorMessages[1] = "Error: Missing name";
			errorMessages[2] = "Error: Missing major";
			errorMessages[3] = "Error: Missing Standing";
		} catch (ArrayIndexOutOfBoundsException aiobe) {
			System.out.println("Error: ITEM_LINES not formatted correctly for findItemError | " + aiobe);
		}
		
		System.out.println(errorMessages[errorIndex]);
	}
	
	/**
	 * Checks the first Char of the UserID and determines the type of User
	 * @return the User type as an enum<UserType>
	 */
	@Override
	protected UserType checkType(String ID) {
		if(ID.startsWith("S")) {
			return UserType.STUDENT;
		}
		if(ID.startsWith("F")) {
			return UserType.FACULTY;
		}
		return UserType.ERROR;
	}
	
	
	//----------------------------
	// Add Methods
	//----------------------------
	/**
	 * takes item data read from the file to create and add a book to items ArrayList
	 * @param itemData
	 */
	private void addStudent(String[] itemData) {
		// Book initialization and addition
		Student student;
		try {
			student = new Student(itemData[0], itemData[1], itemData[2], itemData[3]);
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("Error: Index out of bounds exception while trying to add Student from itemData");
			return;
		}
		output.add(student);
	}
	
	/**
	 * 
	 * @param itemData
	 */
	private void addFaculty(String[] itemData) {
		Faculty faculty;
		try {
			faculty = new Faculty(itemData[0], itemData[1], itemData[2], itemData[3]);
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("Error: Index out of bounds exception while trying to add Student from itemData");
			return;
		}
		output.add(faculty);
	}
	
}
