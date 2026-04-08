/* Brighton Drill
 * Title: TopLeftPanel
 * Date: 4/7/2026
 * Description: A class that handles the input textFields
 */


package GUI;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InputsPanel {
	
	//----------------------------
	// Controls
	//----------------------------
	
	private Label enterInfoLabel; // "Enter Information" label
	
	//TextFields
	private TextField itemID; // item ID TextField
	private TextField userID; // user ID TextField
	private TextField keyWord; // Key Word TextField
	
	//Labels
	private Label itemIDLabel; // item ID Label
	private Label userIDLabel; // user ID Label
	private Label keyWordLabel; // Key Word Label
	
	//containers
	private HBox itemIDContainer; // item ID Container
	private HBox userIDContainer; // user ID Container
	private HBox keyWordContainer; // Key Word Container
	
	
	private VBox container; // container
	
	
	
	//----------------------------
	// Constructor
	//----------------------------
	
	public InputsPanel() {
		
	
		instantiateTextFields();
		
		instantiateLabels();
		
		fillContainers();
		
	}
	
	//----------------------------
	// Constructor methods
	//----------------------------
	/**
	 * initializes the TextFields and sets their width equal
	 */
	private void instantiateTextFields() {
		itemID = new TextField();
		userID = new TextField();
		keyWord = new TextField();
		
		//setting preferredWidth
		int preferredWidth = 300;
		
		itemID.setPrefWidth(preferredWidth);
		userID.setPrefWidth(preferredWidth);
		keyWord.setPrefWidth(preferredWidth);
	}
	
	/**
	 * initializes labels and sets width equal
	 */
	private void instantiateLabels() {
		
		//initializing and setting labels
		enterInfoLabel = new Label("Enter information");
		itemIDLabel = new Label("Item ID");
		userIDLabel = new Label("User ID");
		keyWordLabel = new Label("Keyword");
		
		//setting preferredWidth
		int preferredWidth = 50;
		
		itemIDLabel.setPrefWidth(preferredWidth);
		userIDLabel.setPrefWidth(preferredWidth);
		keyWordLabel.setPrefWidth(preferredWidth);
	}
	
	/**
	 * puts controls in their respective containers and adds the containers to one container
	 */
	private void fillContainers() {
		int padding = 20;
		
		itemIDContainer = new HBox(padding, itemIDLabel, itemID);
		userIDContainer = new HBox(padding, userIDLabel, userID);
		keyWordContainer = new HBox(padding, keyWordLabel, keyWord);
		
		container = new VBox(20, enterInfoLabel, itemIDContainer, userIDContainer, keyWordContainer);
	}
	
	
	//----------------------------
	// Get Methods
	//----------------------------
	/**
	 * @return the class container
	 */
	protected VBox getContainer() {
		return container;
	}
	
	/**
	 * @return information from itemID TextField
	 */
	protected String getItemIDInput() {
		return itemID.getText();
	}
	
	/**
	 * @return information from userID TextField
	 */
	protected String getUserIDInput() {
		return userID.getText();
	}
	
	/**
	 * @return information from keyword TextField
	 */
	protected String getKeyWordInput() {
		return keyWord.getText();
	}
	
}
