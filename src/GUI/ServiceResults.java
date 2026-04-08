/* Brighton Drill
 * Title: Service Results
 * Date: 4/7/2026
 * Description: displays service results
 */


package GUI;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class ServiceResults {

	//----------------------------
	// Controls
	//----------------------------
	
	private Label serviceResultLabel = new Label("Service Results");
	private TextArea serviceResultsOutput;
	
	//container
	private VBox container;
	
	//----------------------------
	// Constructor
	//----------------------------
	public ServiceResults() {
		setTextArea();
		
		container = new VBox(10, serviceResultLabel, serviceResultsOutput);
	}

	//----------------------------
	// Constructor helper methods
	//----------------------------
	private void setTextArea() {
		serviceResultsOutput = new TextArea();
		
		int width = 600;
		int height = 500;
		
		serviceResultsOutput.setPrefSize(width, height);
		
	}
	
	//----------------------------
	// Methods
	//----------------------------
	public VBox getContainer() {
		return container;
	}
	
	public void print(String printRequest) {
		serviceResultsOutput.appendText(printRequest);
	}
	
	public void println(String printRequest) {
		serviceResultsOutput.appendText(printRequest);
		serviceResultsOutput.appendText("\n");
	}
	
	
	
	
}
