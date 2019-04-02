/*
* @author David W. Clendenning Jr.
*/
package testCases;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import InventorySystem.CSV_DBIMP;

class TestParsePrinterCSV {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
	@Test
	void test1() { //test data = printers - Copy.xlsx
		//fail("Not yet implemented");
		CSV_DBIMP csvimp = new CSV_DBIMP();
		//
		//
		//csvimp.readPrinterCSV();
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> csvimp.readPrinterCSV());
        //thrown.expectMessage(startsWith("some Message"));
        thrown.expectMessage("Incorrect file format");
	}
	/*@Test
	void test2() {
		CSV_DBIMP csvimp = new CSV_DBIMP(); WILL WORK ONCE ASSET TAG IS LABELED AS INT IN PRINTER CLASS
		//
		//
		//csvimp.readPrinterCSV();
		assertThrows(NumberFormatException.class, () -> csvimp.readPrinterCSV());
        //thrown.expectMessage(startsWith("some Message"));
        thrown.expectMessage("Incorrect file format");
	}*/

}
