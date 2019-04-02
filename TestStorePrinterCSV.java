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

class TestStorePrinterCSV {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
	@Test
	void test1() {
		//fail("Not yet implemented");
		CSV_DBIMP csvimp = new CSV_DBIMP();
		csvimp.readPrinterCSV();
		assertThrows(NullPointerException.class, () -> csvimp.readPrinterCSV());
        //thrown.expectMessage(startsWith("some Message"));
        //thrown.expectMessage("Incorrect file format");
	}
	void test2() {
		
	}

}
