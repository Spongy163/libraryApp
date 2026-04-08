/* Brighton Drill
 * Title: systemReport
 * Date: 4/7/2026
 * Description: systemreport
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
	/**
	 * initializes and sets size of TextArea
	 */
	public void setTextArea() {
		reportOutput = new TextArea();
		 
		
		int textAreaWidth = 600;
		int textAreaHeight = 500;
		
		reportOutput.setPrefSize(textAreaWidth, textAreaHeight);
		
	}
	
	//----------------------------
	// Methods
	//----------------------------
	/**
	 * @return class container
	 */
	public VBox getContainer() {
		return container;
	}
	
	/**
	 * prints to the systemReport screen
	 * @param printRequest
	 */
	public void print(String printRequest) {
		reportOutput.appendText(printRequest);
	}
	
	/**
	 * prints to the systemReport screen
	 * goes to the next line
	 * @param printRequest
	 */
	public void println(String printRequest) {
		reportOutput.appendText(printRequest);
		reportOutput.appendText("\n");
	}

}
