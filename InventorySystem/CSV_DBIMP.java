/*
* @author David W. Clendenning Jr.
*/
package InventorySystem;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class CSV_DBIMP implements CSV_DB{
	static String printerFilePath = "test"; //= "printers.csv";
	static String itemFilePath = "test";
	public Printer[] printerList = new PrinterImp[457];
	public Item[] itemList = new ItemImp[61];
	static HashMap<String, Printer> printerMap = new HashMap<>();
	
	private Printer what;
	private Item what1;
	
	public static void main(String[] args) {
		//storePrinterCSV();
		//printerFilePath = JOptionPane.showInputDialog("Please enter CSV File Name");
		//readPrinterCSV();
		//readItemCSV();
		CSV_DBIMP dao = new CSV_DBIMP();
		CSV_DBIMP dao1 = new CSV_DBIMP();
		//Item what = new ItemImp();
		//dao.itemList[0] = what;
		//System.out.println(dao.itemList[0]);
		//dao.deleteItem(what);
		///System.out.println(dao.itemList[0]);
		dao.readPrinterCSV();
		System.out.println(Arrays.toString(dao1.printerList));
		System.out.println(Arrays.toString(dao.printerList));
		//dao.addItem(new ItemImp());
		//dao.getAllItems();
		//System.out.println(dao.getAllPrinters().toString());
		//System.out.println(dao.getAllItems().toString());
		//System.out.println(Arrays.toString(printerList));
		//System.out.println(printerFilePath);
		//System.out.println(printerMap.toString());
		//System.out.println(Arrays.toString(itemList));
		//System.out.println(printerMap.toString());
	}
	public CSV_DBIMP(){
		
	}
	public static void storePrinterCSV() {
		printerFilePath = JOptionPane.showInputDialog("Please enter printer CSV File Name");
	    if (printerFilePath == null) {
	        System.out.println("The user canceled");
	        System.exit(0);
	    }

	}
	public static void storeItemCSV() {
		itemFilePath = JOptionPane.showInputDialog("Please enter item/toner CSV File Name");
	    if (itemFilePath == null) {
	        System.out.println("The user canceled");
	        System.exit(0);
	    }

	}
	public void readPrinterCSV() {
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
					this.printerList[printerCount++] = tempPrinter;
				}
			}
		}
		
		catch(ArrayIndexOutOfBoundsException ee) {
			throw new ArrayIndexOutOfBoundsException("Incorrect file format");
		}
		catch(NumberFormatException e) {
			throw new NumberFormatException("Incorrect file format"); 
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
	
	public void readItemCSV() {
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
					
					this.itemList[itemCount++] = tempItem;
					
				}
			}
		}
		/*catch(ArrayIndexOutOfBoundsException | NumberFormatException ee) {
			System.out.println("Incorrect file format");
		}*/
		catch(ArrayIndexOutOfBoundsException e) {
			throw new ArrayIndexOutOfBoundsException("Incorrect file format");
		}
        catch(NumberFormatException e) {
        	throw new NumberFormatException("Incorrect file format");
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
	public ArrayList<Item> getAllItems() {
		ArrayList<Item> allItems = new ArrayList<Item>();
		for(Item a: itemList) {
			allItems.add(a);
		}
		return allItems;
	}
	@Override
	public ArrayList<Printer> getAllPrinters() {
		ArrayList<Printer> allPrinters = new ArrayList<Printer>();
		for(Printer a: printerList) {
			allPrinters.add(a);
		}
		return allPrinters;
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
	public void deleteItem(Item j) {
		for(int i = 0; i < itemList.length; i++) {
			if(itemList[i] == j) {
				itemList[i] = null;
			}
		}
	}
	//@Override
	public void addItem(Item i) {
        int j = 0;
		while(itemList[j] != null) {
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
