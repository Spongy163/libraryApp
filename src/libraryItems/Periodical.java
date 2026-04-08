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

	
	
	@Override
	public ItemType getItemType() {
		return ItemType.PERIODICAL;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nEditor: " + editor + "\nVolume: " + volume + "\n"; 
	}
	
	@Override
	public String outputString() {
		return super.getTitle() + " " + getItemType();
	}

}
