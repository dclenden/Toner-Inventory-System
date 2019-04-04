/*
* @author David W. Clendenning Jr.
*/
package testCases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import InventorySystem.CSV_DBIMP;
import InventorySystem.Item;

class TestGetAllItems {

	@Test
	void testGetAllItems() {
		//fail("Not yet implemented");
		CSV_DBIMP dao = new CSV_DBIMP();
		ArrayList<Item> allItems = new ArrayList<>();
		for(Item i: dao.itemList) {
			allItems.add(i);
		} //passes test case because all items within the printerList are added to the daoPrinterList due to getAllPrinters()
		assertEquals(allItems, dao.getAllItems());
	}
	@Test
	void testGetAllItemsWithWrongData() {
		//fail("Not yet implemented");
		CSV_DBIMP dao = new CSV_DBIMP();
		ArrayList<Item> allItems = new ArrayList<>();
		for(Item i: dao.itemList) {
			allItems.add(i);
		}
		int rand = (int)(Math.random() * dao.itemList.length); //random item being removed
		allItems.remove(rand);
		assertNotEquals(allItems, dao.getAllPrinters());
	}

}
