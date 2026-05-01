/* Brighton Drill
 * Title: Service Results
 * Date: 4/7/2026
 * Description: displays service results
 */


package GUI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class ServiceResults extends VBox {

	//----------------------------
	// Controls
	//----------------------------
	private Label serviceResultLabel = new Label("Service Results");
	private TextArea serviceResultsOutput;
	
	
	//----------------------------
	// Constructor
	//----------------------------
	public ServiceResults() {
		setTextArea();
	}

	//----------------------------
	// Constructor helper methods
	//----------------------------
	private void setTextArea() {
		serviceResultsOutput = new TextArea();
		
		int width = 250;
		int height = 500;
		
		serviceResultsOutput.setPrefSize(width, height);
		
		//VBox config
		setPadding(new Insets(20));
		setSpacing(5);
		
		
		getChildren().addAll(serviceResultLabel, serviceResultsOutput);
	}
	
	//----------------------------
	// Methods
	//----------------------------
	
	/**
	 * prints to the serviceResults screen
	 * @param printRequest
	 */
	public void print(String printRequest) {
		serviceResultsOutput.appendText(printRequest);
	}
	
	/**
	 * prints and goes to the next line on the serviceResults screen
	 * @param printRequest
	 */
	public void println(String printRequest) {
		serviceResultsOutput.appendText(printRequest);
		serviceResultsOutput.appendText("\n");
	}
	
	public void clear() {
		serviceResultsOutput.clear();
	}
	
	
	
}
