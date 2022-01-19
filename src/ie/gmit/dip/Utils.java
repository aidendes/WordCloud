package ie.gmit.dip;

import java.util.concurrent.TimeUnit;



/**
 * Utils class
 * 
 * These are simple code snippets to handle presentation of 
 * menu and information elements.
 * 
 * 
 * @author aidenmac
 *
 */
public class Utils {

	/**
	 * Simple validator for a given URL address, without libraries.
	 * 
	 * @param url String containing URL to be checked
	 * @return boolean
	 */
	public Boolean isValidUrl(String url) {
		try {
			(new java.net.URL(url)).openStream().close();
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * Menu Cleaner Method Sets some colours and spacing for a standard notice
	 * 
	 * Note the use of a 1-second delay. Acceptable for menu use only.
	 *
	 * @param notice String to display on screen
	 */
	public void notice(String notice) {
		try {
			System.out.println();
			System.out.println("\t" + ConsoleColour.GREEN + notice + ConsoleColour.RESET);
			TimeUnit.SECONDS.sleep(1);
			System.out.print("\033[H\033[2J");
			System.out.flush();
		} catch (InterruptedException e) {
			System.out.println("Got Interrupted!");
			e.printStackTrace();
		}
	}

	/**
	 * Menu Cleaner Method Prints a warning text in bright red on screen
	 * 
	 * Note the use of a 2-second delay. Acceptable for menu use only.
	 * 
	 * @param warning Text of the warning to send the user
	 */
	public void warning(String warning) {
		try {
			System.out.println(ConsoleColour.RED_BOLD_BRIGHT);
			System.out.println("\t" + warning);
			System.out.println(ConsoleColour.RESET);
			TimeUnit.SECONDS.sleep(2);
			System.out.print("\033[H\033[2J");
			System.out.flush();
		} catch (InterruptedException e) {
			System.out.println("Got Interrupted!");
			e.printStackTrace();
		}
	}

	/**
	 * Menu Cleaner Method Shows the user a menu and a place to enter a choice
	 * 
	 * @param menu   Array of strings to display to the user, one per line
	 * @param choice String to indicate the program awaits user input
	 */
	public void menuActive(String[] menu, String choice) {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		for (String string : menu) {
			System.out.println(ConsoleColour.RESET + "\t" + ConsoleColour.GREEN + string);
		}
		System.out.print("\t" + choice + ConsoleColour.RESET);

	}

	/**
	 * Menu Cleaner Method Displays the given string in Bright Cyan
	 * 
	 * @param item Text to display
	 */
	public void menuItem(String item) {
		System.out.print(ConsoleColour.CYAN_BRIGHT);
		System.out.print("\t" + item);
		System.out.println(ConsoleColour.RESET);
	}

}
