package InventorySystem;

import java.awt.List;

public interface ItemCSV_DB {
    public List getAllItems();
    public Item getItem(Item i);
    public void updateItem(Item i);
    public void deleteItem(Item i);
    public void addItem(Item i);
}
