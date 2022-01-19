package ie.gmit.dip;

import java.awt.Color;
import java.security.SecureRandom;

/**
 * @author aidenmac
 *
 * Simple class returning a random Color value from the set of named
 * Color classes.
 *
 */

public class Colors {

	
	static Color[] colors = new Color[]
			{
			    Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN,
			    Color.CYAN, Color.PINK, Color.ORANGE, Color.MAGENTA,
			    Color.GRAY, Color.LIGHT_GRAY 
			};
	
	/**
	 * @return Random Color from a short array of valid named Color Codes
	 * 		   https://docs.oracle.com/javase/7/docs/api/java/awt/Color.html
	 */
	public static Color getRandomColour() {
		int rand = new SecureRandom().nextInt(colors.length);
		return colors[rand];
	}
	
}
