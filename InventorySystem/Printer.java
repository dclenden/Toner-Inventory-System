package InventorySystem;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;

public interface Printer {
    public Integer getAssetTag();
	public void setAssetTag(int a);
	public String getLocation();
	public void setLocation(String a);
	public String getDepartment();
	public void setDepartment(String a);
	public String getManufacturer();
	public void setManufacturer(String a);
	public String getDescription();
	public void setDescription(String a);
	public String getCategory();
	public void setCategory(String a);
	public String getSerialNumber();
	public void setSerialNumber(String a);
	public ArrayList<String> getCompatibleToners();
	void setCompatibleToners(ArrayList<String> s);
}