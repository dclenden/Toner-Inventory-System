package testCasesJUnit4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import InventorySystem.CSV_DBIMP;
import InventorySystem.Item;
import InventorySystem.ItemImp;

public class TestDeleteItem {

	@Test
	public void testWhenItemIsIndexFirstItem() {
		//fail("Not yet implemented");
		Item testItem = new ItemImp("printerModel", "brand", "HX756", 0, 0);
		CSV_DBIMP dao = new CSV_DBIMP();
		dao.addItem(testItem);
		dao.deleteItem("HX756");
		assertEquals(null, dao.itemList[0]);
	}
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testWhenItemListIsEmpty() {
		//fail("Not yet implemented");
		CSV_DBIMP dao = new CSV_DBIMP();
		dao.deleteItem("failDueToEmptyList");
	}
	@Test
	public void testWhenItemIsInRandomIndex() {
		//fail("Not yet implemented");
		int rand = (int)(Math.random() * 62);
		Item testItem = new ItemImp();
		CSV_DBIMP dao = new CSV_DBIMP();
		for(int i = 0; i < dao.itemList.length; i++) {
		    dao.addItem(testItem);
		}
		dao.itemList[rand] = new ItemImp("a", "b", "c", 0, 0);
		dao.deleteItem("c");
		assertEquals(null, dao.itemList[rand]);
	}
	
}
