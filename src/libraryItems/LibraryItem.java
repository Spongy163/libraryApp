/* Brighton Drill
 * Title: LibraryItem
 * Date: 3/31/2026
 * Description: An abstract class for library data classes
 */

package libraryItems;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class LibraryItem {

	//----------------------------
	// Data fields
	//----------------------------
	final private String itemID; // Stores the item ID
	final private String title; // Stores the item title
	final private String publisher; // Stores the publisher name
	
	protected boolean isCheckedOut; // Indicates whether the item is currently checked out
	protected LocalDate dueDate; // Stores the item's due date as a LocalDate object
	
	
	//----------------------------
	// Constructor
	//----------------------------
	/**
	 * Abstract class constructor
	 * @param itemID Stores the item ID
	 * @param title Stores the item title
	 * @param publisher Stores the publisher name
	 */
	public LibraryItem(String itemID, String title, String publisher) {
		this.itemID = itemID;
		this.title = title;
		this.publisher = publisher;
		//isCheckedOut left false
		//dueDate left null
	}


	
	//----------------------------
	// Getter and Setter Methods
	//----------------------------
	
	/** @return the isCheckedOut
	 */
	public boolean isCheckedOut() {
		return isCheckedOut;
	}

	/** @param isCheckedOut the isCheckedOut to set
	 */
	public void setCheckedOut(boolean isCheckedOut) {
		this.isCheckedOut = isCheckedOut;
	}

	/** @return the dueDate
	 */
	public LocalDate getDueDate() {
		return dueDate;
	}

	/** @param dueDate the dueDate to set
	 */
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	/** @return the itemID
	 */
	public String getItemID() {
		return itemID;
	}

	/** @return the title
	 */
	public String getTitle() {
		return title;
	}

	/** @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}
	
	//----------------------------
	// Methods
	//----------------------------
	@Override
	public String toString() {
		return "ItemID: " + itemID + " Title: " + title + " Publisher: " + publisher + " Check-Out status: " + checkedOutAsString() + " Due date: " + dueDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")) ; 
	}

	/**
	 * @return checked out status in a more readable way
	 */
	private String checkedOutAsString() {
		String checkedOutStatus;
		if(isCheckedOut) {
			checkedOutStatus = "Checked-out";
		} else {
			checkedOutStatus = "Available";
		}
		return checkedOutStatus;
	}
	
	//----------------------------
	// Abstract Methods
	//----------------------------
	public abstract String getItemType(); // Returns a short label describing the item type ie. (Book) 
	
	
}
