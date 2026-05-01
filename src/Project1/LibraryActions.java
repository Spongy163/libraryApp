/* Brighton Drill
 * Title: Library Actions
 * Date: April 27th, 2026
 * Description: A class that handles library actions
 */

package Project1;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import FileHandling.ItemType;
import GUI.EnterInformation;
import GUI.LibraryServices;
import GUI.SearchTypes;
import GUI.ServiceResults;
import GUI.TopMenu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import libraryItems.LibraryItem;
import users.User;

public class LibraryActions {

	// ----------------------------
	// Class Objects
	// ----------------------------
	private Library library;
	private Stage primaryStage;

	// GUI panels
	private TopMenu topMenu;
	private LibraryServices libraryServices;
	private SearchTypes searchTypes;
	private EnterInformation enterInformation;
	private ServiceResults serviceResults;

	// ----------------------------
	// Constructor
	// ----------------------------
	public LibraryActions(Library library, Stage primaryStage, TopMenu topMenu, LibraryServices libraryServices,
			SearchTypes searchTypes, EnterInformation enterInformation, ServiceResults serviceResults) {
		
		// setting objects
		this.library = library;
		this.primaryStage = primaryStage;
		this.topMenu = topMenu;
		this.libraryServices = libraryServices;
		this.searchTypes = searchTypes;
		this.enterInformation = enterInformation;
		this.serviceResults = serviceResults;

		// Binding actions
		loadLogEntryFile();
		displaySummary();
		analysisAction();
		overdueAction();
		executeAction();
		clearAction();
	}

	// ----------------------------
	// Helper methods
	// ----------------------------

	/**
	 * Shows an alert
	 * @param title
	 * @param header
	 * @param message
	 */
	private void showAlert(String title, String header, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(message);
		alert.showAndWait();
	}

	/**
	 * shows an error alert
	 * @param title
	 * @param header
	 * @param message
	 */
	private void showErrorAlert(String title, String header, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(message);
		alert.showAndWait();
	}

	// ----------------------------
	// Actions
	// ----------------------------

	/**
	 * Allows user to select a file to load log entry samples binds this ability to
	 * button
	 */
	private void loadLogEntryFile() {
		topMenu.getLoadData().setOnAction(e -> {
			//user's file choice
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Choose Log Entry Sample File");
			File selectedFile = fileChooser.showOpenDialog(primaryStage);
			
			//check
			if(selectedFile == null) {
				return;
			}
			
			//loads binary data
			try (
					// resources
					FileInputStream inputStream = new FileInputStream(selectedFile);
					DataInputStream input = new DataInputStream(inputStream);) {
				int recordCount = 0;

				while (input.available() > 0) {
					String type = input.readUTF();
					String title = input.readUTF();
					String name = input.readUTF();
					String timeStampAsString = input.readUTF();

					LocalDateTime timeStamp = LocalDateTime.parse(timeStampAsString);

					library.addData(type, title, name, timeStamp);
					recordCount++;
				}

				// Load successful alert
				String contentText = "Load Data completed successfully. \nRecords loaded: " + recordCount;
				showAlert("Load Successful", "Information" , contentText);
				
				//Exception handling
			} catch (IOException ioe) {
				System.out.println("Data loading unsuccessful");
				showErrorAlert("Data loading unsuccessful", "Error", "Failed to load the selected file");
			} catch (DateTimeParseException dtpe) {
				System.out.println("Local date parse error");
			}

		});
	}

	/**
	 * binds a display summary to the menu item on the top menu
	 */
	private void displaySummary() {
		topMenu.getSummary().setOnAction(e -> {
			//Creating stage
			Stage summaryStage = new Stage();
			summaryStage.setTitle("Summary");
			
			VBox box = new VBox(10);
			
			//TextArea config
			TextArea textArea = new TextArea();
			textArea.setEditable(false);
			textArea.appendText(library.returnSummary());
			textArea.setPrefRowCount(25);
			Button close = new Button("Close");

			close.setOnAction(ec -> {
				summaryStage.close();
			});

			// box config
			box.getChildren().addAll(textArea, close);
			box.setAlignment(Pos.CENTER_LEFT);
			box.setPadding(new Insets(20));

			Scene summaryScene = new Scene(box);
			summaryStage.setScene(summaryScene);

			summaryStage.show();

		});
	}

