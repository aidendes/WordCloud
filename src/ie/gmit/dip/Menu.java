package ie.gmit.dip;


import java.util.*;

/** 
 * This uses much of the ideas and concepts from my
 * ImageProcessor Menu at the end of Semester One, on 
 * the principle of "If it ain't broke..."
 * 
 * @author Aiden Desmond <G00398273@gmit.ie>
 *
 */
public class Menu {

	/**
	 * A private class holding the different values 
	 * chosen by the user from the options available.
	 * 
	 */
	
	private class settings {

		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public Integer getWordTreshold() {
			return wordTreshold;
		}
		public void setWordTreshold(Integer wordTreshold) {
			this.wordTreshold = wordTreshold;
		}
		public String getSaveName() {
			return saveName;
		}
		public void setSaveName(String saveName) {
			this.saveName = saveName;
		}
		
		public Boolean getIsUrl() {
			return isUrl;
		}
		public void setIsUrl(Boolean isUrl) {
			this.isUrl = isUrl;
		}

		private Boolean isUrl = false;
		String fileName = "";
		Integer wordTreshold = 30;
		String saveName = "";
	}
	
	// Instantiate the Utils class
	private Utils u = new Utils();
	private SubMenu sm = new SubMenu();
	
	// set up a scanner to acknowledge input
	private Scanner s;
	private boolean keepRunning = true;
	
	private settings g;
	
	// Fire Up the Scanner!
	public Menu() throws InterruptedException {
		s = new Scanner(System.in);
	}
	
	public void start() throws Exception {
		this.g = new settings();
		
		// set a variable to keep the menu alive until user exits
		while (keepRunning) {
			// This calls the menu the user sees on the screen
			showOptions();
			
			try {
				int choice = Integer.parseInt(s.next());
				// Each option from the menu needs a method to do something.
				switch (choice) {
				case 1 -> filePicker();
				case 2 -> urlPicker();
				case 3 -> thresholdPicker();
				case 4 -> savePicker();
				case 5 -> execute();
				case 6 -> shutDown();
				default -> throw new IllegalArgumentException("User Error: Value not in range: " + choice);
				}
			} catch (Exception e) {
				//u.warning("User Error: You must enter a number between 1 and 7");
			}
		}
	}



	private void showOptions() throws Exception {
		// Allows a cleaner presentation
		System.out.println();
		System.out.flush();
		
		filePickerMenu();
		urlPickerMenu();
		thresholdPickerMenu();
		savePickerMenu();
		System.out.println("\t5) Execute the Program....");
		System.out.println("\t6) Quit"); // Terminate
		System.out.print("\tSelect Option [1-6] -> ");

		System.out.print(ConsoleColour.GREEN);
		
	}
	
	
	
	private void savePickerMenu() {
		if (g.getSaveName().isEmpty()) {
			System.out.println("\t4) Enter Save File Name");
		} else {
			u.menuItem("4) Saving to " + g.getSaveName());
		}
		
	}

	private void filePickerMenu() {
		if (g.getFileName().isEmpty() || (g.getFileName().isEmpty() && g.getIsUrl())) {
			System.out.println("\t1) Enter File Path and Name");
		} else {
			// if the user has set a choice, then they are shown the filename.
			u.menuItem("1) File Selected:\t" + g.getFileName());
		}
		
	}

	private void urlPickerMenu() {
		if (g.getFileName().isEmpty() || (!g.getFileName().isEmpty() && !g.getIsUrl())) {
			System.out.println("\t2) Enter URL Path");
		} else {
			// if the user has set a choice, then they are shown the filename.
			u.menuItem("2) URL Selected:\t" + g.getFileName());
		}
		
	}

	private void thresholdPickerMenu() {
		if (g.getWordTreshold() == 30) {
			System.out.println("\t3) Choose Threshold (Default 30)");
		} else {
			u.menuItem("3) Threshold Chosen: " + g.getWordTreshold());
		}
		
	}

	private void filePicker() {
		try {
			g.setFileName(sm.fileChooser());
			g.setIsUrl(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void urlPicker() {
		g.setFileName(sm.urlChooser());
		g.setIsUrl(true);
	}
	
	private void thresholdPicker() {
		g.setWordTreshold(sm.threshold());
	}
	
	private void savePicker() {
		g.setSaveName(sm.savePicker());
	}
	
	
	/**
	 * Execute Function.
	 * 
	 * Sends all the collected data through to the Creator() class
	 * All complexity of this operation is dealt with in appropriate
	 * locations.
	 * 
	 * @throws Exception
	 */
	private void execute() throws Exception {
		if (g.getFileName().isEmpty() || g.getSaveName().isEmpty()) {
			u.warning("You must select something to parse and a file to save the wordcloud to!");
		} else {
			Creator cr = new Creator();
			cr.setFileName(g.fileName);
			cr.setIsUri(g.getIsUrl());
			cr.setSaveFile(g.getSaveName());
			cr.setWordCount(g.getWordTreshold());
			Boolean success = cr.main();
			if (success) {
				u.notice("WordCloud Saved to " + g.getSaveName());
				u.warning("Let's do another!");
				g = new settings();
			}
		}
		
	}

	private void shutDown() {
		u.warning("User has told us to shut down.");
		u.warning("Shutting down now...\nAiden Desmond - G00398273@gmit.ie");
		keepRunning = false;
	}
	
}
