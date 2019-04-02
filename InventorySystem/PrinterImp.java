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
	private String assetTag;
	private String location;
	private String department;
	private String manufacturer;
	private String description;
	private String category;
	private String serialNumber;
	private ArrayList<String> compatibleToners;
	public static void main(String[] args) {
		//readPrinterCSV();
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
