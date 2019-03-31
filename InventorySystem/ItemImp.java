/*
* @author David W. Clendenning Jr.
*/
package InventorySystem;

public class ItemImp implements Item{
    private String printerModel;
    private String brand;
    private String model;
    private int minStock;
    private int curStock;
	ItemImp(String printerModel, String brand, String model, int minStock, int curStock){
	    this.printerModel = printerModel;
	    this.brand = brand;
	    this.model = model;
	    this.minStock = minStock;
	    this.curStock = curStock;
	}
	
	ItemImp(){
		
	}
	
	@Override
	public boolean hasDeficit(Item i) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateStock(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDeficit(Item i) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getPrinterModel() {
		return printerModel;
	}

	public void setPrinterModel(String printerModel) {
		this.printerModel = printerModel;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getMinStock() {
		return minStock;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	public int getCurrentStock() {
		return curStock;
	}

	public void setCurrentStock(int curStock) {
		this.curStock = curStock;
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