/* Brighton Drill
 * Title: Search Types
 * Date: April 29, 2026
 * Description: A GUI panel that allows the user to filter their library search
 */

package GUI;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SearchTypes extends VBox {

	//----------------------------
	// Class Objects
	//----------------------------
	
	private CheckBox book;
	private CheckBox periodical;
	private CheckBox recordings;
	
	//----------------------------
	// Constructor 
	//----------------------------
	
	public SearchTypes() {
		instantiateControls();
		configureVBox();
	}
	
	/**
	 * Builds GUI controls for this panel
	 */
	private void instantiateControls() {
		//Creating controls
		Label searchTypes = new Label("Search Types");
		book = new CheckBox("Book");
		periodical = new CheckBox("Periodical");
		recordings = new CheckBox("Recordings");
		
		//Adding controls to the VBox
		getChildren().addAll(searchTypes, book, periodical, recordings);
		
		//VBox Config
		setPadding(new Insets(10));
		setSpacing(5);
	}
	
	/**
	 * Configures the dimensions of the VBox
	 */
	private void configureVBox() {
		setSpacing(SizingSettings.VBoxSpacing);
		setPrefHeight(SizingSettings.VBoxHeight);
		setPrefWidth(SizingSettings.VBoxWidth);
	}
	
	//----------------------------
	// Getter Methods
	//----------------------------
	
	public boolean isBookSelected() {
		return book.isSelected();
	}
	
	public boolean isPeriodicalSelected() {
		return periodical.isSelected();
	}
	
	public boolean isRecordingsSelected() {
		return recordings.isSelected();
	}
}
