/* Brighton Drill
 * Title: Menu
 * Date: 4/7/2026
 * Description: a menu for the library app
 */



package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Menu {
	//----------------------------
	// Controls
	//----------------------------
	private Label title = new Label("Library App!");
	
	protected Button startButton = new Button("Start"); // start button
	protected Button creditsButton = new Button("Credits"); // credits button
	protected Button exitButton = new Button("Exit"); // exit button
	
	private VBox container; // container
	
	private Scene menuScene; // scene
	
	
	//----------------------------
	// Constructor 
	//----------------------------
	public Menu(GUInterface appInterface) {
		//Button alignment
		setButtonSize();
		
		//Container
		container = new VBox(20, title, startButton, creditsButton, exitButton);
		
		//alignment and spacing
		container.setAlignment(Pos.CENTER);
		container.setPadding(new Insets(50));
		title.setFont(new Font(70));
		
		// button actions
		setButtonActions(appInterface);
		
		//Scene setting
		menuScene = new Scene(container, 1300, 824);
	}
	
	
	//----------------------------
	// Constructor methods
	//----------------------------
	/**
	 * sets the button sizes and fonts equal to eachother
	 */
	private void setButtonSize() {
		int width = 400;
		int height = 100;
		
		startButton.setPrefSize(width, height);
		creditsButton.setPrefSize(width, height);
		exitButton.setPrefSize(width, height);
		
		int fontSize = 50;
		startButton.setFont(new Font(fontSize));
		creditsButton.setFont(new Font(fontSize));
		exitButton.setFont(new Font(fontSize));
		
	}
	
	/**
	 * sets ActionEvents to appInterface actions
	 * @param appInterface
	 */
	private void setButtonActions(GUInterface appInterface) {
		startButton.setOnAction(e -> appInterface.startApp());
		creditsButton.setOnAction(e -> appInterface.creditsScene());
		exitButton.setOnAction(e -> appInterface.close());
	}
	
	/**
	 * @return the menuScene
	 */
	public Scene getScene() {
		return menuScene;
	}

}
