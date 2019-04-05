/*
* @author David W. Clendenning Jr.
*/
package testCases;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import InventorySystem.CSV_DBIMP;
import InventorySystem.Printer;

class TestGetAllPrinters {

	@Test
	void testGetAllPrinters() {
		//fail("Not yet implemented");
		CSV_DBIMP dao = new CSV_DBIMP();
		ArrayList<Printer> allPrinters = new ArrayList<>();
		for(Printer i: dao.printerList) {
			allPrinters.add(i);
		} //passes test case because all items within the printerList are added to the daoPrinterList due to getAllPrinters()
		assertEquals(allPrinters, dao.getAllPrinters());
	}
	@Test
	void testGetAllPrintersWithWrongData() {
		//fail("Not yet implemented");
		CSV_DBIMP dao = new CSV_DBIMP();
		ArrayList<Printer> allPrinters = new ArrayList<>();
		for(Printer i: dao.printerList) {
			allPrinters.add(i);
		}
		int rand = (int)(Math.random() * dao.printerList.length); //random item being removed
		allPrinters.remove(rand);
		assertNotEquals(allPrinters, dao.getAllPrinters());
	}

}
