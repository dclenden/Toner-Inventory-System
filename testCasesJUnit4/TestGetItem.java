/*
* @author David W. Clendenning Jr.
*/
package testCasesJUnit4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import InventorySystem.CSV_DBIMP;
import InventorySystem.Item;
import InventorySystem.ItemImp;

public class TestGetItem {

	@Test
	public void testRandomIndexGetItem() {
		//fail("Not yet implemented");
		CSV_DBIMP dao = new CSV_DBIMP();
		int rand = (int)(Math.random() * dao.itemList.length);
		Item testItem = new ItemImp();
		for(int i = 0; i < dao.itemList.length; i++) {
		    dao.addItem(testItem);
		}
		//System.out.println(dao.getAllItems());
		Item expectedResult = new ItemImp("a", "b", "c", 0, 0);
		dao.itemList[rand] = expectedResult;
		//Item tesItem = dao.getItem("c");
		//System.out.println(rand + " " + tesItem);
		assertEquals(expectedResult, dao.getItem("c"));
		
	}
	@Test
	public void testWhenItemNotInList() {
		//fail("Not yet implemented");
		CSV_DBIMP dao = new CSV_DBIMP();
		//System.out.println(Arrays.toString(dao.itemList));
		assertEquals(null, dao.getItem("c"));
	}

}
