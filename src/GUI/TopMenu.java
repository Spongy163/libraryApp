/* Brighton Drill
 * Title: TopMenu
 * Date: April 30th, 2026
 * Description: A MenuBar class
 */

package GUI;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class TopMenu extends MenuBar {

	//----------------------------
	// Class Objects
	//----------------------------
	
	//System menu items
	MenuItem loadData;
	MenuItem exit;
	
	//Overview menu items
	MenuItem summary;
	MenuItem analysis;
	MenuItem overdue;
	
	//----------------------------
	// Constructor 
	//----------------------------
	
	public TopMenu() {
		//Creating Menus
		Menu system = new Menu("System");
		Menu overview = new Menu("Overview");
		
		//instantiating menu items
		instantiateMenuItems();
		
		//adding menu items to menus
		system.getItems().addAll(loadData, exit);
		overview.getItems().addAll(summary, analysis, overdue);
		
		//adding menus to MenuBar
		getMenus().addAll(system, overview);
	}
	
	//----------------------------
	// Helper Methods
	//----------------------------
	
	private void instantiateMenuItems() {
		//System menu items
		loadData = new MenuItem("Load Data");
		exit = new MenuItem("Exit");
		
		//Overview menu items
		summary = new MenuItem("Summary");
		analysis = new MenuItem("Analysis");
		overdue = new MenuItem("Overdue");
	}
	
	//----------------------------
	// Menu Item getter methods
	//----------------------------

	public MenuItem getLoadData() {
		return loadData;
	}

	public MenuItem getExit() {
		return exit;
	}

	public MenuItem getSummary() {
		return summary;
	}

	public MenuItem getAnalysis() {
		return analysis;
	}

	public MenuItem getOverdue() {
		return overdue;
	}
	
}
