/* Brighton Drill
 * Title: LibraryItemLoader
 * Date: 4/7/2026
 * Description: Loads library items and returns an ArrayList<LibraryItem>
 */



package FileHandling;

import java.util.ArrayList;

import libraryItems.Book;
import libraryItems.LibraryItem;
import libraryItems.Periodical;
import libraryItems.Recordings;

public class LibraryItemLoader extends FileLoader<LibraryItem, ItemType> {
	//----------------------------
	// Data Fields
	//----------------------------
	final public int ITEM_LINES = 5;
	
	//----------------------------
	// Constructor
	//----------------------------
	public LibraryItemLoader(String filename) {
		super(filename);
	}

	//----------------------------
	// readData()
	//----------------------------
	
	/**
	 * Reads data from the file and returns an ArrayList of the type <LibraryItem>
	 * @return an ArrayList<LibraryItem> of data
	 */
	@Override
	public ArrayList<LibraryItem> readData() {
		
		output = new ArrayList<LibraryItem>(); // Creates a new return ArrayList for the class
		
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
			
			if(itemData[4] == null) {break;} // Checks the for loop operation success
			
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
			case BOOK:
				addBook(itemData);
				break;
			case PERIODICAL: 
				addPeriodical(itemData);
				break;
			case RECORDINGS:
				addRecordings(itemData);
				break;
			default:
				System.out.println("Error: Unable to determine libraryItem type " + itemData[0]);
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
			errorMessages[0] = "Error: Missing itemID";
			errorMessages[1] = "Error: Missing publisher";
			errorMessages[2] = "Error: Missing title";
			errorMessages[3] = "Error: Missing subclass unique data";
			errorMessages[4] = "Error: Missing subclass unique data";
		} catch (ArrayIndexOutOfBoundsException aiobe) {
			System.out.println("Error: ITEM_LINES not formatted correctly for findItemError | " + aiobe);
		}
		
		System.out.println(errorMessages[errorIndex]);
	}
	
	/**
	 * Checks the first four letters of the itemID and determines the type of LibraryItem
	 * @return the Item type as an enum ItemType
	 */
	@Override
	protected ItemType checkType(String ID) {
		if(ID.startsWith("ISBN")) {
			return ItemType.BOOK;
		}
		if(ID.startsWith("ISSN")) {
			return ItemType.PERIODICAL;
		}
		if(ID.startsWith("IRSC")) {
			return ItemType.RECORDINGS;
		}
		return ItemType.ERROR;
	}
	
	//----------------------------
	// Add Methods
	//----------------------------
	/**
	 * takes item data read from the file to create and add a book to items ArrayList
	 * @param itemData
	 */
	private void addBook(String[] itemData) {
		// Book initialization and addition
		Book book;
		try {
			book = new Book(itemData[0], itemData[1], itemData[2], itemData[3], itemData [4]);
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("Error: Index out of bounds exception while trying to add Book from itemData");
			return;
		}
		output.add(book);
	}
	
	/**
	 * takes item data read from the file to create and add a recordings to items ArrayList
	 * @param itemData
	 */
	private void addRecordings(String[] itemData) {
		// Book initialization and addition
		Recordings recordings;
		try {
			recordings = new Recordings(itemData[0], itemData[1], itemData[2], itemData[3], itemData [4]);
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("Error: Index out of bounds exception while trying to add Book from itemData");
			return;
		}
		output.add(recordings);
	}
	
	/**
	 * takes item data read from the file to create and add a periodical to items ArrayList
	 * @param itemData
	 */
	private void addPeriodical(String[] itemData) {
		
		// Volume to int parsing
		int volumeInt;
		try {
			volumeInt = Integer.parseInt(itemData[4]);
		} catch (NumberFormatException nfe) {
			System.out.println("Error: periodical volume was not able to be parsed");
			return;
		}
		
		Periodical periodical = new Periodical(itemData[0], itemData[1], itemData[2], itemData[3], volumeInt);
		output.add(periodical);
	}
	
}
