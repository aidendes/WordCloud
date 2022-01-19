package ie.gmit.dip;

/**
 * 
 * @author Aiden Desmond
 *
 */
public class Runner {

	public static void main(String[] args) throws Exception {
		// Title goes here, as it only runs once.
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println(ConsoleColour.BLUE_BRIGHT);
		System.out.println("\t***************************************************");
		System.out.println("\t* GMIT - Dept. Computer Science & Applied Physics *");
		System.out.println("\t*                                                 *");
		System.out.println("\t*            Word Cloud Generator v.0.3           *");
		System.out.println("\t*     H.Dip in Science (Software Development)     *");
		System.out.println("\t*                                                 *");
		System.out.println("\t*        Aiden Desmond - G00398273@gmit.ie        *");
		System.out.println("\t*                                                 *");
		System.out.println("\t***************************************************");
		System.out.println(ConsoleColour.RESET);
		//sets up and calls the Menu. We're done here ;)
		Menu m = new Menu();
		m.start();

	}

}
