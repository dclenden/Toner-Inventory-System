package testCasesJUnit4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import InventorySystem.CSV_DBIMP;
import InventorySystem.Item;
import InventorySystem.ItemImp;

public class TestAddItem {
    @Test
	public void testAddItem() {
		//fail("Not yet implemented");
		CSV_DBIMP dao = new CSV_DBIMP(); // fill the array itemList array to the max point, then try to add
        // an item so that it will throw an ArrayIndexOutOfBounds Exception
		Item test1 = new ItemImp();
		dao.addItem(test1);
		assertEquals(test1, dao.itemList[0]); // confirms that the list within the dao is indeed filled with 
	}                                         // the test object
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testAddItemWhenFull() {
		//fail("Not yet implemented");
		CSV_DBIMP dao = new CSV_DBIMP(); // fill the array itemList array to the max point, then try to add
		                                 // an item so that it will throw an ArrayIndexOutOfBounds Exception
		for(int i = 0; i < dao.itemList.length+1; i++) {
		    dao.addItem(new ItemImp());
		}

	}

}
