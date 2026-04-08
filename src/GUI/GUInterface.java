/* Brighton Drill
 * Title: GUInterface
 * Date: 4/7/2026
 * Description: 
 */




package GUI;

import java.util.ArrayList;

import Project1.Library;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import libraryItems.LibraryItem;
import users.User;

public class GUInterface {

	//----------------------------
	// Class Fields
	//----------------------------
	
	//Library
	private Library library;
	
	
	//Stage
	private Stage primaryStage;
	
	//UI panels
	private InputsPanel inputs;
	private ButtonsPanel buttons;
	private SystemReportPanel systemReport;
	private ServiceResults serviceResults;
	
	
	//Main App container
	private GridPane container;

	//Scenes
	private Scene appScene;
	private Scene menuScene;
	private Scene creditsScene;
	
	//----------------------------
	// Constructor
	//----------------------------
	
	public GUInterface(Library library, Stage primaryStage) {
		//Setting library and stage
		this.library = library;
		this.primaryStage = primaryStage;
		
		//Panel initialization
		instantiatePanels();
		
		buildAppContainer();
		
		//Setting Scenes
		Menu menu = new Menu(this);
		
		menuScene = menu.getScene();
		appScene = new Scene(container);
		makeCreditsScene();
		
		
		
		
	}


	//----------------------------
	// Constructor helper methods
	//----------------------------
	private void makeCreditsScene() {
		int titleFontSize = 70;
		int nameFontSize = 50;
		
		
		Label development = new Label("Development");
		development.setFont(new Font(titleFontSize));
		
		Label brightonDrill = new Label("Brighton Drill");
		brightonDrill.setFont(new Font(nameFontSize));
		
		Label design = new Label("Design");
		design.setFont(new Font(titleFontSize));
		
		Label brightonDrill2 = new Label("Brighton Drill");
		brightonDrill2.setFont(new Font(nameFontSize));
		
		Label drKim = new Label("Dr. Beomjin Kim");
		drKim.setFont(new Font(nameFontSize));
		
		Label johnAdemola = new Label("John Ademola");
		johnAdemola.setFont(new Font(nameFontSize));
		
		Button returnButton = new Button("Return");
		returnButton.setPrefSize(400, 100);
		returnButton.setFont(new Font(nameFontSize));
		returnButton.setOnAction(e -> primaryStage.setScene(menuScene));
		
		VBox creditContainer = new VBox(10, development, brightonDrill, design, drKim, johnAdemola, brightonDrill2, returnButton);
		creditContainer.setAlignment(Pos.CENTER);
		creditContainer.setPadding(new Insets(50));
		
		creditsScene = new Scene(creditContainer, 1300, 824);
	}
	
	
	
	private void instantiatePanels() {
		//Menu
		inputs = new InputsPanel();
		buttons = new ButtonsPanel(this);
		systemReport = new SystemReportPanel(this);
		serviceResults = new ServiceResults();
		
	}
	
	private void buildAppContainer() {
		container = new GridPane();
		
		container.add(inputs.getContainer(), 0, 0);
		container.add(buttons.getContainer(), 1, 0);
		container.add(systemReport.getContainer(), 0, 1);
		container.add(serviceResults.getContainer(), 1, 1);
		
		container.setPadding(new Insets(40));
		container.setHgap(20);
		container.setVgap(20);
		
		
	}
	
	//----------------------------
	// Menu Actions
	//----------------------------
	public void startApp() {
		primaryStage.setScene(appScene);
	}
	
	public void creditsScene() {
		primaryStage.setScene(creditsScene);
	}

	
	public Scene getMenuScene() {
	    return menuScene;
	}
	
	//----------------------------
	// Button Actions
	//----------------------------
	public void itemSearchAction() {
		String userInput = inputs.getItemIDInput();
		LibraryItem foundItem = library.findItemByItemID(userInput);

		if (foundItem == null) {
			if(userInput.isEmpty()) {
				serviceResults.println("Error: Please enter ItemID");
				return;
			}
			serviceResults.println("Error: Item not found by provided ID");
			return;
		} else {
			serviceResults.println(foundItem.toString());
		}
	}
	
