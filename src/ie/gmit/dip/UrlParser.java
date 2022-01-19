package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Aiden Desmond <G00398273@gmit.ie
 * 
 * UrlParser
 * Concrete Implementation of <i>Parser</i> Class
 * 
 * Reads an Url from the internet, and creates a HashMap of:
 * Strings from a <b>File</b> input operation
 * Integers of the number of times these Strings appear in the text.
 * 
 * <b>The matching of html elements is very rudimentary, but yields
 * good results from longform articles.</b>
 * 
 * Time Complexity is addressed in the individual methods
 *
 */

public class UrlParser implements Parser {

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

	public UrlParser() {

	}

	/**
	 * The Primary Word Counting Function (URL Version)
	 * 
	 * Reads a file using BufferedReader on a Stream provided by URLConnection
	 * and populates a HashMap.
	 * Utilises IgnoreWords interface as a filter of words not to add.
	 * 
	 * Time Complexity:
	 * 
	 * Reading I/O file is OS- and Internet-dependent, but of character O(n) where n is 
	 * the number of lines to read.
	 * Filtering Operation has an outer bound of O(n) where n is the number of words
	 * in a line, but O(1) to search the filter for a matching word.
	 * Adding <K,V> to the HashMap is also a O(1) operation
	 * 
	 * @param resource to be read, in this case a file.
	 * @return HashMap of <String,Integer> values.
	 */
	private HashMap<String, Integer> urlCounter(String resource) {
		try {
			URL url = new URL(resource);
			URLConnection uc = url.openConnection();
			BufferedReader file = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			HashSet<String> filter = this.getFilter();

			file.lines().forEach(line -> {
				String[] words = line.toLowerCase().replaceAll("<.*?>", "").replaceAll("[{}\"!?“”’_.,0-9|-]", "")
						.split("\\s+");
				for (String word : words) {
					
					// This is very ugly, but it gives a moderately decent wordlist
					// from most long-form webpages
					// I chose the value of 15 as an upper bound to English language 
					// wordlength. A utility library might be better here.
					
					if (filter.contains(word) 
							|| word.length() <= 2 
							|| word.length() > 15 
							|| word.contains("http")
							|| word.contains("@")) {
					} else
						table.compute(word, (k, v) -> v == null ? 1 : v + 1);
				}

			});

		} catch (IOException e) {
			u.warning("Something Went Wrong");
		}

		return table;

	}

	
	/**
	 * Public conformant interface fulfilment.
	 */
	@Override
	public HashMap<String, Integer> wordCount(String resource) throws IOException {

		HashMap<String, Integer> table = urlCounter(resource);

		return table;
	}

}
