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
	public String printerFilePath = "test"; //= "printers.csv";
	public String itemFilePath = "test";
	public final String crossWalk = "crosswalk.csv";
//	static String updatePrinterFilePath = "";
//	static String updateItemFilePath = "";
	public Printer[] printerList = new PrinterImp[457];//457
	public Item[] itemList = new ItemImp[62];//62 or 61
	public HashMap<Integer, Printer> printerAccess = new HashMap<>();
	public HashMap<String, Item> itemAccess = new HashMap<>();
	//main method was created for development testing
	public static void main(String[] args) throws IOException{
		CSV_DBIMP dao = new CSV_DBIMP();
		dao.storePrinterCSV("printers.csv");
		dao.storeItemCSV("Wilmington Toner DatabaseORIGINAL.csv");
		//dao.readPrinterCSV();
		dao.readItemCSV();
		//dao.readCrossWalk();
		for(Printer p : dao.printerList) {
			//System.out.println(p);
			//System.out.println(p.getCompatibleToners());
		}
		System.out.println(dao.orderList());
//		dao.storePrinterCSV("printers.csv");
//		dao.readPrinterCSV();
//		dao.storeItemCSV("Wilmington Toner Database.csv");
//		dao.readItemCSV();
//		dao.updateCSVs();
//		//storePrinterCSV();
//		//printerFilePath = JOptionPane.showInputDialog("Please enter CSV File Name");
//		//readPrinterCSV();
//		//readItemCSV();
//		CSV_DBIMP dao = new CSV_DBIMP();
//		//dao.readPrinterCSV();
//		//dao.addItem(new ItemImp("a", "b", "c", 0, 0));
//		//System.out.println(dao.getAllItems());
//	    //dao.deleteItem("c");
//	    //System.out.println(dao.getAllItems());
//		int rand = (int)(Math.random() * 62);
//		Item testItem = new ItemImp();
//		//dao.readPrinterCSV("waht");
//		for(int i = 0; i < dao.itemList.length; i++) {
//		    dao.addItem(testItem);
//		}
//		System.out.println(dao.getAllItems());
//		dao.itemList[rand] = new ItemImp("a", "b", "c", 0, 0);
//		Item tesItem = dao.getItem("c");
//		System.out.println(rand + " " + tesItem);
//	    
//		//CSV_DBIMP dao1 = new CSV_DBIMP();
//		//Item what = new ItemImp();
//		//dao.itemList[0] = what;
//		//System.out.println(dao.itemList[0]);
//		//dao.deleteItem(what);
//		///System.out.println(dao.itemList[0]);
//		//dao.readPrinterCSV();
//		//dao.addItem(new ItemImp());
//		//dao.getAllItems();
//		//System.out.println(dao.getAllPrinters().toString());
//		//System.out.println(dao.getAllItems().toString());
//		//System.out.println(Arrays.toString(printerList));
//		//System.out.println(printerFilePath);
//		//System.out.println(printerMap.toString());
//		//System.out.println(Arrays.toString(itemList));
//		//System.out.println(printerMap.toString());
	}
	
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
	
	// added method to read crosswalk between toners and printers, (currently throws error but doesn't prevent data from being
	// read, this error should be fixed when possible (low priority)
	public void readCrossWalk() {
		BufferedReader br = null;
		int printerCount = 0;
		int start = 0;
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
	//reads items from CSV and assigns them to array associated with that type of object
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
	//may use this method in order to add more abstraction to the code base, but have not fully decided yet
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
//		File printerFile = new File(printerFilePath);
//		File itemFile = new File(itemFilePath);
//		if(printerFile.exists()) {
//				FileInputStream printerinstream = null;
//				FileOutputStream printeroutstream = null;
//			 
//		    	try{
//		    		File printeroutfile = new File(printerFilePath);//null;
//		    		
//		    		printerinstream = new FileInputStream(printerFile);
//		    		printeroutstream = new FileOutputStream(printeroutfile);
//		 
//		    	    byte[] printerbuffer = new byte[1024];
//		    	    int length;
//		    	    /*copying the contents from input stream to
//		    	     * output stream using read and write methods
//		    	     */
//		    	    while ((length = printerinstream.read(printerbuffer)) > 0){
//		    	    	printeroutstream.write(printerbuffer, 0, length);
//		    	    }
//	
//		    	    //Closing the input/output file streams
//		    	    printerinstream.close();
//		    	    printeroutstream.close();
//		    	    System.out.println("CSV replaced");
//	
//			 
//		    	}
//		    	catch(IOException ioe){
//		    		ioe.printStackTrace();
//		    	}
//		}
//		
//		if(itemFile.exists()) {
//			FileInputStream iteminstream = null;
//			FileOutputStream itemoutstream = null;
//		 
//	    	try{
//	    		File itemoutfile = new File(itemFilePath);
//	    		iteminstream = new FileInputStream(itemFile);
//	    		itemoutstream = new FileOutputStream(itemoutfile);
//	 
//	    	    byte[] itembuffer = new byte[1024];
//	    	    int length;
//	    	    /*copying the contents from input stream to
//	    	     * output stream using read and write methods
//	    	     */
//	    	    while ((length = iteminstream.read(itembuffer)) > 0){
//	    	    	itemoutstream.write(itembuffer, 0, length);
//	    	    }
//	
//	    	    //Closing the input/output file streams
//	    	    iteminstream.close();
//	    	    itemoutstream.close();
//	
//	    	    System.out.println("CSV replaced");
//		 
//	    	}
//	    	catch(IOException ioe){
//	    		ioe.printStackTrace();
//	    	}
//		 }
//		else {
//			 System.out.println("Verification error");
//			 return;
//		}
	}
			
	
}
}
