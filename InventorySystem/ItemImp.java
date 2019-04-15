/*
* @author David W. Clendenning Jr.
*/
package InventorySystem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ItemImp implements Item {
    private SimpleStringProperty printerModel;
    private SimpleStringProperty brand;
    private SimpleStringProperty model;
    private IntegerProperty minStock;
    private IntegerProperty curStock;
	public ItemImp(String printerModel, String brand, String model, int minStock, int curStock){
	    this.printerModel = new SimpleStringProperty(printerModel);
	    this.brand = new SimpleStringProperty(brand);
	    this.model = new SimpleStringProperty(model);
	    this.minStock = new SimpleIntegerProperty(minStock);
	    this.curStock = new SimpleIntegerProperty(curStock);
	}
	
	public ItemImp(){
		this.printerModel = new SimpleStringProperty("");
		this.brand = new SimpleStringProperty("");
		this.model = new SimpleStringProperty("");
		this.minStock = new SimpleIntegerProperty(0);
		this.curStock = new SimpleIntegerProperty(0);
	}
	
	@Override
	public boolean hasDeficit() { //returns if the items have a deficit or if they are in surplus of the min stock
        if(this.minStock.get() < 0 || this.curStock.get() < 0) {
        	throw new NumberFormatException();
        }
		boolean hasDeficit = (this.getCurrentStock() < this.getMinStock()) ? true : false;
		return hasDeficit;
	}
	
	/*
	 * removed the "UPDATESTOCK" due to the function being too similar to "getStock"
	 */

	@Override
	public int getDeficit() { //returns the amount of items that are needed to be bought to keep up with the minstock
		if(this.hasDeficit() == true) {
        	return Math.abs(this.curStock.get() - this.minStock.get());
        }
        else {
            return 0; // if returning 0 then there is no deficit	
        }
	}

	public String getPrinterModel() {
		return printerModel.get();
	}

	public void setPrinterModel(String printerModel) {
		this.printerModel = new SimpleStringProperty(printerModel);
	}

	public String getBrand() {
		return brand.get();
	}

	public void setBrand(String brand) {
		this.brand = new SimpleStringProperty(brand);
	}

	public String getModel() {
		return model.get();
	}

	public void setModel(String model) {
		this.model = new SimpleStringProperty(model);
	}

	public int getMinStock() {
		return minStock.get();
	}

	public void setMinStock(int minStock) {
		this.minStock = new SimpleIntegerProperty(minStock);
	}

	public int getCurrentStock() {
		return curStock.get();
	}

	public void setCurrentStock(int curStock) {
		this.curStock = new SimpleIntegerProperty(curStock);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "ItemImp [printerModel=" + printerModel + ", brand=" + brand + ", model=" + model + ", minStock="
				+ minStock + ", curStock=" + curStock + "]\n";
	}
}