/* Brighton Drill
 * Title: TopLeftPanel
 * Date: 4/7/2026
 * Description: A class that represents the topLeft panel of the GUI
 */


package GUI;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TopLeftPanel {
	
	//----------------------------
	// Controls
	//----------------------------
	
	private Label label1; // "Enter Information" label
	private TextField itemID; // item ID TextField
	private TextField userID; // user ID TextField
	private TextField keyWord; // Key Word TextField
	private VBox container; // container
	
	
	//----------------------------
	// Constructor
	//----------------------------
	
	public TopLeftPanel() {
		label1 = new Label("Enter information");
		itemID = new TextField();
		
	}
	
	//----------------------------
	// Get Methods
	//----------------------------
	protected VBox getContainer() {
		return container;
	}
	
	protected String getItemIDInput() {
		return itemID.getText();
	}
	
}
