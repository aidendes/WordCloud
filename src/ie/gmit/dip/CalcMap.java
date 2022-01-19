package ie.gmit.dip;

import java.util.LinkedHashMap;

/**
 * 
 * @author Aiden Desmond <G00398273@gmit.ie>
 * 
 * Interface CalcMap is used to remap the Integer Values in a LinkedHashMap
 * to some variant of those values which may be required. In the case of 
 * this project, the interface is used to calculate the percentage, or 
 * <i>weight</i> of each Key compared to the other Keys to be used.
 *  
 * These are used for the purpose of establishing the 
 * <i>size</i> of a Key/Word when populating the WordCloud.
 * 
 * As this is an <i>interface</i> all methods are purely abstract.
 *
 */
public interface CalcMap {

	/**
	 * calcValues is not parameterized allowing a consistent abstracted
	 * approach.
	 * 
	 * @return LinkedHashMap with altered Integer Values.
	 */
	public abstract LinkedHashMap<String, Integer> calcValues();

	
	/**
	 * setCalcMap requires an implementation to allow the initial
	 * unaltered LinkedHashMap to be set externally, allowing a 
	 * non-parameterised main method.
	 * 
	 * @param hm Initial LinkedHashMap with unaltered Integer Values
	 */
	public abstract void setCalcMap(LinkedHashMap<String, Integer> hm);

}