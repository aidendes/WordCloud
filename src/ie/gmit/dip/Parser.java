package ie.gmit.dip;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author Aiden Desmond <G00398273@gmit.ie
 *
 * Interface to obtain a HashMap of: 
 * Strings from an input stream operation
 * Integers of the number of times these Strings appear in the text.
 * 
 * Time Complexity addressed in Concrete Classes
 *
 */
public interface Parser {

	public HashMap<String,Integer> wordCount(String resource) throws IOException;
	
}
