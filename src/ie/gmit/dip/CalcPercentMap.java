package ie.gmit.dip;

import java.util.*;
import java.util.Map.Entry;

/**
 * @author Aiden Desmond <G00398273@gmit.ie>
 * 
 * CalcPercentMap is an <i>implementation</i> or concrete class of the abstract
 * interface type <i>CalcMap</i>
 * 
 * This implementation reconfigures a LinkedHashMap by a two-step process:
 * 1) Sums all the Integer values of the Entries 
 * 2) Recalculates those values as a percentage of the total.
 * 
 * The private class settings is used to populate the initial LinkedHashMap.
 * All abstract classes from the interface are fully realised.
 *
 */
public class CalcPercentMap implements CalcMap {

	private settings calc;
	
	/**
	 * Setter and Getter method to set the values necessary for functioning
	 *
	 */
	private class settings {
		public LinkedHashMap<String,Integer> hm;

		public LinkedHashMap<String, Integer> getHm() {
			return hm;
		}

		public void setHm(LinkedHashMap<String, Integer> hm) {
			this.hm = hm;
		}
	}
	
	/**
	 * Initialises the setting values.
	 */
	public CalcPercentMap() {
		this.calc = new settings();
	}
	
	/**
	 * calcMap 
	 * A private class fulfilling the CalcMap <i>interface</i> contract.
	 * 
	 * This is a private method, and is not accessible outside the class.
	 * 
	 * Time Complexity of the elements is as follows:
	 * 
	 * IntSum: O(n) as it traverses the entire LinkedHashMap
	 * Conversion of HashMap to Set: O(n) as traversal
	 * Conversion of Set to HashMap: O(n) as traversal
	 * 
	 * Note, however, that the value of _n_ is restricted 
	 * elsewhere in the program by user input.
	 * 
	 * @return LinkedHashMap with values remapped to percentages.
	 */
	private LinkedHashMap<String,Integer> calcMap() {
		
		LinkedHashMap<String,Integer> hm = calc.getHm();
		LinkedHashMap<String,Integer> calcMap = new LinkedHashMap<>();
		Set<Entry<String, Integer>> s = hm.entrySet();
		
		Iterator<Entry<String, Integer>> it = s.iterator();
		
		Map.Entry<String, Integer> entry = null;
		
		Integer intSum = hm.values().stream().mapToInt(Integer::intValue).sum();
		
		int i = 0;
		while (it.hasNext() && i <= hm.size()) {
			entry = it.next();

			int p = Math.round((entry.getValue() * 100) / intSum) + 1;

			calcMap.put(entry.getKey(), p);
			i++;

		}
		return calcMap;
	}
	
	/**
	 * The public "face" of the Concrete Class. Simply calls the
	 * private method.
	 * 
	 * @return LinkedHashMap with values remapped to percentages.
	 */
	@Override
	public LinkedHashMap<String,Integer> calcValues() {
		LinkedHashMap<String,Integer> calcMap = calcMap();
		return calcMap;
	}
	
	/**
	 * Simple setter to pass the value of the initial LinkedHashMap
	 * 
	 */
	@Override
	public void setCalcMap(LinkedHashMap<String,Integer> hm) {
		calc.setHm(hm);
	}
}

