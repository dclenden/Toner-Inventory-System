package InventorySystem;

public interface Item {
    public boolean hasDeficit();
    //public void updateStock(int num);
    public int getDeficit();
	public String getName();
	public void setName(String name);
	public String getBrand();
	public void setBrand(String brand);
	public int getMinStock();
	public void setMinStock(int minStock);
	public int getCurrentStock();
	public void setCurrentStock(int quantity);
	public String getModel();
	public void setModel(String model);
}
