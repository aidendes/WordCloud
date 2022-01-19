package ie.gmit.dip;

import java.util.*;
import java.util.Map.Entry;

/**
 * @author aidenmac
 *
 * Implementation of a <b>Comparator</b> for HashMaps
 * 
 * Simple comparison of the Values in a <K,V> dataset where
 * V is an Integer.
 * 
 * All values are passed, no time complexity arises.
 *
 */
public class CustomisedHashMap implements Comparator<Map.Entry<String, Integer>> {

	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		
		return o2.getValue().compareTo(o1.getValue());
	}
	

}
