/*
* @author David W. Clendenning Jr.
*/
package testCases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import InventorySystem.CSV_DBIMP;
import InventorySystem.Printer;
import InventorySystem.PrinterImp;

class TestGetPrinter {

	@Test
	void testWhenPrinterListIsFull() {
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
	void testWhenPrinterListIsEmpty() {
		CSV_DBIMP dao = new CSV_DBIMP();
		Printer expectedResult = null;
		assertEquals(expectedResult, dao.getPrinter(14321));
	}

}
