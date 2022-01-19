package ie.gmit.dip;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author Aiden Desmond <G00398273@gmit.ie>
 * 
 * Concrete class implementation of <i>IgnoreWords</i>.
 * 
 * The value of ignorewords.txt is hardcoded, as per the parameters of
 * the project.
 * 
 * Class reads the values present in the file into a HashSet for the purposes
 * of speedy comparison of values.
 *
 */
public class IgnoreWordsImpl implements IgnoreWords {

	private Utils u = new Utils();
	private HashSet<String> filter = new HashSet<String>();
	private Scanner sc;
	private settings s;
	
	private class settings {
		
		public String getFileName() {
			return fileName;
		}
		
		String fileName = "./ignorewords.txt";
	}
	
	
	public IgnoreWordsImpl() {
		this.s = new settings();
	}
	
	
	/**
	 * Private function returning the HashSet.
	 * 
	 * Time Complexity for the construction of the HashSet is O(1)
	 * complexity of the reading function is of course I/O limited
	 * and O(n) in terms of reading the lines.
	 * 
	 * @return HashSet of Strings
	 */
	private HashSet<String> getFilter() {	
		try {
			this.sc = new Scanner(new File(s.getFileName()));
			while (this.sc.hasNext()) {
				this.filter.add(this.sc.next());
			}
			this.sc.close();
		} catch (FileNotFoundException e) {
			u.warning("./ignorewords.txt not found. Have you set this program up properly?");
		}
		return filter;
	}
			
		
	

	/**
	 * Public function performing the Interface, by calling the private method.
	 */
	@Override
	public HashSet<String> getIgnores()  {
	
		HashSet<String> filter = this.getFilter();
				
		u.menuItem("Ignorewords loaded: Ignoring " + filter.size() + " words.");

		return filter;
		}
	

}
