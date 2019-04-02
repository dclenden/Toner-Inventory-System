/*
* @author David W. Clendenning Jr.
*/
package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import InventorySystem.Item;
import InventorySystem.ItemImp;

class TestHasDeficit {

	@Test
	void test1() {
		//fail("Not yet implemented");
		ItemImp item = new ItemImp();
		boolean hasDeficit = item.hasDeficit(); // values by default are 0, therefore min and cur stock = 0
		assertEquals(false, hasDeficit);
	}
	@Test
	void test2() {
		ItemImp item = new ItemImp("abc", "a", "ab", 5, 6); //minStock = 5, curStock = 6 <- should return false
		boolean hasDeficit = item.hasDeficit();
		assertEquals(false, hasDeficit);
	}
	@Test
	void test3() {
		ItemImp item = new ItemImp("abc", "a", "ab", 5, 2); //minStock = 5, curStock = 2 <- should return true
		boolean hasDeficit = item.hasDeficit();
		assertEquals(true, hasDeficit);
	}

}
