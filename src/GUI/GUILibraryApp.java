package GUI;

import java.io.FileNotFoundException;

import Project1.Database;
import Project1.Library;
import javafx.application.Application;
import javafx.stage.Stage;

public class GUILibraryApp extends Application {
	
	GUInterface appInterface;
	private Stage primaryStage;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;
		
		//Library Creation
		String ItemFileName = "LibraryItems.dat";
		String UserInfoName = "UserInfo.dat";
		
		Library library = new Library(ItemFileName, UserInfoName);
		
		//interface creation
		appInterface = new GUInterface(library, primaryStage);
		
		primaryStage.setScene(appInterface.getMenuScene());
		
		
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	
}
