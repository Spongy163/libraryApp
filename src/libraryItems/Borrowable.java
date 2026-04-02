/* Brighton Drill
 * Title: Borrowable
 * Date: 3/1/2026
 * Description: An interface that provides functionality for borrowing a Library Item
 */

package libraryItems;

public interface Borrowable {
	//----------------------------
	// Abstract Methods
	//----------------------------
	
	/**
	 * Attempts a checkout of the object
	 * @param days allowed for borrow
	 * @return The result of the operation
	 */
	public abstract boolean checkOut(int days);
	
	/**
	 * Attempts to return the object
	 * @return The result of the operation
	 */
	public abstract boolean returnItem();
	
	/**
	 * Checks whether the object is overdue
	 * @return The overdue status
	 */
	public abstract boolean isOverdue();
}


