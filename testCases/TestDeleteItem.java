package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import InventorySystem.CSV_DBIMP;
import InventorySystem.Item;
import InventorySystem.ItemImp;

class TestDeleteItem {

	@Test
	void test() {
		//fail("Not yet implemented");
		Item testItem = new ItemImp();
		CSV_DBIMP dao = new CSV_DBIMP();
		dao.addItem(testItem);
		dao.itemList[0] = testItem;
		dao.deleteItem(testItem);
		assertEquals(null, dao.itemList[0]);
	}

}
