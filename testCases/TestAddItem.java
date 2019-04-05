package testCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import InventorySystem.ItemImp;
import InventorySystem.CSV_DBIMP;
import InventorySystem.Item;

class TestAddItem {

	@Test
	void testAddItem() {
		//fail("Not yet implemented");
		CSV_DBIMP dao = new CSV_DBIMP(); // fill the array itemList array to the max point, then try to add
        // an item so that it will throw an ArrayIndexOutOfBounds Exception
		Item test1 = new ItemImp();
		dao.addItem(test1);
		assertEquals(test1, dao.itemList[0]); // confirms that the list within the dao is indeed filled with 
	}                                         // the test object
	
	@Test
	void testAddItemWhenFull() {
		//fail("Not yet implemented");
		CSV_DBIMP dao = new CSV_DBIMP(); // fill the array itemList array to the max point, then try to add
		                                 // an item so that it will throw an ArrayIndexOutOfBounds Exception
		for(int i = 0; i < dao.itemList.length; i++) {
		    dao.addItem(new ItemImp());
		}
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> dao.addItem(new ItemImp()));
	}

}
