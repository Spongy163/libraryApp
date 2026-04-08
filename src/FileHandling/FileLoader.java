

package FileHandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class FileLoader<T, E extends Enum<E>> {
	//----------------------------
	// Data Fields
	//----------------------------
	protected Scanner dataReader;
	protected File inputFile; 
	protected ArrayList<T> output;
	
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