	public void keywordSearchAction() {
		String userInput = inputs.getKeyWordInput();
		ArrayList<LibraryItem> foundList = library.searchItemsByTitle(userInput);
		
		if(userInput.isEmpty()) {
			serviceResults.println("Error: Please enter Keyword.");
			return;
		}
		
		try {
			for (LibraryItem libraryItem : foundList) {
				serviceResults.println(libraryItem.outputString());
			}
		} catch (Exception e) {
			serviceResults.println("Error: No results for that Keyword");
		}
		
		serviceResults.println("");
		
	}
	
	public void checkoutAction() {
		String libraryItemInput = inputs.getItemIDInput();
		String userIDInput = inputs.getUserIDInput();
		
		if(libraryItemInput.isEmpty() || userIDInput.isEmpty()) {
			serviceResults.println("Error: Please enter itemID and userID.");
			return;
		}
		
		LibraryItem item = library.findItemByItemID(libraryItemInput);
		User user = library.findUserByID(userIDInput);
		
		if(item == null && user == null) {
			serviceResults.println("Error: invalid Item ID and User ID.");
			return;
		}
		
		if (item == null) {
			serviceResults.println("Error: invalid Item ID.");
			return;
		}
		
		if (user == null) {
			serviceResults.println("Error: invalid User ID.");
			return;
		}
		
		if(item.isCheckedOut()) {
			serviceResults.println("Error: Item is already checked out.");
			return;
		}
		
		if(user.getCheckedOutItems().size() >= user.getCheckoutLimit()) {
			serviceResults.println("Error: User has reached their maximum checkout limit");
			return;
		}
		
		boolean result = library.checkoutItem(user, item);
		
		if (result) {
			serviceResults.println("Checkout was successful");
		} else {
			serviceResults.println("Checkout was not successful");
		}
	}
	
	public void returnItemAction() {
		String libraryItemInput = inputs.getItemIDInput();
		String userIDInput = inputs.getUserIDInput();
		
		if(libraryItemInput.isEmpty() || userIDInput.isEmpty()) {
			serviceResults.println("Error: Please enter itemID and userID.");
			return;
		}
		
		LibraryItem item = library.findItemByItemID(libraryItemInput);
		User user = library.findUserByID(userIDInput);
		
		if(item == null && user == null) {
			serviceResults.println("Error: invalid Item ID and User ID.");
			return;
		}
		
		if (item == null) {
			serviceResults.println("Error: invalid Item ID.");
			return;
		}
		
		if (user == null) {
			serviceResults.println("Error: invalid User ID.");
			return;
		}
		
		if(!user.hasItem(libraryItemInput)) {
			serviceResults.println("Error: User does not have this item checked out");
			return;
		}
		
		boolean result = library.returnItem(user, item);
		
		if (result) {
			serviceResults.println("Return successful!");
		} else {
			serviceResults.println("Return was not successful");
		}
	}
	
	public void borrowedItemsAction() {
		User user = library.findUserByID(inputs.getUserIDInput());
		ArrayList<LibraryItem> foundList = user.getCheckedOutItems();
		
		
		serviceResults.println("=== Currently borrowed items ===");
		serviceResults.println("User: " + user.getName());
		if(foundList.isEmpty()) {
			serviceResults.println("No borrowed Items");
			return;
		}
		
		for(LibraryItem libraryItem : foundList) {
			serviceResults.println(libraryItem.outputString() + " Due: " + libraryItem.dueDateStringFormat());
		}
	}
	
	public void overdueAction() {
		ArrayList<LibraryItem> overdueBooks = library.getOverdueItems();
		
		systemReport.println("==== Overdue Items {Mock Data} ====");
		
//		for(LibraryItem libraryItem : overdueBooks) {
//			systemReport.println(libraryItem.toString());
//		}
		
		//Mock Data
		systemReport.println("-Interstellar: The Engineering of Space RECORDINGS");
		systemReport.println("-The C Programming Language BOOK");
		systemReport.println("==========================\n");
	}
	
	public void summaryAction() {
		systemReport.print(library.returnSummary());
		systemReport.println("\n");
	}
	
	public void exitAction() {
		primaryStage.setScene(menuScene);
	}


	public void close() {
		primaryStage.close();
	}
	
}
