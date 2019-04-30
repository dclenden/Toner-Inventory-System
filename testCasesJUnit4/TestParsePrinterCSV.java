/*
* @author David W. Clendenning Jr.
*/
package testCasesJUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Test;
import java.io.File;
import java.util.Arrays;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

import InventorySystem.CSV_DBIMP;

public class TestParsePrinterCSV {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testReadPrinterIncorrectSizeOfFile() { //test data = printers - Copy.xlsx
		//fail("Not yet implemented");
		CSV_DBIMP dao = new CSV_DBIMP();
		//
		//
		//csvimp.readPrinterCSV();
		dao.storePrinterCSV("printersTestOutOfRange.csv"); //columns of original file
		dao.readPrinterCSV();
        //thrown.expectMessage(startsWith("some Message"));
        thrown.expectMessage("Incorrect file format");
	}
	@Test(expected = NumberFormatException.class)
	public void testReadPrinterNonCSV() {
		CSV_DBIMP dao = new CSV_DBIMP();
		//
		//
		//csvimp.readPrinterCSV();
		dao.storePrinterCSV("printers(1).xlsx"); // this is not a csv file - should not work for this method
        //thrown.expectMessage(startsWith("some Message"));
		dao.readPrinterCSV();
        thrown.expectMessage("Incorrect file format");
	}

	@Test
	public void testReadPrinterWorks() { //test data = printers - Copy.xlsx
		//fail("Not yet implemented");
		CSV_DBIMP dao = new CSV_DBIMP();
		//
		//
		//csvimp.readPrinterCSV();
		int i = 0;
		dao.storePrinterCSV("printers.csv"); // Exception is never thrown so this passes the testCase
		dao.readPrinterCSV();                // if this test failed, it would ask for an input or throw an exception
		assertEquals(false, dao.needInput);
		//System.out.println(Arrays.toString(dao.printerList));
	}

}
