/* Brighton Drill
 * Title: Library Services
 * Date: April 29th, 2026
 * Description: A GUI panel that displays radio button options for library services
 */

package GUI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class LibraryServices extends VBox {
	
	//----------------------------
	// Class Objects
	//----------------------------
	
	private ToggleGroup toggleGroup;
	
	//----------------------------
	// Constructor 
	//----------------------------
	
	public LibraryServices() {
		
		toggleGroup = new ToggleGroup();
		buildRadioButtons();
		configureVBox();
		
	}

	/**
	 * Instantiates and attaches radio buttons to toggleGroup
	 */
	private void buildRadioButtons() {
		//label 
		Label libraryServiceLabel = new Label("Library Services");
		
		//Instantiating radio buttons
		RadioButton itemSearch = new RadioButton("Item Search");
		RadioButton keywordSearch = new RadioButton("Keyword Search");
		RadioButton checkout = new RadioButton("Checkout");
		RadioButton returnItem = new RadioButton("Return Item");
		RadioButton borrowedItems = new RadioButton("Borrowed Items");
		
		//Attaching radio buttons to toggleGroup
		itemSearch.setToggleGroup(toggleGroup);
		keywordSearch.setToggleGroup(toggleGroup);
		checkout.setToggleGroup(toggleGroup);
		returnItem.setToggleGroup(toggleGroup);
		borrowedItems.setToggleGroup(toggleGroup);
		
		//Adding radio buttons to VBox
		getChildren().addAll(libraryServiceLabel, itemSearch, keywordSearch, checkout, returnItem, borrowedItems);
		
		//VBox Config
		setPadding(new Insets(10));
		setSpacing(5);
	}
	
	/**
	 * Configures the dimensions of the VBox
	 */
	private void configureVBox() {
		setPrefHeight(SizingSettings.VBoxHeight);
		setPrefWidth(SizingSettings.VBoxWidth);
	}
	
	/**
	 * @return the selected radio button
	 */
	public RadioButton getSelectedRadio() {
		return (RadioButton) toggleGroup.getSelectedToggle();
	}
	
	
	
	
}