	/**
	 * attaches functionality to the analysis menu item
	 */
	private void analysisAction() {
		topMenu.getAnalysis().setOnAction(e -> {

			// Stage Building
			Stage analysisStage = new Stage();
			analysisStage.setTitle("Analysis");

			VBox box = new VBox(10);
			
			//textArea config
			TextArea textArea = new TextArea();
			textArea.setEditable(false);
			textArea.setFont(Font.font("Monospaced"));
			textArea.appendText(library.returnAnalytics());
			textArea.setPrefRowCount(25);
			Button close = new Button("Close");
			
			//Close button config
			close.setOnAction(ec -> {
				analysisStage.close();
			});

			// box config
			box.getChildren().addAll(textArea, close);
			box.setAlignment(Pos.CENTER_LEFT);
			box.setPadding(new Insets(20));

			// Stage building
			Scene summaryScene = new Scene(box);
			analysisStage.setScene(summaryScene);

			analysisStage.show();
		});
	}
	
	/**
	 * attaches functionality to the overdue menu item
	 */
	private void overdueAction() {
		topMenu.getOverdue().setOnAction(e -> {
			Stage overdueStage = new Stage();
			overdueStage.setTitle("Overdue");

			VBox box = new VBox(10);

			//textAreaConfig
			TextArea textArea = new TextArea();
			textArea.setEditable(false);
			textArea.appendText("Overdue Items (Mock data for testing): \n- Interstellar: The Engineering of Space (Recordings)\n- The C Programming Language (Book)");
			textArea.setPrefRowCount(25);
			Button close = new Button("Close");

			close.setOnAction(ec -> {
				overdueStage.close();
			});

			// box config
			box.getChildren().addAll(textArea, close);
			box.setAlignment(Pos.CENTER_LEFT);
			box.setPadding(new Insets(20));

			Scene overdueScene = new Scene(box);
			overdueStage.setScene(overdueScene);

			overdueStage.show();
		});
	}

	/**
	 * adds functionality to the execute button
	 */
	public void executeAction() {
		enterInformation.getExecute().setOnAction(e -> {
			RadioButton selectedRadio = libraryServices.getSelectedRadio();

			if (selectedRadio == null) {
				showAlert("No Service Selected", "Choose a library service",
						"Please select an action before pressing Execute.");
				return;
			}

			String selectedAction = selectedRadio.getText();
			
			//performs an action based on the selected radio
			switch (selectedAction) {
			case "Item Search":
				itemSearchAction();
				break;

			case "Keyword Search":
				keywordSearchAction();
				break;

			case "Checkout":
				checkoutAction();
				break;

			case "Return Item":
				returnItemAction();
				break;

			case "Borrowed Items":
				borrowedItemsAction();
				break;

			default:
				System.out.println("Error: fell through case block in executeAction");
				break;
			}
		});
	}
	
	
	
	
	/**
	 * searches for item using itemIDInput from inputs
	 */
	public void itemSearchAction() {
		String itemID = enterInformation.getItemID().getText();
		LibraryItem libraryItem;
		
		if(itemID.isBlank()) {
			showErrorAlert("Error", "Error", "Please enter Item ID");
		} else {
			try {
				libraryItem = library.findItemByItemID(itemID);
				
				serviceResults.clear();
				serviceResults.println(libraryItem.toString());
				
			} catch (IllegalArgumentException iae) {
				showErrorAlert("Error", "Error", "Invalid Item ID");
			}
		}
	}

	/**
	 * searches for items using the keyword input
	 */
	public void keywordSearchAction() {
		String keyword = enterInformation.getKeyword().getText();
		
		if(keyword.isBlank()) {
			showErrorAlert("Error", "Error", "Please enter a keyword");
			return;
		}
		
		//converts data into an array
		boolean[] searchTypeList = new boolean[3];
		
		searchTypeList[0] = searchTypes.isBookSelected();
		searchTypeList[1] = searchTypes.isPeriodicalSelected();
		searchTypeList[2] = searchTypes.isRecordingsSelected();
		
		ArrayList<LibraryItem> foundItems = library.searchItemsByTitle(keyword, searchTypeList);
		
		serviceResults.clear();
		
		if(foundItems == null || foundItems.isEmpty()) {
			serviceResults.println("No results");
			return;
		}
		
		for(LibraryItem item : foundItems) {
			serviceResults.println(item.outputString());
		}
		
	}

