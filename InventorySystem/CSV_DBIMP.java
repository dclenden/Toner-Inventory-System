/*
* @author David W. Clendenning Jr.
*/
package InventorySystem;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class CSV_DBIMP implements CSV_DB{
	static String printerFilePath = "test"; //= "printers.csv";
	static String itemFilePath = "test";
	static Printer[] printerList = new PrinterImp[457];
	static Item[] itemList = new ItemImp[61];
	static HashMap<String, Printer> printerMap = new HashMap<>();
	
	private Printer what;
	private Item what1;
	
	public static void main(String[] args) {
		//storePrinterCSV();
		//printerFilePath = JOptionPane.showInputDialog("Please enter CSV File Name");
		//readPrinterCSV();
		//readItemCSV();
		//System.out.println(Arrays.toString(printerList));
		//System.out.println(printerFilePath);
		//System.out.println(printerMap.toString());
		for(int i = 0; i < 61; i++) {
			addItem(new ItemImp());
		}
		System.out.println(itemList[0]);
		//System.out.println(Arrays.toString(itemList));
		//System.out.println(printerMap.toString());
	}
	public CSV_DBIMP(){
		
	}
	public static void storePrinterCSV() {
		printerFilePath = JOptionPane.showInputDialog("Please enter CSV File Name");
	    if (printerFilePath == null) {
	        System.out.println("The user canceled");
	        System.exit(0);
	    }

	}
	public static void storeItemCSV() {
		itemFilePath = JOptionPane.showInputDialog("Please enter CSV File Name");
	    if (itemFilePath == null) {
	        System.out.println("The user canceled");
	        System.exit(0);
	    }

	}
	public static void readPrinterCSV() {
		// The following is adapted from Example 1. Using Buffered Reader and String.split() from https://www.javainterviewpoint.com/how-to-read-and-parse-csv-file-in-java/
		BufferedReader br = null;
		int printerCount = 0;
		File temp;
	      try
	      {
	         temp = new File(printerFilePath);
	         if(!temp.exists()) {
		         while(!temp.exists()) {
			 	    temp.delete();
		        	storePrinterCSV();
		            temp = new File(printerFilePath);
		        	
		 		 }
	         }
	         
	      }
	      catch (Exception e) {
	         e.printStackTrace();
	      }
		//storePrinterCSV();
		try
		{
			br = new BufferedReader(new FileReader(printerFilePath));
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
		
		catch(ArrayIndexOutOfBoundsException | NumberFormatException ee) {
			System.out.println("Incorrect file format");
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
		File temp;
	      try
	      {
	         temp = new File(itemFilePath);
	         if(!temp.exists()) {
		         while(!temp.exists()) {
			 	    temp.delete();
		        	storeItemCSV();
		            temp = new File(itemFilePath);
		        	
		 		 }
	         }
	      }
	      catch(Exception ee) {
	    	  ee.printStackTrace();
	      }
		try
		{
			br = new BufferedReader(new FileReader(itemFilePath));
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
		catch(ArrayIndexOutOfBoundsException | NumberFormatException ee) {
			System.out.println("Incorrect file format");
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
	@Override
	public List getAllItems() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List getAllPrinters() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Item getItem(Item i) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Printer getPrinter(Printer p) {
		return p;
	}
	@Override
	public void deleteItem(Item i) {
		// TODO Auto-generated method stub
		
	}
	//@Override
	public static void addItem(Item i) {
        Item a = itemList[0];
        int j = 0;
		while(a != null) {
        	j++;
        }
		try {
			itemList[j] = i;
		}
		catch(Exception ee) {
			ee.printStackTrace();
		}
	}
}
