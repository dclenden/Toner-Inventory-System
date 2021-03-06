/*
* @author David W. Clendenning Jr.
*/
package testCasesJUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Test;

import InventorySystem.CSV_DBIMP;
import InventorySystem.Printer;
import InventorySystem.PrinterImp;

public class TestGetPrinter {

	@Test
	public void testGetPrinter() {
		CSV_DBIMP dao = new CSV_DBIMP();
		int rand = (int)(Math.random() * dao.printerList.length);
		Printer testPrinter = new PrinterImp();
		for(int i = 0; i < dao.printerList.length; i++) {
		    dao.printerList[i] = testPrinter;
		}
		Printer expectedResult = new PrinterImp(14321, "location", "department"
				, "manufacturer", "testDescription", "category", "serialNumber");
		dao.printerList[rand] = expectedResult;
		assertEquals(expectedResult, dao.getPrinter(14321));
	}
	@Test
	public void testWhenPrinterNotInList() {
		CSV_DBIMP dao = new CSV_DBIMP();
		assertEquals(null, dao.getPrinter(14321));
	}

}
