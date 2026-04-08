/* Brighton Drill
 * Title: FileHandling
 * Date: 4/7/2026
 * Description: An abstract class for file handling classes
 */

package FileHandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class FileLoader<T, E extends Enum<E>> {
	//----------------------------
	// Data Fields
	//----------------------------
	protected Scanner dataReader; // reads data from file
	protected File inputFile; // the input file
	protected ArrayList<T> output; // An ArrayList of derived data
	
	//----------------------------
	// Constructor
	//----------------------------
	public FileLoader(String filename) {
		inputFile = new File(filename);
		try {
			dataReader = new Scanner(inputFile);
		} catch (FileNotFoundException fnfe) {
			System.out.println("Error: " + filename + " was not found | " + fnfe);
		}
	}
	
	
	//----------------------------
	// Abstract methods
	//----------------------------
	
	/**
	 * Reads data from file and returns an ArrayList of created objects
	 * @return an ArrayList of data
	 */
	public abstract ArrayList<T> readData();
	
	/**
	 * When data is not formatted correctly, 
	 * this method will return what is missing to the console
	 * @param errorIndex
	 */
	protected abstract void findItemError(int errorIndex);
	
	/**
	 * Checks what type of object something is by ID
	 * @param ID
	 * @return the type as a String
	 */
	protected abstract E checkType(String ID);

}
