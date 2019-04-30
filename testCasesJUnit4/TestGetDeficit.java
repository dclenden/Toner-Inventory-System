/*
* @author David W. Clendenning Jr.
*/
package testCasesJUnit4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import InventorySystem.ItemImp;

public class TestGetDeficit {

	@Test
	public void test1() {
		ItemImp item = new ItemImp();
		int getDeficit = item.getDeficit(); // values by default are 0, therefore min and cur stock = 0
		assertEquals(0, getDeficit);
	}
	@Test
	public void test2() {
		ItemImp item = new ItemImp("a", "b", "c", 5, 0);
		int getDeficit = item.getDeficit(); //min stock is 5, curStock is 0, we should have a deficit of 5
		assertEquals(5, getDeficit);
	}
	@Test(expected = NumberFormatException.class)
	public void test3() {
		ItemImp item = new ItemImp("a", "b", "c", -5, 10);//min stock is 1000, cur stock is 5 we should have a deficit of 995
        item.getDeficit();
	}
}
