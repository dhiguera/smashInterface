/**
 * 
 */
package FirstTest;

import java.io.*;
import java.util.*;
/**
 * @author David Higuera & Jeremie Meurisse
 *
 *  To read a txt file
 */
public class ReadFileTxt {

	Scanner input;
	/**
	 * Constructor
	 * @throws FileNotFoundException 
	 */
	public ReadFileTxt(String fileName) throws FileNotFoundException {

		 input = new Scanner(new File(fileName));
	
	}

	/**
	 * Test function
	 */
	public void getInt(){
		
		while(input.hasNextInt()){
			
			System.out.println(input.nextInt());
			
		}
		
	}
}
