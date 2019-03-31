/*
* @author David W. Clendenning Jr.
*/
package InventorySystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class CSV_DBIMP {
	static Printer[] printerList = new PrinterImp[500];
	static Item[] itemList = new ItemImp[500];
	static HashMap<String, Printer> printerMap = new HashMap<>();
	
	public static void main(String[] args) {
		readPrinterCSV();
		readItemCSV();
		System.out.println(Arrays.toString(printerList));
		//System.out.println(printerMap.toString());
		System.out.println(Arrays.toString(itemList));
		//System.out.println(printerMap.toString());
	}
	
	public static void readPrinterCSV() {
		// The following is adapted from Example 1. Using Buffered Reader and String.split() from https://www.javainterviewpoint.com/how-to-read-and-parse-csv-file-in-java/
		BufferedReader br = null;
		int printerCount = 0;
		try
		{
			br = new BufferedReader(new FileReader("printers.csv"));
			String line = "";
			br.readLine();
			while ((line = br.readLine()) != null) 
			{
				String[] printerDetails = line.split(",");
				// When the string is split, it will result in an array with the following information in the corresponding index
				// 0 - AssetTag
				// 1 - Description
				// 2 - Category Name
				// 3 - Location Name
				// 4 - Serial Number
				// 5 - Manufacturer
				// 6 - Division
				// 7 - Department
				// 8 - Campus
				// 9 - Status
				
				if(printerDetails.length > 0 && !printerDetails[0].isEmpty())
				{
					Printer tempPrinter = new PrinterImp(printerDetails[0], printerDetails[3], printerDetails[7]
							, printerDetails[5], printerDetails[1], printerDetails[2], printerDetails[4]);
                    printerMap.put(printerDetails[0], tempPrinter);
					printerList[printerCount++] = tempPrinter;
				}
			}
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
		finally
		{
			try
			{
				br.close();
			}
			catch(IOException ie)
			{
				System.out.println("Error occured while closing the BufferedReader");
				ie.printStackTrace();
			}
		}
	}
	
	public static void readItemCSV() {
		// The following is adapted from Example 1. Using Buffered Reader and String.split() from https://www.javainterviewpoint.com/how-to-read-and-parse-csv-file-in-java/
		BufferedReader br = null;
		int itemCount = 0;
		try
		{
			br = new BufferedReader(new FileReader("Wilmington Toner Database.csv"));
			String line = "";
			br.readLine();
			while ((line = br.readLine()) != null) 
			{
				String[] itemDetails = line.split(",");
				// When the string is split, it will result in an array with the following information in the corresponding index
				// 0 - PrinterModel
				// 1 - Brand
				// 2 - Model
				// 3 - Printers
				// 4 - Min Stock
				// 5 - Current Stock
				// 6 - Ordered?
				// 7 - Needed amount
				// 8 - Campus
				// 9 - Status
				
				if(itemDetails.length > 0 && !itemDetails[0].isEmpty())
				{
					Item tempItem = new ItemImp(itemDetails[0], itemDetails[1], itemDetails[2]
							, Integer.parseInt(itemDetails[4]), Integer.parseInt(itemDetails[5]));
					itemList[itemCount++] = tempItem;
				}
			}
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
		finally
		{
			try
			{
				br.close();
			}
			catch(IOException ie)
			{
				System.out.println("Error occured while closing the BufferedReader");
				ie.printStackTrace();
			}
		}
	}
}
