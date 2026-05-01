/* Brighton Drill
 * Title: Enter Information
 * Date: April 30th, 2026
 * Description: A GUI panel for entering information
 */


package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EnterInformation extends VBox {

	//----------------------------
	// Class Objects
	//----------------------------
	
	//Text box width
	private static final int textBoxWidth = 254;
	private static final int buttonWidth = 150;
	private static final int rowHeight = 30;
	
	//Text Fields
	TextField itemID;
	TextField userID;
	TextField keyword;
	
	//Buttons
	Button clear;
	Button execute;
	
	//----------------------------
	// Constructor 
	//----------------------------
	
	public EnterInformation () {
		//Labels
		Label enterInformationLabel = new Label("Enter Information");
		Label itemIDLabel = new Label("Item ID");
		Label userIDLabel = new Label("User ID");
		Label keywordLabel = new Label("Keyword");
		
		//label height config
		itemIDLabel.setPrefHeight(rowHeight);
		userIDLabel.setPrefHeight(rowHeight);
		keywordLabel.setPrefHeight(rowHeight);

		itemIDLabel.setAlignment(Pos.CENTER_LEFT);
		userIDLabel.setAlignment(Pos.CENTER_LEFT);
		keywordLabel.setAlignment(Pos.CENTER_LEFT);
		
		//Building class fields
		buildTextFields();
		buildButtons();
		
		//labels and textfields formatting
		int verticalGap = 10;
		VBox labelBox = new VBox(verticalGap, itemIDLabel, userIDLabel, keywordLabel);
		VBox textFieldBox = new VBox(verticalGap, itemID, userID, keyword);
		
		HBox labelAndFieldBox = new HBox(10, labelBox, textFieldBox);
		HBox buttonBox = new HBox(10, clear, execute);
		
		//Configuring VBox
		getChildren().addAll(enterInformationLabel, labelAndFieldBox, buttonBox);
		
		
		//VBox Config
		setPadding(new Insets(10));
		setSpacing(5);
	}
	
	//----------------------------
	// Helper Methods
	//----------------------------
	
	/**
	 * Instantiates the textfields
	 */
	private void buildTextFields() {
		//building text fields
		itemID = new TextField();
		userID = new TextField();
		keyword = new TextField();
		
		// Setting textField suggestion text
		itemID.setPromptText("e.g. ISBN-9780131103627");
		userID.setPromptText("e.g. S-1029384");
		keyword.setPromptText("e.g. Programming");
		
		//setting height
		itemID.setPrefHeight(rowHeight);
		userID.setPrefHeight(rowHeight);
		keyword.setPrefHeight(rowHeight);
		
		//setting width
		itemID.setPrefWidth(textBoxWidth);
		userID.setPrefWidth(textBoxWidth);
		keyword.setPrefWidth(textBoxWidth);
	}
	
	/**
	 * Instantiates the buttons
	 */
	private void buildButtons() {
		//building buttons
		clear = new Button("Clear");
		execute = new Button ("Execute");
		
		//setting width
		clear.setPrefWidth(buttonWidth);
		execute.setPrefWidth(buttonWidth);
	}

	//----------------------------
	// Getter Methods
	//----------------------------
	
	public TextField getItemID() {
		return itemID;
	}

	public TextField getUserID() {
		return userID;
	}

	public TextField getKeyword() {
		return keyword;
	}

	public Button getClear() {
		return clear;
	}

	public Button getExecute() {
		return execute;
	}
	
}
