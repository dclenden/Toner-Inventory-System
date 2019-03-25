/*
* @author David W. Clendenning Jr.
*/
package InventorySystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PrinterImp implements Printer{
	static HashMap<String, Printer> printerMap = new HashMap<>();
	private String assetTag;
	private String location;
	private String department;
	private String manufacturer;
	private String description;
	private String category;
	private String serialNumber;
	private ArrayList<String> compatibleToners;
	static Printer[] printerList = new PrinterImp[500];
	public static void main(String[] args) {
		readPrinterCSV();
		System.out.println(Arrays.toString(printerList));
		System.out.println(printerMap.toString());
	}
    public PrinterImp(){
    	
    }
    public PrinterImp(String assetTag, String location, String department, String manufacturer, String description,
			String category, String serialNumber) {
		super();
		this.assetTag = assetTag;
		this.location = location;
		this.department = department;
		this.manufacturer = manufacturer;
		this.description = description;
		this.category = category;
		this.serialNumber = serialNumber;
	}
	static void readPrinterCSV() {
		// The following is adapted from Example 1. Using Buffered Reader and String.split() from https://www.javainterviewpoint.com/how-to-read-and-parse-csv-file-in-java/
		BufferedReader br = null;
		int printerCount = 0;
		try
		{
			//Reading the csv file
			br = new BufferedReader(new FileReader("printers.csv"));

			//Create List for holding Pokemon objects
			//List<Pokemon> pokemonList = new ArrayList<Pokemon>();

			String line = "";
			//Read to skip the header
			br.readLine();
			//Reading from the second line
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
				
				if(printerDetails.length > 0 )
				{
					//Create a temporary pokemon
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
		// End of code adapted from Example 1. Using Buffered Reader and String.split() from https://www.javainterviewpoint.com/how-to-read-and-parse-csv-file-in-java/

	}
    
    @Override
	public String getAssetTag() {
		return assetTag;
	}
	@Override
	public void setAssetTag(String assetTag) {
		this.assetTag = assetTag;
	}
	@Override
	public String getLocation() {
		return location;
	}
	@Override
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String getDepartment() {
		return department;
	}
	@Override
	public void setDepartment(String department) {
		this.department = department;
	}
	@Override
	public String getManufacturer() {
		return manufacturer;
	}
	@Override
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	@Override
	public String getDescription() {
		return description;
	}
	@Override
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String getCategory() {
		return category;
	}
	@Override
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String getSerialNumber() {
		return serialNumber;
	}
	@Override
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	@Override
	public ArrayList<String> getCompatibleToners(Toner t) {
		return compatibleToners;
	}
	@Override
	public void setCompatibleToners(ArrayList<Toner> t) {
		this.compatibleToners = compatibleToners;
	}
	@Override
	public String toString() {
		return "PrinterImp [assetTag=" + assetTag + ", location=" + location + ", department=" + department
				+ ", manufacturer=" + manufacturer + ", description=" + description + ", category=" + category
				+ ", serialNumber=" + serialNumber + ", compatibleToners=" + compatibleToners + "]\n";
	}
}
