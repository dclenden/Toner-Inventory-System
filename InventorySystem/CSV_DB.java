package InventorySystem;

import java.util.ArrayList;

public interface CSV_DB {
    public ArrayList getAllItems();
    public ArrayList getAllPrinters();
    public Item getItem(String item);
    public Printer getPrinter(int printerAT);
    //public void updateItem(Item i);
    //public void addItem(Item i);
	public void deleteItem(String itemModel);
}
