/* Brighton Drill
 * Title: GUInterface
 * Date: 4/7/2026
 * Description: 
 */




package GUI;

import Project1.Library;
import Project1.LibraryActions;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GUInterface {

	//----------------------------
	// Class Fields
	//----------------------------
	
	//Library and Actions
	private Library library;
	private LibraryActions libraryActions;
	
	//Stage
	private Stage primaryStage;
	
	//MenuBar
	private TopMenu topMenu;
	
	//Panels
	private LibraryServices libraryServices;
	private SearchTypes searchTypes;
	private EnterInformation enterInformation;
	private ServiceResults serviceResults;
	
	//Main App container
	private BorderPane container;

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
		
		menuExitBinding();
		
		//Setting Scenes
		MenuScene menu = new MenuScene(this);
		
		menuScene = menu.getScene();
		appScene = new Scene(container, SizingSettings.SceneWidth, SizingSettings.SceneHeight);
		makeCreditsScene();
		
		//library controller class
		libraryActions = new LibraryActions(library, primaryStage, topMenu, libraryServices, searchTypes, enterInformation, serviceResults);
		
	}


	//----------------------------
	// Constructor helper methods
	//----------------------------
	
	/**
	 * menu Exit handler, goes back to menu scene
	 */
	private void menuExitBinding() {
		topMenu.getExit().setOnAction(e -> {
			primaryStage.setScene(menuScene);
		});
	}
	
	
	/**
	 * Creates a credit Scene
	 */
	private void makeCreditsScene() {
		CreditsScene creditsSceneBox = new CreditsScene(primaryStage, this);
		creditsScene = new Scene(creditsSceneBox, SizingSettings.SceneWidth, SizingSettings.SceneHeight);
	}
	
	
	/**
	 * creates the different GUI corner classes
	 */
	private void instantiatePanels() {
		topMenu = new TopMenu();
		libraryServices = new LibraryServices();
		searchTypes = new SearchTypes();
		enterInformation = new EnterInformation();
		serviceResults = new ServiceResults();
	}
	
	/**
	 * builds the app container for the appScene
	 */
	private void buildAppContainer() {
		//Container 
		container = new BorderPane();
		
		//Top
		container.setTop(topMenu);
		
		HBox topControls = new HBox(40);
		topControls.setAlignment(Pos.TOP_LEFT);

		topControls.getChildren().addAll(
		    libraryServices,
		    searchTypes,
		    enterInformation
		);
		

		container.setCenter(topControls);
		
		//Bottom
		container.setBottom(serviceResults);
		
	}
	
	//----------------------------
	// Menu Actions
	//----------------------------
	/**
	 * sets the scene to appScene
	 */
	public void startApp() {
		primaryStage.setScene(appScene);
	}
	
	/**
	 * sets scene to credits scene
	 */
	public void creditsScene() {
		primaryStage.setScene(creditsScene);
	}

	/**
	 * passes menu scene to GUILibraryApp to set as the initial scene
	 * @return menuScene
	 */
	public Scene getMenuScene() {
	    return menuScene;
	}
	
	//----------------------------
	// Button Actions
	//----------------------------
	
	
	/**
	 * goes back to the menu
	 */
	public void exitAction() {
		primaryStage.setScene(menuScene);
	}

	/**
	 * closes the application
	 */
	public void close() {
		primaryStage.close();
	}
	
}
