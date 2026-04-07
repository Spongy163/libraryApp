package GUI;

import java.io.FileNotFoundException;

import Project1.Database;
import Project1.Library;
import javafx.application.Application;
import javafx.stage.Stage;

public class GUILibraryApp extends Application {
	
	GUInterface appInterface;
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		
		
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		
		String ItemFileName = "LibraryItems.dat";
		String UserInfoName = "UserInfo.dat";
		
		Library library = new Library(ItemFileName, UserInfoName);
		
		
		
		launch(args);
	}

}
