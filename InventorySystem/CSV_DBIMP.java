/*
* @author David W. Clendenning Jr.
*/
package InventorySystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class CSV_DBIMP implements CSV_DB{
	public boolean needInput = false;
	static String printerFilePath = "test"; //= "printers.csv";
	static String itemFilePath = "test";
	public Printer[] printerList = new PrinterImp[457];
	public Item[] itemList = new ItemImp[62];
	public HashMap<Integer, Printer> printerAccess = new HashMap<>();
	public HashMap<String, Item> itemAccess = new HashMap<>();
	
	public static void main(String[] args) throws IOException{
		//storePrinterCSV();
		//printerFilePath = JOptionPane.showInputDialog("Please enter CSV File Name");
		//readPrinterCSV();
		//readItemCSV();
		CSV_DBIMP dao = new CSV_DBIMP();
		//dao.readPrinterCSV();
		//dao.addItem(new ItemImp("a", "b", "c", 0, 0));
		//System.out.println(dao.getAllItems());
	    //dao.deleteItem("c");
	    //System.out.println(dao.getAllItems());
		int rand = (int)(Math.random() * 62);
		Item testItem = new ItemImp();
		//dao.readPrinterCSV("waht");
		for(int i = 0; i < dao.itemList.length; i++) {
		    dao.addItem(testItem);
		}
		System.out.println(dao.getAllItems());
		dao.itemList[rand] = new ItemImp("a", "b", "c", 0, 0);
		Item tesItem = dao.getItem("c");
		System.out.println(rand + " " + tesItem);
	    
		//CSV_DBIMP dao1 = new CSV_DBIMP();
		//Item what = new ItemImp();
		//dao.itemList[0] = what;
		//System.out.println(dao.itemList[0]);
		//dao.deleteItem(what);
		///System.out.println(dao.itemList[0]);
		//dao.readPrinterCSV();
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
	
	public CSV_DBIMP() {
		
	}
	
	public void storePrinterCSV() {
		needInput = true;
		printerFilePath = JOptionPane.showInputDialog("Please enter printer CSV File Name");
	    if (printerFilePath == null) {
	        System.out.println("The user canceled");
	        System.exit(0);
	    }

	}
	public void storeItemCSV() {
		needInput = true;
		itemFilePath = JOptionPane.showInputDialog("Please enter item/toner CSV File Name");
	    if (itemFilePath == null) {
	        System.out.println("The user canceled");
	        System.exit(0);
	    }

	}
	
	public void storePrinterCSV(String filePath) {
	    printerFilePath = filePath;	
	}
	
	public void storeItemCSV(String filePath) {
	    itemFilePath = filePath;	
	}
	
	public void readPrinterCSV() {
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
		try
		{
			br = new BufferedReader(new FileReader(printerFilePath));
			String line = "";
			br.readLine();
			while ((line = br.readLine()) != null) 
			{
				String[] printerDetails = line.split(",");

				if(printerDetails.length > 0 && !printerDetails[0].isEmpty())
				{
					Printer tempPrinter = new PrinterImp(Integer.parseInt(printerDetails[0]), printerDetails[3], printerDetails[7]
							, printerDetails[5], printerDetails[1], printerDetails[2], printerDetails[4]);
                    printerAccess.put(Integer.parseInt(printerDetails[0]), tempPrinter);
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

				if(itemDetails.length > 0 && !itemDetails[0].isEmpty())
				{
					Item tempItem = new ItemImp(itemDetails[0], itemDetails[1], itemDetails[2]
							, Integer.parseInt(itemDetails[4]), Integer.parseInt(itemDetails[5]));
					itemAccess.put(itemDetails[2], tempItem);
					this.itemList[itemCount++] = tempItem;
					
				}
			}
		}

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
	/*public void readCSV(String csv) {
	BufferedReader br = null;
	File temp;
	if(csv.equals("printer")) {
		int printerCount = 0;
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
		try
		{
			br = new BufferedReader(new FileReader(printerFilePath));
			String line = "";
			br.readLine();
			while ((line = br.readLine()) != null) 
			{
				String[] printerDetails = line.split(",");

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
	else if(csv.equals("item")){
		int itemCount = 0;
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

				if(itemDetails.length > 0 && !itemDetails[0].isEmpty())
				{
					Item tempItem = new ItemImp(itemDetails[0], itemDetails[1], itemDetails[2]
							, Integer.parseInt(itemDetails[4]), Integer.parseInt(itemDetails[5]));
					
					this.itemList[itemCount++] = tempItem;
					
				}
			}
		}

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
}*/
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
	public Item getItem(String item) {
		Item foundItem = new ItemImp();
		for(Item i: this.itemList) {
			if(i != null) {
				if(i.getModel().equals(item)) {
			    	foundItem = i;
			    	break;
		    	}
		    }
			else {
				foundItem = null;
			}
	    }
		return foundItem;
		
	}
	@Override
	public Printer getPrinter(int printerAT) {
		Printer foundPrinter = new PrinterImp();
		for(Printer i: this.printerList) {
			if(i != null) {
				if(i.getAssetTag() == printerAT) {
					foundPrinter = i;
			    	break;
		    	}
		    }
			else {
				foundPrinter = null;
			}
	    }
		return foundPrinter;
	}
	public boolean isPrinterListEmpty() {
		boolean isEmpty = true;
		for(int i = 0; i < printerList.length; i++) {
			if (printerList[i] != null) {
				isEmpty = false;
			}
					
		}
		return isEmpty;
	}
	public boolean isItemListEmpty() {
		boolean isEmpty = true;
		for(int i = 0; i < itemList.length; i++) {
			if (itemList[i] != null) {
				isEmpty = false;
			}
					
		}
		return isEmpty;
	}
	@Override
	public void deleteItem(String j) {
		if(this.isItemListEmpty() == true) {
			throw new ArrayIndexOutOfBoundsException("The item list is empty");
		}
		for(int i = 0; i < this.itemList.length; i++) {
			if(this.itemList[i] != null) {
				if(this.itemList[i].getModel().equals(j)) {
					this.itemList[i] = null;
					break;
				}
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
