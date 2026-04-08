/* Brighton Drill
 * Title: GUILibraryApp
 * Date: 4/7/2026
 * Description: starts the library application
 */

package GUI;

import java.io.FileNotFoundException;

import Project1.Database;
import Project1.Library;
import javafx.application.Application;
import javafx.stage.Stage;

public class GUILibraryApp extends Application {
	
	//----------------------------
	// Fields
	//----------------------------
	GUInterface appInterface; // appInterface
	
	/**
	 * Starts the application and is given a stage
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		
		//Library Creation
		String ItemFileName = "LibraryItems.dat";
		String UserInfoName = "UserInfo.dat";
		
		Library library = new Library(ItemFileName, UserInfoName);
		
		//interface creation
		appInterface = new GUInterface(library, primaryStage);
		
		primaryStage.setScene(appInterface.getMenuScene());
		
		
		primaryStage.show();
	}
	
	/**
	 * launches the start method
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	
}
