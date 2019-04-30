/*
* @author David W. Clendenning Jr.
*/
package InventorySystem;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PrinterImp implements Printer{
	private IntegerProperty assetTag;
	private SimpleStringProperty location;
	private SimpleStringProperty department;
	private SimpleStringProperty manufacturer;
	private SimpleStringProperty description;
	private SimpleStringProperty category;
	private SimpleStringProperty serialNumber;
	private ArrayList<String> compatibleToners;
	public static void main(String[] args) {
		//readPrinterCSV();
	}
    public PrinterImp(){
    	this.assetTag = new SimpleIntegerProperty(0);
		this.location = new SimpleStringProperty("");
		this.department = new SimpleStringProperty("");
		this.manufacturer = new SimpleStringProperty("");
		this.description = new SimpleStringProperty("");
		this.category = new SimpleStringProperty("");
		this.serialNumber = new SimpleStringProperty("");
    }
    public PrinterImp(Integer assetTag, String location, String department, String manufacturer, String description,
			String category, String serialNumber) {
		super();
		this.assetTag = new SimpleIntegerProperty(assetTag);
		this.location = new SimpleStringProperty(location);
		this.department = new SimpleStringProperty(department);
		this.manufacturer = new SimpleStringProperty(manufacturer);
		this.description = new SimpleStringProperty(description);
		this.category = new SimpleStringProperty(category);
		this.serialNumber = new SimpleStringProperty(serialNumber);
	}
	
    
    @Override
	public Integer getAssetTag() {
		return assetTag.get();
	}
	@Override
	public void setAssetTag(int assetTag) {
		this.assetTag = new SimpleIntegerProperty(assetTag);
	}
	@Override
	public String getLocation() {
		return location.get();
	}
	@Override
	public void setLocation(String location) {
		this.location = new SimpleStringProperty(location);
	}
	@Override
	public String getDepartment() {
		return department.get();
	}
	@Override
	public void setDepartment(String department) {
		this.department = new SimpleStringProperty(department);
	}
	@Override
	public String getManufacturer() {
		return manufacturer.get();
	}
	@Override
	public void setManufacturer(String manufacturer) {
		this.manufacturer = new SimpleStringProperty(manufacturer);
	}
	@Override
	public String getDescription() {
		return description.get();
	}
	@Override
	public void setDescription(String description) {
		this.description = new SimpleStringProperty(description);
	}
	@Override
	public String getCategory() {
		return category.get();
	}
	@Override
	public void setCategory(String category) {
		this.category = new SimpleStringProperty(category);
	}
	@Override
	public String getSerialNumber() {
		return serialNumber.get();
	}
	@Override
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = new SimpleStringProperty(serialNumber);
	}
	@Override
	public ArrayList<String> getCompatibleToners() {
		return this.compatibleToners;
	}
	public void setCompatibleToners(ArrayList<String> s) {
		this.compatibleToners = s;
	}
	@Override
	public String toString() {
		return "PrinterImp [assetTag=" + assetTag + ", location=" + location + ", department=" + department
				+ ", manufacturer=" + manufacturer + ", description=" + description + ", category=" + category
				+ ", serialNumber=" + serialNumber + ", compatibleToners=" + compatibleToners + "]\n";
	}
}
