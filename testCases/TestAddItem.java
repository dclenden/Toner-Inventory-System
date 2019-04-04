package testCases;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import InventorySystem.ItemImp;
import InventorySystem.CSV_DBIMP;

class TestAddItem {

	@Test
	void test() {
		//fail("Not yet implemented");
		CSV_DBIMP dao = new CSV_DBIMP(); // fill the array itemList array to the max point, then try to add
		                                 // an item so that it will throw an ArrayIndexOutOfBounds Exception
		for(int i = 0; i < 61; i++) {
		    dao.addItem(new ItemImp());
		}
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> dao.addItem(new ItemImp()));
	}

}
