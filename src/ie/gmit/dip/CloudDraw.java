package ie.gmit.dip;

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.security.SecureRandom;
import java.util.*;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * @author Aiden Desmond <G00398273@gmit.ie>
 * 
 * Long Utility Class to functionally prepare the WordCloud Image.
 * 
 * Each method is separately documented to show the functions more clearly.
 *
 */
public class CloudDraw {


	/**
	 * @param hm		LinkedHashMap to be used to generate WordCloud
	 * @param saveName	Filename to save the generated image
	 * @return			boolean on success
	 * @throws Exception
	 */
	public boolean main(LinkedHashMap<String, Integer> hm, String saveName) throws Exception {
		BufferedImage finalImg = wordCloud(hm);
		try {
			ImageIO.write(finalImg, "png", new File(saveName));
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	/**
	 * wordCloud
	 * Prepares the contents of the wordcloud
	 * 
	 * @param hm	LinkedHashMap from which to generate a WordCloud Image
	 * @return		Returns a BufferedImage Object
	 * @throws IOException
	 * 
	 * There are two data access structures which have the following time-values:
	 * 1) hm.keySet() is accessing all Keys in a HashMap -> O(n)
	 * 2) for (String key : keys) traversing Set -> O(n)
	 */

	private BufferedImage wordCloud(LinkedHashMap<String, Integer> hm) throws IOException  {
		BufferedImage img = new BufferedImage(1400, 900, BufferedImage.TYPE_4BYTE_ABGR);
		Set<String> keys = hm.keySet();
		
		// The floor is lava!
		HashSet<Rectangle2D> lava = new HashSet<>(hm.size());
		
		Random rand = new SecureRandom();
		Font font = new Font(Font.SERIF, Font.BOLD, 20);

		for (String key : keys) {
			Graphics2D gd = img.createGraphics();
			int weight = hm.get(key);
			int size = 30 * weight;
			Font wordFont = font.deriveFont(Font.BOLD, size);
			FontMetrics fm = gd.getFontMetrics(wordFont);

			Rectangle2D box = fm.getStringBounds(key, gd);
			
			do {
				int x = rand.nextInt(img.getWidth() - (int) box.getWidth());
				int y = rand.nextInt(img.getHeight() - (int) box.getHeight());
				box.setFrame(x, y, box.getWidth(), box.getHeight());
				
			} while (checker(lava, box)); // is the proposed location lava?
			
			// add the new painted area to the Set of painted areas (lava!)
			lava.add(box);
			
			gd.setFont(wordFont);
			gd.setColor(Colors.getRandomColour());
			
			gd.drawString(key, (float) box.getX(), (float) box.getY() + fm.getAscent());
			gd.dispose();
		}
		
		return img;
	}

	/**
	 * @param lava 	HashSet of limiting values for the words already painted
	 * @param box	limiting values for the word currently being painted
	 * @return		true/false on if the proposed box intersects with existing value
	 * 
	 * I made the set of "painted" values a HashSet, hoping to use an O(1)
	 * search here. I couldn't get that to work with getStringBounds.intersects()
	 * so am stuck with O(n) as an ordinary traversal
	 * 
	 * Called the set of used values `lava` because the floor is lava!
	 */
	private boolean checker(HashSet<Rectangle2D> lava, Rectangle2D box) {
		boolean checker = false;
		for (Rectangle2D check : lava) {
			if (box.intersects(check)) {
				checker = true;
				break;
			}
		}
		return checker;
	}

}
