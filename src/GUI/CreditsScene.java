/* Brighton Drill
 * Title: Credits Scene
 * Date: April 27th, 2026
 * Description: A container that displays credits
 */


package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CreditsScene extends VBox {

	public CreditsScene(Stage primaryStage, GUInterface userInterface) {
		super(10);
		
		//Font Size
		int titleFontSize = 70;
		int nameFontSize = 50;
		
		//Credit Labels
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
		
		//Return button
		Button returnButton = new Button("Return");
		returnButton.setPrefSize(400, 100);
		returnButton.setFont(new Font(nameFontSize));
		returnButton.setOnAction(e -> primaryStage.setScene(userInterface.getMenuScene()));
		
		getChildren().addAll(development, brightonDrill, design, drKim, johnAdemola, brightonDrill2, returnButton);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(50));
	}
	
	
	
	
	
}
