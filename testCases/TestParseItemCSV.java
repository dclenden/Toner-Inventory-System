package testCases;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import InventorySystem.CSV_DBIMP;

class TestParseItemCSV {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
	@Test
	void test() { //test data = printers.csv
		//fail("Not yet implemented");
		CSV_DBIMP csvimp = new CSV_DBIMP();
		//
		//
		//csvimp.readPrinterCSV();
		assertThrows(NumberFormatException.class, () -> csvimp.readItemCSV());
        //thrown.expectMessage(startsWith("some Message"));
        thrown.expectMessage("Incorrect file format");
		
	}
	/*@Test test data = printers - Copy.xlsx WORKS
	void test2() {
		CSV_DBIMP csvimp = new CSV_DBIMP();
		//
		//
		//csvimp.readPrinterCSV();
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> csvimp.readItemCSV());
        //thrown.expectMessage(startsWith("some Message"));
        thrown.expectMessage("Incorrect file format");
	}*/

}
