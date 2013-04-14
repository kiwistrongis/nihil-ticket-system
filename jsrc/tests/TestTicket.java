
package tests;

import assets.*;
import transaction.*;
import java.util.zip.DataFormatException;
import java.util.Vector;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestTicket {

	//BEING WORKED ON BY WES, STILL HAVE MANY TESTS TO ADD HERE

	//Test 1: Valid construction of a ticket object, given a correctly formatted ATF File Line
	@Test
	public void testTicket_Valid() throws DataFormatException
	{
		Ticket t = new Ticket("EventName1222223333 Username1122222 100 102.49");
	}

	//Test 2: Tests for failure of ticket creation due to incorrect ATF File Line size
	@Test(expected = DataFormatException.class)
	public void testTicket_InvalidLineLength() throws DataFormatException
	{
		Ticket t = new Ticket("IncorrectLengthOfThisLIne");
	}

	




}
