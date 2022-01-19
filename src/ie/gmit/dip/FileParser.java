package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Aiden Desmond <G00398273@gmit.ie
 * 
 * FileParser
 * Concrete Implementation of <i>Parser</i> Class
 * 
 * Reads a file from I/O, and creates a HashMap of:
 * Strings from a <b>File</b> input operation
 * Integers of the number of times these Strings appear in the text.
 * 
 * Time Complexity is addressed in the individual methods
 *
 */
public class FileParser implements Parser {

	private Utils u = new Utils();
	private IgnoreWords iw = new IgnoreWordsImpl();
	private HashMap<String, Integer> table = new HashMap<String, Integer>();

	private final HashSet<String> getFilter() {
		HashSet<String> filter = null;
		try {
			filter = iw.getIgnores();
		} catch (FileNotFoundException f) {
			u.warning("something went wrong...");
		}

		return filter;

	}

	public FileParser() {

	}

	/**
	 * The Primary Word Counting Function (File Version)
	 * 
	 * Reads a file using BufferedReader, and populates a HashMap
	 * Utilises IgnoreWords interface as a filter of words not to add.
	 * 
	 * Time Complexity:
	 * 
	 * Reading I/O file is OS-dependent, but of character O(n) where n is 
	 * the number of lines to read.
	 * Filtering Operation has an outer bound of O(n) where n is the number of words
	 * in a line, but O(1) to search the filter for a matching word.
	 * Adding <K,V> to the HashMap is also a O(1) operation
	 * 
	 * @param resource to be read, in this case a file.
	 * @return HashMap of <String,Integer> values.
	 */
	private HashMap<String, Integer> fileCounter(String resource) {
		try {
			BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(new File(resource))));
			this.u.menuItem("Reading from file: " + resource);
			HashSet<String> filter = this.getFilter();

			// Thanks for showing us this new -> method! Don't think I used it more than in these parsers
			file.lines().forEach(line -> {
				String[] words = line.toLowerCase().replaceAll("[!?“”’_.,0-9|-]", "").split("\\s+");

				for (String word : words) {
					if (filter.contains(word)) {
					} else
						table.compute(word, (k, v) -> v == null ? 1 : v + 1);
				}
			});
			file.close();
		} catch (IOException e) {
			u.warning("Something went wrong.");
		}
		return table;

	}

	/**
	 * Public conformant interface fulfilment.
	 */
	@Override
	public HashMap<String, Integer> wordCount(String resource) throws IOException {

		HashMap<String, Integer> table = fileCounter(resource);
		return table;

	}

}
