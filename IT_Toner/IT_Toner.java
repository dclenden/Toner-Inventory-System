//David W. Clendenning Jr. - CSC214 - Mini - Project
package IT_Toner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
public class IT_Toner{
    public static void main(String[] args) {
    	/* Creating container (TonerLevels (pair of values)) for the values in the CSV and creating a MAP to
    	 * allow data manipulation of toner levels 
    	 * Map holds String, ArrayList which in this case is: 
    	 * (Printer Name, (Black Toner Level, Color Toner Level))
    	 */
    	ArrayList<String> temp_info = new ArrayList<>();
    	Map<String, TonerLevels> PrinterData = new HashMap<>();
    	final Integer LOW_THRESHOLD = Integer.valueOf(10);
    	/* Creating buffered reader to then read CSV file, csvReader is automatically closed after operation is done */
    	try(BufferedReader csvReader = new BufferedReader(new FileReader("Wilmington_west_toner.csv"))) {
    		csvReader.readLine();
    		String line = "";
    		while((line = csvReader.readLine()) != null) {
    			String[] toner_Details = line.split(",");
    			
    			/*
    			 * index[0] = printer name
    			 * index[1] = IPv4Address
    			 * index[2] = LastComm
    			 * index[3] = SerialNum
    			 * index[4] = PageCount
    			 * index[5] = BlackCartridge
    			 * index[6] = ColorCartridge
    			 */
    			/* Logic to allow for splitting of csv file */
    			if(toner_Details.length > 0) {
    				int blackCartridge = Integer.parseInt(toner_Details[5]);
    				int colorCartridge = Integer.parseInt(toner_Details[6]);
    				temp_info.add(toner_Details[0]);
    				/* Assigns the printer name to both the colored and black toner levels with the use of
    				 * a map, using the put method to create this format for each instance of available 
    				 * printers in the CSV file
    				 */
    				PrinterData.put(toner_Details[0], new TonerLevels
    						    (new Integer(blackCartridge),new Integer(colorCartridge)));
    			}
    			/* Displays toner levels in terminal for operators to see */
    			System.out.println("PRINTER: " + toner_Details[0] + " [black, color] -> " 
    			                  + PrinterData.get(toner_Details[0]));
    		}
    		/* Creates a single string that holds all low toner printer information (lines [50 - 65])*/
    		StringBuilder warningMessage = new StringBuilder();
    		for(int i = 0; i < temp_info.size(); i++) {
    			if(PrinterData.get(temp_info.get(i)).getBlackToner() < LOW_THRESHOLD) {
    				warningMessage.append("Printer ");
    				warningMessage.append(temp_info.get(i));
    				warningMessage.append(" black cartridge must be refilled" + "(" 
    				    + PrinterData.get(temp_info.get(i)).getBlackToner() + "%)\n" );
    			}
    		}
    		
    		for(int i = 0; i < temp_info.size(); i++) {
    			if(PrinterData.get(temp_info.get(i)).getColorToner() < LOW_THRESHOLD) {
    				warningMessage.append("Printer ");
    				warningMessage.append(temp_info.get(i));
    				warningMessage.append(" color cartridge must be refilled " + "(" 
    				    + PrinterData.get(temp_info.get(i)).getColorToner() + "%)\n" );
    			}
    		}
    		/* Method using swing that creates a WARNING pop-up message alerting 
    		 * the operators to refill their toner 
    		 * */
			infoBox(warningMessage.toString() ,"TONER LEVEL LOW");

    	}
    	/* catches error if the file is not found on your computer */
        catch(FileNotFoundException e) {
    		System.out.println("File not found, please check your file system");
    	}
    	/* Prints errors if any */
    	catch(Exception ee) {
    		ee.printStackTrace();
    	}
    	/* Exits program after pop-up is closed */
    	finally {
    		System.exit(0);
    	}
    }
    /* Used to create a warning message for pop-up window to show low toner */
    public static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "" + titleBar, JOptionPane.WARNING_MESSAGE);
    }

}
