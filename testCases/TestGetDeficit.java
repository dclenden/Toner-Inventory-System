/*
* @author David W. Clendenning Jr.
*/
package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import InventorySystem.ItemImp;

class TestGetDeficit {

	@Test
	void test1() {
		ItemImp item = new ItemImp();
		int getDeficit = item.getDeficit(); // values by default are 0, therefore min and cur stock = 0
		assertEquals(0, getDeficit);
	}
	@Test
	void test2() {
		ItemImp item = new ItemImp("a", "b", "c", 5, 0);
		int getDeficit = item.getDeficit(); //min stock is 5, curStock is 0, we should have a deficit of 5
		assertEquals(5, getDeficit);
	}
	@Test
	void test3() {
		ItemImp item = new ItemImp("a", "b", "c", -5, 10);//min stock is 1000, cur stock is 5 we should have a deficit of 995
		assertThrows(NumberFormatException.class, () -> item.getDeficit());
	}
}
