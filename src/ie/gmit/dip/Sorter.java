package ie.gmit.dip;

import java.util.*;

/**
 * @author Aiden Desmond <G00398273@gmit.ie>
 * 
 * Interface to Convert a HashMap to a LinkedHashMap, with the values of 
 * the LinkedHashMap sorted according to a value to be determined in the
 * implementation.
 * 
 * All methods are abstract.
 * 
 * Time complexity is dealt with in the concrete class.
 *
 */
public interface Sorter {

		public abstract LinkedHashMap<String, Integer> sortByValue();
		
		public abstract void setHashToOrder(HashMap<String,Integer> hm);
}


