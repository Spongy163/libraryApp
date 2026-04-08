/* Brighton Drill
 * Title: bottom left panel
 * Date: 4/7/2026
 * Description: bottom left panel
 */

package GUI;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class SystemReportPanel {

	//----------------------------
	// Controls
	//----------------------------
	
	private Label librarySystemReportLabel = new Label("Library System Report");
	private TextArea reportOutput;
	
	private VBox container;
	
	//----------------------------
	// Constructor
	//----------------------------
	public SystemReportPanel(GUInterface userInterface) {
		
		setTextArea();
		
		container = new VBox(10, librarySystemReportLabel, reportOutput);
		
	}
	
	//----------------------------
	// Constructor Helper
	//----------------------------
	public void setTextArea() {
		reportOutput = new TextArea();
		 
		
		int textAreaWidth = 600;
		int textAreaHeight = 500;
		
		reportOutput.setPrefSize(textAreaWidth, textAreaHeight);
		
	}
	
	//----------------------------
	// Methods
	//----------------------------
	
	public VBox getContainer() {
		return container;
	}
	
	public void print(String printRequest) {
		reportOutput.appendText(printRequest);
	}
	
	public void println(String printRequest) {
		reportOutput.appendText(printRequest);
		reportOutput.appendText("\n");
	}

}
