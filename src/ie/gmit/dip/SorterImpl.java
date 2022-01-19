package ie.gmit.dip;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * @author Aiden Desmond <G00398273@gmit.ie>
 * 
 * An implentation of the Sorter Interface used here to Convert a HashMap 
 * to a LinkedHashMap, with the Keys sorted according to the descending 
 * order of the Values.
 * 
 * Time Complexity is addressed in the individual methods
 *
 */
public class SorterImpl implements Sorter {

	private settings sort;
	
	private class settings {
		public HashMap<String,Integer> hm;

		public HashMap<String, Integer> getHm() {
			return hm;
		}

		public void setHm(HashMap<String, Integer> hm) {
			this.hm = hm;
		}
		
	}
	
	public SorterImpl() {
			this.sort = new settings();
	}
	
	/**
	 * orderedHash()
	 * Private implementation method which sorts the values of a HashMap into a 
	 * LinkedHashMap.
	 * 
	 * Time Complexity as follows:-
	 * 
	 * Populating the LinkedList is an O(1) operation
	 * The Sort Method is O(n*log n), using the standard Collections.sort
	 * Inserting the values to the new LinkedHashMap is O(1)
	 * 
	 * 
	 * @return LinkedHashMap sorted version of the initial HashMap.
	 */
	private LinkedHashMap<String,Integer> orderedHash() {
		
		HashMap<String, Integer> hm = sort.getHm();

		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());
		
		// Use collections framework for speedy comparison
		// Sorting using CustomisedHashMap class
		Collections.sort(list, new CustomisedHashMap());
		
		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();

		
		// put data from sorted list into a LinkedHashmap
		// Using opportunity to remove empty Keys, keys consisting of an apostrophe
		// and all values where the freq count is 2 or less.
		
		for (Map.Entry<String, Integer> entry : list) {
			if (!entry.getKey().isEmpty() && !entry.getKey().matches("'")) {
				if (entry.getValue() > 2) {
					sortedMap.put(entry.getKey(), entry.getValue());
				}
			}
		}
		return sortedMap;
	}
	
	
	@Override
	public LinkedHashMap<String, Integer> sortByValue() {
		
		LinkedHashMap<String, Integer> sortedMap = orderedHash();
		
		return sortedMap;
	}
	
	@Override
	public void setHashToOrder(HashMap<String,Integer> hm) {
		sort.setHm(hm);
	}
}
