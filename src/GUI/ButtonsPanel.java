/* Brighton Drill
 * Title: TopRightCorner
 * Date: 4/7/2026
 * Description: top right coner of the app ui
 */


package GUI;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ButtonsPanel {

	//----------------------------
	// Controls
	//----------------------------
	
	//Title label
	private Label title;
	
	//Buttons
	private Button itemSearch;
	private Button keywordSearch;
	private Button checkout;
	private Button returnItem;
	private Button borrowedItems;
	private Button overdue;
	private Button summary;
	private Button menu;
	
	//Containers
	
	private GridPane buttonContainer;
	
	private VBox container;
	
	
	//----------------------------
	// Constructor
	//----------------------------
	public ButtonsPanel(GUInterface userInterface) {
		title = new Label("Library Services");
		
		instantiateButtons();
		
		tieEventsToButtons(userInterface);
		
		fillContainers();
	}
	
	//----------------------------
	// Constructor helper methods
	//----------------------------
	
	private void instantiateButtons() {
		itemSearch = new Button("Item Search");
		keywordSearch = new Button("Keyword Search");
		checkout = new Button("Checkout");
		returnItem = new Button("Return Item");
		borrowedItems = new Button("Borrowed Items");
		overdue = new Button("Overdue");
		summary = new Button("Summary");
		menu = new Button("Menu");
		
		//Preferred Width and height of buttons
		int prefferedWidth = 300;
		int prefferedHeight = 40;
		
		itemSearch.setPrefSize(prefferedWidth, prefferedHeight);
		keywordSearch.setPrefSize(prefferedWidth, prefferedHeight);
		checkout.setPrefSize(prefferedWidth, prefferedHeight);
		returnItem.setPrefSize(prefferedWidth, prefferedHeight);
		borrowedItems.setPrefSize(prefferedWidth, prefferedHeight);
		overdue.setPrefSize(prefferedWidth, prefferedHeight);
		summary.setPrefSize(prefferedWidth, prefferedHeight);
		menu.setPrefSize(prefferedWidth, prefferedHeight);
		
	}
	
	private void tieEventsToButtons(GUInterface userInterface) {
		itemSearch.setOnAction(e -> userInterface.itemSearchAction());
		keywordSearch.setOnAction(e -> userInterface.keywordSearchAction());
		checkout.setOnAction(e -> userInterface.checkoutAction());
		returnItem.setOnAction(e -> userInterface.returnItemAction());
		borrowedItems.setOnAction(e -> userInterface.borrowedItemsAction());
		overdue.setOnAction(e -> userInterface.overdueAction());
		summary.setOnAction(e -> userInterface.summaryAction());
		menu.setOnAction(e -> userInterface.exitAction());
	}
	
	private void fillContainers() {
		buttonContainer = new GridPane();
	
		buttonContainer.add(itemSearch, 0, 0);
		buttonContainer.add(keywordSearch, 1, 0);
		buttonContainer.add(checkout, 0, 1);
		buttonContainer.add(returnItem, 1, 1);
		buttonContainer.add(borrowedItems, 0, 2);
		buttonContainer.add(overdue, 1, 2);
		buttonContainer.add(summary, 0, 3);
		buttonContainer.add(menu, 1, 3);
		
		container = new VBox(20, title, buttonContainer);
	}
	
	//----------------------------
	// Get methods
	//----------------------------
	public VBox getContainer() {
		return container;
	}
	

}
