/*
* @author David W. Clendenning Jr.
*/
package InventorySystem;

public class TonerImp implements Toner{
	private String tonerModel;
	private String color;
	private String printerModel;
	private String brand;
	private String model;
	private int minStock;
	private int curStock;
	
	public String getTonerModel() {
		return tonerModel;
	}
	public void setTonerModel(String tonerModel) {
		this.tonerModel = tonerModel;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
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
	public boolean hasDeficit() { //returns if the items have a deficit or if they are in surplus of the min stock
		boolean hasDeficit = (this.getCurrentStock() < this.getMinStock()) ? true : false;
		return hasDeficit;
	}
	
	/*
	 * removed the "UPDATESTOCK" due to the function being too similar to "getStock"
	 */

	@Override
	public int getDeficit() { //returns the amount of items that are needed to be bought to keep up with the minstock
        if(this.hasDeficit() == true) {
        	return Math.abs(this.curStock - this.minStock);
        }
        else {
            return 0; // if returning 0 then there is no deficit	
        }
	}
	
	public void setOnOrder(boolean b) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setQuantityPrinters(int parseInt) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getQuantityPrinters() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setOnOrder(String b) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getOnOrder() {
		// TODO Auto-generated method stub
		return null;
	}
}
