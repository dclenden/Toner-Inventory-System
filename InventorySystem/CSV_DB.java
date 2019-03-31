package InventorySystem;

import java.awt.List;

public interface CSV_DB {
    public List getAllItems();
    public List getAllPrinters();
    public Item getItem(Item i);
    public Printer getPrinter(Printer p);
    public void updateItem(Item i);
    public void deleteItem(Item i);
    public void addItem(Item i);
}
