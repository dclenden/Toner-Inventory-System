package testCases;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import InventorySystem.CSV_DBIMP;

class TestParseItemCSV {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
	@Test
	void testReadItemIncorrectSizeOfFile() { //test data = printers - Copy.xlsx
		//fail("Not yet implemented");
		CSV_DBIMP dao = new CSV_DBIMP();
		//
		//
		dao.storeItemCSV("printersTestOutOfRange.csv"); //the parsing of the file for type Item did not match the 'printer' CSV
		assertThrows(NumberFormatException.class, () -> dao.readItemCSV());
        //thrown.expectMessage(startsWith("some Message"));
        thrown.expectMessage("Incorrect file format");
	}
	@Test
	void testReadItemNonCSV() {
		CSV_DBIMP dao = new CSV_DBIMP();
		//
		//
		//csvimp.readPrinterCSV();
		dao.storeItemCSV("Wilmington Toner Database(1).xlsx"); // this non csv file has fewer Columns than what is required
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> dao.readItemCSV());
        //thrown.expectMessage(startsWith("some Message"));
        thrown.expectMessage("Incorrect file format");
	}

	@Test
	void testReadItems() { //test data = printers - Copy.xlsx
		//fail("Not yet implemented");
		CSV_DBIMP dao = new CSV_DBIMP();
		//
		//
		//csvimp.readPrinterCSV();
		dao.storeItemCSV("Wilmington Toner Database.csv"); // Exception is never thrown so this passes the testCase
		dao.readItemCSV();                // if this test failed, it would ask for an input or throw an exception
		assertEquals(false, dao.needInput);
		System.out.println(Arrays.toString(dao.printerList));
	}

}
