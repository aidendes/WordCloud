package ie.gmit.dip;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author Aiden Desmond <G00398273@gmit.ie>
 *
 * Utility Class to bring all the elements together.
 * All variables are instantiated from the menu class
 * 
 * Time Complexity depends on the dependent functions
 */
public class Creator {

	private settings create;
	
	
	private class settings {
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public boolean isUrl() {
			return isUrl;
		}
		public void setUrl(boolean isUrl) {
			this.isUrl = isUrl;
		}
		public String getSaveName() {
			return saveName;
		}
		public void setSaveName(String saveName) {
			this.saveName = saveName;
		}
		public Integer getThreshold() {
			return threshold;
		}
		public void setThreshold(Integer threshold) {
			this.threshold = threshold;
		}
		public String fileName;
		public boolean isUrl;
		public String saveName;
		public Integer threshold;
	
	}
	
	public Creator() {
		create = new settings();
	}
	
	/**
	 * private method to create the WordCloud
	 * 
	 * @return boolean on success or failure
	 * @throws Exception
	 */
	private boolean CreateCloud() throws Exception {
		Utils u = new Utils();
		Parser wc;
		HashMap<String,Integer> hm;
		if (create.isUrl()) {
			wc = new UrlParser();
			hm = wc.wordCount(create.getFileName());
		} else {
			wc = new FileParser();
			hm = wc.wordCount(create.getFileName());
		}
		u.menuItem("Detected " + hm.size() + " words in total.");

		Sorter sort = new SorterImpl();
		sort.setHashToOrder(hm);
		LinkedHashMap<String,Integer> lhm = sort.sortByValue();
		
		ShortMap sMap = new ShortenMapImpl();
		sMap.setHashMap(lhm);
		sMap.setThreshold(create.getThreshold());
		LinkedHashMap<String,Integer> shm = sMap.getShortMap();
		u.menuItem("Shortened that to a more manageable " + create.getThreshold() + " words.");
		
		CalcMap cm = new CalcPercentMap();
		cm.setCalcMap(shm);
		LinkedHashMap<String,Integer> percentMap = cm.calcValues();
		
		CloudDraw cd = new CloudDraw();

		Boolean success = cd.main(percentMap, create.getSaveName());
		return success;
	}
	
	public boolean main() throws Exception {

		Boolean success = CreateCloud();
		if (success) {
			return true;
		}
		return false;
		
	}
	
	public void setFileName(String fileName) {
		create.setFileName(fileName);
	}
	
	public void setIsUri(boolean url) {
		create.setUrl(url);
	}
	
	public void setWordCount(Integer wc) {
		create.setThreshold(wc);
	}
	
	public void setSaveFile(String save) {
		create.setSaveName(save);
	}
	
	
}
