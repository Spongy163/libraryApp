/* Brighton Drill
 * Title: Log Entry
 * Date: 2/23/2026
 * Description: Structured data that contains information about book checkouts and returns
 */

package Project1;

import java.time.LocalDateTime;

public final class LogEntry {
	/* STATIC FIELDS
	 * entryCount:int = tracks the amount of log entries created. Useful for data analytics
	 */
	
	private static int entryCount = 0;
	
	/* IMMUTABLE INSTANCE FIELDS
	 * -action:String = Stores the type of transaction: "CHECKOUT" or "RETURN"
	 * -title:String = Stores the book title
	 * -userName:String = Stores the student's name
	 * -timeStamp:LocalDateTime = stores the current date and time of the transaction
	 */
	
	private final String action;
	private final String title;
	private final String userName;
	private final LocalDateTime timestamp;
	
	
	/**CONSTRUCTOR
	 * Initializes LogEntry object
	 * Accepts and sets action, title, and userName
	 * Initializes timeStamp as current time
	 * 
	 * @param action sets action to "CHECKOUT" or "RETURN"
	 * @param title sets title from book
	 * @param userName sets userName from StudentUser
	 */
	public LogEntry(String action, String title, String userName) {
		this.action = action;
		this.title = title;
		this.userName = userName;
		this.timestamp = LocalDateTime.now();
		entryCount++;
	}

	/**CONSTRUCTOR
	 * Initializes LogEntry object
	 * Accepts and sets action, title, userName, and timeStamp
	 * 
	 * @param action sets action to "CHECKOUT" or "RETURN"
	 * @param title sets title from book
	 * @param userName sets userName from StudentUser
	 * @param timeStamp the timeStamp to set
	 */
	public LogEntry(String action, String title, String userName, LocalDateTime timeStamp) {
		this.action = action;
		this.title = title;
		this.userName = userName;
		this.timestamp = timeStamp;
		entryCount++;
	}
	

	/* GETTER Methods
	 * There are no setter methods as the data should be immutable
	 */

	/**
	 * @return the entryCount
	 */
	public static int getEntryCount() {
		return entryCount;
	}


	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @return the timeStamp
	 */
	public LocalDateTime getTimeStamp() {
		return timestamp;
	}

	/**
	 * @return LogEntry as a String
	 */
	@Override
	public String toString() {
		return "\n [" + action + "||"+ title +"||" + userName + "]"; //\n for data analytic formating
	}
	
	
	
	
	
	
}