	/**
	 * attempts to checkout using itemID and userID inputs
	 */
	public void checkoutAction() {
		String itemID = enterInformation.getItemID().getText().trim();
		String userID = enterInformation.getUserID().getText().trim();
		
		//check for information
		if(itemID.isBlank() && userID.isBlank()) {
			showErrorAlert("Error", "Error", "Please enter ItemID and UserID");
			return;
		}
		if(itemID.isBlank()) {
			showErrorAlert("Error", "Error", "Please enter ItemID");
			return;
		}
		if(userID.isBlank()) {
			showErrorAlert("Error", "Error", "Please enter UserID");
			return;
		}
		
		//creates the item and user
		LibraryItem item;
		User user;
		try {
			item = library.findItemByItemID(itemID);
			user = library.findUserByID(userID);
		} catch (IllegalArgumentException e) {
			showErrorAlert("Error", "Error", "Invalid item ID");
			return;
		} catch (NoSuchElementException nsee) {
			showErrorAlert("Error", "Error", "Invalid user ID");
			return;
		}
		
		//checks
		if(item.getItemType().equals(ItemType.PERIODICAL)) {
			showErrorAlert("Error", "Error", "Cannot check out a Periodical");
			return;
		}
		
		if(item.isCheckedOut()) {
			showErrorAlert("Error", "Error", "Item is already checked out");
			return;
		}
		if(user.limitReached()) {
			showErrorAlert("Error", "Error", "User has already reached their limit");
			return;
		}
		
		//performs the operation
		boolean result = library.checkoutItem(user, item);
		String message = "Checkout successful\nDue: " + item.getDueDate();
		
		if(result) {
			serviceResults.clear();
			serviceResults.println(message);
			showAlert("Message", "Information", message);
		} else {
			showErrorAlert("Error", "Error", "Error 113: Could not checkout item");
		}
	}

	/**
	 * attempts to return using itemID and userID inputs
	 */
	public void returnItemAction() {
		String itemID = enterInformation.getItemID().getText().trim();
		String userID = enterInformation.getUserID().getText().trim();
		
		//check for information
		if(itemID.isBlank() && userID.isBlank()) {
			showErrorAlert("Error", "Error", "Please enter ItemID and UserID");
			return;
		}
		if(itemID.isBlank()) {
			showErrorAlert("Error", "Error", "Please enter ItemID");
			return;
		}
		if(userID.isBlank()) {
			showErrorAlert("Error", "Error", "Please enter UserID");
			return;
		}
		
		//finding item and user
		LibraryItem item;
		User user;
		try {
			item = library.findItemByItemID(itemID);
			user = library.findUserByID(userID);
		} catch (IllegalArgumentException e) {
			showErrorAlert("Error", "Error", "Invalid item ID");
			return;
		} catch (NoSuchElementException nsee) {
			showErrorAlert("Error", "Error", "Invalid user ID");
			return;
		}
		
		//checks
		if(item.getItemType().equals(ItemType.PERIODICAL)) {
			showErrorAlert("Error", "Error", "Cannot return a Periodical");
			return;
		}
		
		if(!user.hasItem(itemID) && item.isCheckedOut()) {
			showErrorAlert("Error", "Error", "Item is checked out by a different user");
			return;
		}
		if(!user.hasItem(itemID)) {
			showErrorAlert("Error", "Error", "User does not have this item");
			return;
		}
		
		//performs the operation
		boolean result = library.returnItem(user, item);
		String message = "Return Successful";
		
		if(result) {
			serviceResults.clear();
			serviceResults.println(message);
			showAlert("Message", "Information", message);
		} else {
			showErrorAlert("Error", "Error", "Error 113: Could not return item"); //should not reach
		}
	}

	/**
	 * displays borrowed items for entered userID
	 */
	public void borrowedItemsAction() {
		String userID = enterInformation.getUserID().getText().trim();
		
		if(userID.isBlank()) {
			showErrorAlert("Error", "Error", "Please enter UserID");
			return;
		}
		
		User user;
		
		try {
			user = library.findUserByID(userID);
		} catch (NoSuchElementException nsee) {
			showErrorAlert("Error", "Error", "Invalid user ID");
			return;
		}
		
		serviceResults.clear();
		
		ArrayList<LibraryItem> items = user.getCheckedOutItems();
		serviceResults.print("User: ");
		serviceResults.println(user.getName());
		serviceResults.println("");
		serviceResults.println("Currently borrowed items: ");
		
		if(items.isEmpty()) {
			serviceResults.println("User has no borrowed items");
			return;
		}
		
		for(LibraryItem item : items) {
			serviceResults.print("- ");
			serviceResults.print(item.outputString());
			String dueDate = " (Due: " + item.getDueDate() + ")";
			serviceResults.println(dueDate);
		}
		
	}

	/**
	 * Clears text fields
	 */
	public void clearAction() {
		enterInformation.getClear().setOnAction(e -> {
			enterInformation.getItemID().clear();
			enterInformation.getUserID().clear();
			enterInformation.getKeyword().clear();
		});
	}

}
