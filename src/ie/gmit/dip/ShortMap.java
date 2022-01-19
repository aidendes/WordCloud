package ie.gmit.dip;

import java.util.LinkedHashMap;

/**
 * @author Aiden Desmond <G00398273@gmit.ie>
 * 
 * Interface to reduce the size of a LinkedHashMap.
 * Requires implentations to allow the setting of the identity of the 
 * Initial LinkedHashMap, and the Bounds to reduce the Map to.
 * 
 * All methods are abstract.
 * 
 * Time complexity is dealt with in the concrete class.
 *
 */
public interface ShortMap {

	LinkedHashMap<String, Integer> getShortMap();

	void setHashMap(LinkedHashMap<String, Integer> hm);

	void setThreshold(Integer th);

}