package InventorySystem;

public interface Item {
    public boolean hasDeficit(Item i);
    public void updateStock(int num);
    public int getDeficit(Item i);
	public String getName();
	public void setName(String name);
	public String getBrand();
	public void setBrand(String brand);
	public int getMinStock();
	public void setMinStock(int minStock);
	public int getCurrentStock();
	public void setCurrentStock(int quantity);
}
