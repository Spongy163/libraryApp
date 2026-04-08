/* Brighton Drill
 * Title: User
 * Date: 4/1/2026
 * Description: An abstract class for users interacting with the library system
 */

package users;

import java.util.ArrayList;

import FileHandling.UserType;
import libraryItems.Borrowable;
import libraryItems.LibraryItem;

public abstract class User {
	//----------------------------
	// Data Fields
	//----------------------------
	private String userID; // Stores the user ID
	private String name; // Stores the user's full name
	private String major; // Stores the user's major or department
	
	protected int maxLimit; // Stores the maximum number of items the user type can borrow
	protected int loanPeriod; // Stores the default loan period for the user type in days
	
	private ArrayList<LibraryItem> checkedOutItems = new ArrayList<>(); // Stores the items currently checked out by the user
	
	
	//----------------------------
	// Constructor
	//----------------------------
	
	/**
	 * Initializes the User abstract class
	 * @param userID
	 * @param name
	 * @param major
	 */
	public User (String userID, String name, String major) {
		this.userID = userID;
		this.name = name;
		this.major = major;
	}


	//----------------------------
	// Getter/Setter
	//----------------------------
	
	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}
	
	/**
	 * @return the user name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the user's major
	 */
	public String getMajor() {
		return major;
	}
	
	//----------------------------
	// Abstract methods
	//----------------------------
	
	/**
	 * @return The checkout limit for this user type
	 */
	public abstract int getCheckoutLimit();
	
	/**
	 * @return The loan period for this user type
	 */
	public abstract int getLoanPeriod();
	
	/**
	 * @return The type of User
	 */
	public abstract UserType getUserType();
	
	//----------------------------
	// Methods
	//----------------------------

	/**
	 * @return The number of checked out books the User has
	 */
	public int getCheckedOutCount() {
		return checkedOutItems.size();
	}
	
	
	//----------------------------
	// addItem method + helpers
	//----------------------------
	
	/**
	 * Attempts to check out an item and add it to the User's checkedOutItems ArrayList
	 * @param item:LibraryItem
	 * @return The result of the operation
	 */
	public boolean addItem(LibraryItem item) {
		 // Checks if the limit is reached 
		if (limitReached()) {
			return false;
		}
		 // Checks if the item is borrowable
		if (!(item instanceof Borrowable)) {
			return false;
		}
		// Attempts to check out the item and adds it to checkedOutItems
		return checkOutItem(item); 
	}

	/**
	 * Helper method for addItem()
	 * @return is the limit reached
	 */
	private boolean limitReached() {
		// Checks if the number of items the User has checked-out is greater or equal to the limit
		if(getCheckedOutCount() >= getCheckoutLimit()) {
			return true;
		}
		return false;
	}

	/**
	 * Helper method for addItem()
	 * @param item
	 * @return The result of the operation
	 */
	private boolean checkOutItem(LibraryItem item) {
		boolean checkOutResult = ((Borrowable) item).checkOut(getLoanPeriod());
		
		if (checkOutResult) {
			checkedOutItems.add(item);
			return true;
		} 
		return false;
	}
	
	
	//----------------------------
	// Methods
	//----------------------------
	
	/**
	 * Removes an item from the User's checked out items
	 * @param item to remove
	 * @return The result of the operation
	 */
	public boolean removeItem(LibraryItem item) {
		
		if(!checkedOutItems.contains(item)) {
			return false;
		}
		
		if(!(item instanceof Borrowable)) {
			return false;
		}
		
		boolean returnResult = ((Borrowable)item).returnItem();
		if(returnResult) {
			checkedOutItems.remove(item);
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the user has the item
	 * @param itemID:String
	 * @return Does the user have the item
	 */
	public boolean hasItem (String itemID) {
		for (LibraryItem libraryItem : checkedOutItems) {
			if (itemID.equals(libraryItem.getItemID())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns items that are checked out
	 * copies the arraylist
	 * shallow copies the library items
	 */
	public ArrayList<LibraryItem> getCheckedOutItems() {
		ArrayList<LibraryItem> checkedOutItems = new ArrayList<>();
		
		for(LibraryItem libraryItem : this.checkedOutItems) {
			checkedOutItems.add(libraryItem);
		}
		
		return checkedOutItems;
	}
	
	public String outputString() {
		return getUserID() + " " + getName() + " " + getUserType();
	}
	
	//----------------------------
	// Override Methods
	//----------------------------
	
	/**
	 * @return Information of User as a String
	 */
	@Override
	public String toString() {
		return "UserID: " + userID + ", Name: " + name + ", Major: " + major + ", Check-out limit: " + getCheckoutLimit() + ", Loan period: " + getLoanPeriod();
	}
	
}
