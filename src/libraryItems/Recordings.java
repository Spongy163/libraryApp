package libraryItems;

import java.time.LocalDate;

import FileHandling.ItemType;

public class Recordings extends LibraryItem implements Borrowable{
	
	//----------------------------
	// Data Fields
	//----------------------------
	private String director; // the artist of the recording
	private String format; // the media format of the recordings
	
	
	//----------------------------
	// Constructor
	//----------------------------
	public Recordings(String itemID, String title, String publisher, String director, String format) {
		super(itemID, title, publisher);
		this.director = director;
		this.format = format;
	}
	
	//----------------------------
	// LibraryItem implementation
	//----------------------------
	
	/**
	 * @return item Type enum
	 */
	@Override
	public ItemType getItemType() {
		return ItemType.RECORDINGS;
	}

	/**
	 * @return a formatted string for display
	 */
	@Override
	public String outputString() {
		return super.getTitle() + " " + getItemType();
	}

	//----------------------------
	// Borrowable implementaiton
	//----------------------------
	
	/**
	 * Attempts to checkout the recordings
	 * @return The result of the operation
	 */
	@Override
	public boolean checkOut(int days) {
		if (!isCheckedOut) {
			isCheckedOut = true;
			dueDate = LocalDate.now().plusDays(days);
			return true;
		}
		return false;	
	}

	/**
	 * Attempts to return the recordings
	 * @return The result of the operation
	 */
	@Override
	public boolean returnItem() {
		if (isCheckedOut) {
			isCheckedOut = false;
			dueDate = null;
			return true;
		}
		return false;
	}

	/**
	 * Checks whether the recordings is overdue
	 * @return The overdue status
	 */
	@Override
	public boolean isOverdue() {
		if (dueDate == null) {return false;} // Validity check
		
		if(LocalDate.now().isAfter(dueDate)) {return true;} // Checks if it is after the due date
		
		return false; // base case
	}
	
	/**
	 * @return a formatted string of the object
	 */
	@Override
	public String toString() {
		return super.toString() + "\nDirector: " + director + "\nFormat: " + format + "\n"; 
	}

}
