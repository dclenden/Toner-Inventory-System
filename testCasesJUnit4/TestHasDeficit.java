/*
* @author David W. Clendenning Jr.
*/
package testCasesJUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Test;

import InventorySystem.Item;
import InventorySystem.ItemImp;

public class TestHasDeficit {

	@Test
	public void test1() {
		//fail("Not yet implemented");
		ItemImp item = new ItemImp();
		boolean hasDeficit = item.hasDeficit(); // values by default are 0, therefore min and cur stock = 0
		assertEquals(false, hasDeficit);
	}
	@Test
	public void test2() {
		ItemImp item = new ItemImp("abc", "a", "ab", 5, 6); //minStock = 5, curStock = 6 <- should return false
		boolean hasDeficit = item.hasDeficit();
		assertEquals(false, hasDeficit);
	}
	@Test
	public void test3() {
		ItemImp item = new ItemImp("abc", "a", "ab", 5, 2); //minStock = 5, curStock = 2 <- should return true
		boolean hasDeficit = item.hasDeficit();
		assertEquals(true, hasDeficit);
	}
	@Test(expected = NumberFormatException.class)
	public void test4() {
		ItemImp item = new ItemImp("a", "b", "c", -100, -5);
		item.hasDeficit();
	}

}
