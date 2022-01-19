package ie.gmit.dip;

import java.io.FileNotFoundException;
import java.util.HashSet;

/**
 * @author Aiden Desmond <G00398273@gmit.ie>
 * 
 * Interface to obtain a HashSet of Strings from a File input operation.
 * 
 * Time complexity is dealt with in the concrete class.
 *
 */
public interface IgnoreWords {

	public abstract HashSet<String> getIgnores() throws FileNotFoundException;

}