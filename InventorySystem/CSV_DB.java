package InventorySystem;

import java.util.ArrayList;

public interface CSV_DB {
    public ArrayList getAllItems();
    public ArrayList getAllPrinters();
    public Item getItem(Item i);
    public Printer getPrinter(Printer p);
    //public void updateItem(Item i);
    public void deleteItem(Item i);
    //public void addItem(Item i);
}
