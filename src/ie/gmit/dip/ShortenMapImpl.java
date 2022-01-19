package ie.gmit.dip;

import java.util.*;
import java.util.Map.Entry;
/**
* @author Aiden Desmond <G00398273@gmit.ie>
* 
* Concrete class implementation of <i>ShortMap</i>.
* 
* Values of the initial LinkedHashMap and the Bounds are
* set by public classes. Function is carried out by a 
* private class in accordance with abstraction.
*
*/
public class ShortenMapImpl implements ShortMap {

	private settings ss;
	
	private class settings {
		
		public LinkedHashMap<String, Integer> getHm() {
			return hm;
		}
		public void setHm(LinkedHashMap<String, Integer> hm) {
			this.hm = hm;
		}
		public Integer getThreshold() {
			return thresh;
		}
		public void setThreshold(Integer thresh) {
			this.thresh = thresh;
		}
		LinkedHashMap<String, Integer> hm;
		Integer thresh;		
	}
	
	public ShortenMapImpl() {
		this.ss = new settings();
	}
	
	/**
	 * Implementation of the shortening function.
	 * 
	 * This is more complex than otherwise because there is no
	 * method to easily traverse a LinkedHashMap within bounds.
	 * 
	 * Time Complexity:
	 * Conversion to Set is O(n) to traverse the LinkedHashMap
	 * Parameterised iteration on the Set is also O(n) but operates
	 * at a finite Integer Value for n set by the User.
	 * 
	 * 
	 * @param hm LinkedHashMap to be shortened
	 * @param threshold Integer value to be shortened to
	 * @return LinkedHashMap of size(threshold)
	 */
	private LinkedHashMap<String,Integer> shortLinkMap(LinkedHashMap<String,Integer> hm, Integer threshold) {
		
		LinkedHashMap<String,Integer> shortMap = new LinkedHashMap<>();
		Set<Entry<String, Integer>> s = hm.entrySet();
		
		Iterator<Entry<String, Integer>> it = s.iterator();
		
		Map.Entry<String, Integer> entry = null;
		
		int i = 0;
		while (it.hasNext() && i <= threshold) {
			entry = it.next();
			shortMap.put(entry.getKey(), entry.getValue());
			i++;
		}
		return shortMap;
	}
	
	@Override
	public LinkedHashMap<String,Integer> getShortMap() {
		LinkedHashMap<String, Integer> shortMap = shortLinkMap(ss.getHm(), ss.getThreshold());
		return shortMap;
	}
	
	@Override
	public void setHashMap(LinkedHashMap<String,Integer> hm) {
		ss.setHm(hm);
	}
	
	@Override
	public void setThreshold(Integer th) {
		ss.setThreshold(th);
	}
}
