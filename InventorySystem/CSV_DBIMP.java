/*
* @author David W. Clendenning Jr.
*/
package InventorySystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

public class CSV_DBIMP implements CSV_DB{
	public boolean needInput = false;
	public String printerFilePath = "printers.csv"; //= "printers.csv";
	public String itemFilePath = "Wilmington Toner Database.csv";
	public final String crossWalk = "crosswalk.csv";
	public Printer[] printerList = new PrinterImp[457];//457
	public Item[] itemList = new ItemImp[62];//62 or 61
	public HashMap<Integer, Printer> printerAccess = new HashMap<>();
	public HashMap<String, Item> itemAccess = new HashMap<>();
	
	public CSV_DBIMP() {
		
	}
	//popup window to enter CSV file (this may get scrapped)
	public void storePrinterCSV() {
		needInput = true;
		printerFilePath = JOptionPane.showInputDialog("Please enter printer CSV File Name");
	    if (printerFilePath == null) {
	        System.out.println("The user canceled");
	        System.exit(0);
	    }

	}
	//popup window to enter CSV file (this may get scrapped)
	public void storeItemCSV() {
		needInput = true;
		itemFilePath = JOptionPane.showInputDialog("Please enter item/toner CSV File Name");
	    if (itemFilePath == null) {
	        System.out.println("The user canceled");
	        System.exit(0);
	    }

	}
	//if we decide to have a seperate updated file to write to -> most likely going to be scrapped
//	public void storeupdatePrinterCSV(String filePath) {
//	    updatePrinterFilePath = filePath;	
//	}
//	
//	public void storeupdateItemCSV(String filePath) {
//	    updateItemFilePath = filePath;	
//	}
	public void storePrinterCSV(String filePath) {
	    printerFilePath = filePath;	
	}
	
	public void storeItemCSV(String filePath) {
	    itemFilePath = filePath;	
	}
	//reads printers from CSV and assigns them to array associated with that type of object
	
	//nothing is done with this data per requirements but
	public void readCrossWalk() {
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(crossWalk));
			String line = "";
			br.readLine();
		
			while ((line = br.readLine()) != null) 
			{
				List<String> tokensList = new ArrayList<String>();
				boolean inQuotes = false;
				StringBuilder b = new StringBuilder();
				for (char c : line.toCharArray()) {
				    switch (c) {
				    case ',':
				        if (inQuotes) {
				            b.append(c);
				        } else {
				            tokensList.add(b.toString());
				            b = new StringBuilder();
				        }
				        break;
				    case '\"':
				        inQuotes = !inQuotes;
				    default:
				        b.append(c);
				    break;
				    }
				}
				tokensList.add(b.toString());
				
				//String[] printerDetails = line.split(",");
				//System.out.println(tokensList);

				if(tokensList.size() > 0 && !tokensList.get(0).isEmpty())
				{
					//no quote token
					String nqToken = tokensList.get(4).replaceAll("^\"|\"$", "");
					//System.out.println(nqToken);
					//System.out.println(tokensList.get(4));
					String[] tokens = nqToken.split("\\s*,\\s*");
					//System.out.println(tokens);
					for(Printer p : this.printerList) {
						if(p.getDescription().equals(tokensList.get(1))){
							ArrayList<String> infoHolder = new ArrayList<>();
							for(String info : tokens) {
								infoHolder.add(info);
							}
							p.setCompatibleToners(infoHolder);
						}
			        }
			    }
			}
		    }
		
		
		catch(ArrayIndexOutOfBoundsException ee) {
			ee.printStackTrace();
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
	public void readPrinterCSV() {
		BufferedReader br = null;
		int printerCount = 0;
		//File temp;
	      
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
	//reads items from CSV and assigns them to array associated with that type of object
	public void readItemCSV() {
		BufferedReader br = null;
		int itemCount = 0;
	      
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
							, Integer.parseInt(itemDetails[4]), Integer.parseInt(itemDetails[5])
							,itemDetails[6],Integer.parseInt(itemDetails[7]),Integer.parseInt(itemDetails[3]));
					//tempItem.setQuantityPrinters(Integer.parseInt(itemDetails[3]));
					//tempItem.setOnOrder(itemDetails[6]);
					
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
	//returns item if it exists by searching for the model number
	@Override
	public Item getItem(String model) {
		Item foundItem = new ItemImp();
		for(Item i: this.itemList) {
			if(i != null) {
				if(i.getModel().equals(model)) {
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
	public boolean containsPrinter(Printer p) {
		boolean containsPrinter = false;
		for(Printer printer: printerList) {
			if(p.equals(printer)) {
				containsPrinter = true;
			}
			else {
				containsPrinter = false;
			}
		}
		return containsPrinter;
	}
	//returns printer if it exists by searching for the printers asset tag
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
	//deletes item based on model number/tag
	@Override
	public void deleteItem(String model) {
		if(this.isItemListEmpty() == true) {
			throw new ArrayIndexOutOfBoundsException("The item list is empty");
		}
		for(int i = 0; i < this.itemList.length; i++) {
			if(this.itemList[i] != null) {
				if(this.itemList[i].getModel().equals(model)) {
					this.itemList[i] = null;
					break;
				}
			}
		}
	}
	
	// returns list of items to be ordered
	public List<Item> orderList() {
		List<Item> toOrder = new ArrayList<>();
		for(Item item : this.itemList) {
			if(item != null) {
				if(item.hasDeficit()) {
					toOrder.add(item);
				}
			}
		}
		return toOrder;
	}
	
	//adds item based off of a new item creation, and assigns it to the next null value within the list
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
	public void updateCSVs() throws IOException {
	   ArrayList<Item> iList = new ArrayList<>();
	   for(int i = 0; i < itemList.length; i++) {
		   iList.add(itemList[i]);
	   }
	   File f = new File(itemFilePath);
	   if(f.exists()) {
		   f.delete();
		   try {
			   f.createNewFile();
		   }
		   catch(IOException e) {
			   e.printStackTrace();
		   }
		try (FileWriter writer = new FileWriter(f);
		             BufferedWriter bw = new BufferedWriter(writer)) {
        	StringBuilder sb = new StringBuilder();
	        Iterator<Item> it = iList.iterator();
	        //appends header to csv file
	        sb.append("PrinterModel" + "," + "Brand" + "," + "Model" + "," + "Printers" + "," 
	        + "MinStock" + "," + "CurStock" + "," + "Order" + "," + "Needed" +"\n");
	        while(it.hasNext()) {
	        	
	            Item i = (Item)it.next(); //BUG: issue where item would be null within dao itemList -> FIXED: 4/22/2019
	            if(i != null) {
		            sb.append(i.getPrinterModel().toString());
		            sb.append(",");
		            sb.append(i.getBrand().toString());
		            sb.append(",");
		            sb.append(i.getModel().toString());
		            sb.append(",");
		            sb.append(String.valueOf(i.getQuantityPrinters()));
		            sb.append(",");
		            sb.append(String.valueOf(i.getMinStock()));
		            sb.append(",");
		            sb.append(String.valueOf(i.getCurrentStock()));
		            sb.append(",");
		            sb.append(i.getOnOrder());
		            sb.append(",");
		            sb.append(String.valueOf(i.getDeficit()));
		            sb.append("\n");
	            }
	        }
	        bw.write(sb.toString());
	    } catch (Exception ex) {
	        ex.printStackTrace();
	      }
	   }
			
	
    }
}
