package ie.gmit.dip;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Scanner;



/**
 * SubMenu
 * 
 * Classes which are subordinate to the Menu and permit interactivity.
 * None of these operations are time-complex.
 * 
 * @author aidenmac
 *
 */
public class SubMenu {

	private Scanner s = new Scanner(System.in);
	private Utils u = new Utils();


	public String fileChooser() throws Exception {
		
		boolean fileMenu = true;

		String[] filenames = null;
		String filePath = "";
		String filename = "";
		
		while (fileMenu) {
			boolean pathMenu = true;
			while (pathMenu) {
				String[] thisMenu = { "Please enter a path where your text file can be found.\n",
						"This should be a directory, not a path to a single file,",
						"and should be given relative to the program folder (e.g. ../),",
						"or an absolute system path (e.g. /home/user/ or D:\\folder\\)"
						};
				u.menuActive(thisMenu, "Path -> ");
				filePath = s.next();
				
				// Quick check for a file separator character
				if (!filePath.endsWith(File.separator)) {
					filePath += File.separator;
				}
				u.notice("\tYour text files are located at: " + filePath);
				
				try {
					// This process taken from
					// https://stackabuse.com/java-list-files-in-a-directory/

					File f = new File(filePath);

					FilenameFilter filter = new FilenameFilter() {
						@Override
						public boolean accept(File f, String name) {
							return name.endsWith(".txt");
						}
					};

					filenames = f.list(filter);
					if (filenames.length != 0) {
						u.notice("The following files are available:\n");
						//
						// This technically has O(n) complexity, depending
						// on the user's choice of file location.
						//
						for (String fname : filenames) {
							// Print the names of matching files
							System.out.println("\t" + fname);
							pathMenu = false;
						}
					} else {
						u.warning("[User Error] There are no text files at that location.");
					}
				} catch (Exception e) {
					u.warning("[User Error] Path is either wrong or not available.");
				}
			}

			// End of researched solution
			// Asks the user to type the name of the file they want to process
			System.out.print("\n\tPlease enter the name of your file "
							+ ConsoleColour.WHITE_BOLD + " -> "
							+ ConsoleColour.GREEN
							);

			// TRUST BUT VERIFY! OK, maybe don't trust the user at all...
			// get the user's choice, but check it exists!!
			String fileChoice = s.next();

			// Is the users selection in the list of files? If so, back to the main menu.
			// Again, technically 0(n) depending on the user's choice.
			if (Arrays.asList(filenames).contains(fileChoice)) {
				filename = filePath + fileChoice;
				fileMenu = false;
			} else {
				// naughty user must be censured.
				u.warning("[User Error] You have entered a filename which does not exist!");
			}

		}
		
		return filename;
		
	}

	public String urlChooser() {
		boolean urlMenu = true;
		String urlPath = "";
	
		while (urlMenu) {
			String[] urlMenuText = { "Please enter a path to your chosen url.\n",
					"The domain should be a fully qualified domain name with protocol,\n",
					"and the full address to the url resource should be specified.\n",
					"(e.g. https://example.com/page.html\n"
					};
			u.menuActive(urlMenuText, "Path -> ");
			urlPath = s.next();
			
			if (u.isValidUrl(urlPath)) {
				u.notice("You have successfully chosen " + urlPath);
				urlMenu = false;
			} else {
				u.warning("That's not an URL I can understand. Please choose again");
			}
			
		}
		
		return urlPath;

	}

	public Integer threshold() {
		boolean thresholdMenu = true;
		Integer thresholdSet = 30;
		
		while (thresholdMenu) {
			String[] thresholdMenuText = {"How many words should be shown?\n",
						"(Larger numbers are slower, default is 30)\n"
						};
			u.menuActive(thresholdMenuText, "Number -> ");
			thresholdSet = s.nextInt();
			
			u.notice("You have chosen to represent " + thresholdSet + " words.");
			thresholdMenu = false;
		}
		return thresholdSet;
	}
	

	public String savePicker() {
		boolean saveMenu = true;
		String saveChoice = null;
		while (saveMenu) {
			String[] saveMenuText = {"Please enter the name of the image to be saved?\n"};
			u.menuActive(saveMenuText, "Enter Name -> ");
		
			saveChoice = s.next();
			u.notice("Saving image to: " + saveChoice);
			saveMenu = false;
		}
		return saveChoice;

	}
	
}
