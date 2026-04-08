/* Brighton Drill
 * Title: Periodical
 * Date: 4/7/2026
 * Description: a class that represents a periodical
 */



package libraryItems;

import FileHandling.ItemType;

public class Periodical extends LibraryItem {

	//----------------------------
	// Data Fields
	//----------------------------
	private String editor;
	private int volume;
	
	
	//----------------------------
	// Constructor
	//----------------------------
	public Periodical(String itemID, String title, String publisher, String editor, int volume) {
		super(itemID, title, publisher);
		this.editor = editor;
		this.volume = volume;
	}

	
	/**
	 * @return Returns a short label describing the item type ie. (Book) 
	 */
	@Override
	public ItemType getItemType() {
		return ItemType.PERIODICAL;
	}
	
	/**
	 * returns the object as a string
	 */
	@Override
	public String toString() {
		return super.toString() + "\nEditor: " + editor + "\nVolume: " + volume + "\n"; 
	}
	
	/**
	 * @return a nicer formatted string for output
	 */
	@Override
	public String outputString() {
		return super.getTitle() + " " + getItemType();
	}

}
