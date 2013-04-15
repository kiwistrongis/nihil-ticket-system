
package tests;

import assets.*;
import transaction.*;
import java.util.zip.DataFormatException;
import java.util.Vector;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestTicket {

	//Test 1: Valid construction of a ticket object, given a correctly formatted ATF File Line
	@Test
	public void testTicket() throws DataFormatException
	{
		Ticket t = new Ticket("EventName1222223333 Username1122222 100 102.49");
	}

	//Test 2: Tests for failure of ticket creation due to incorrect ATF File Line size
	@Test(expected = DataFormatException.class)
	public void testTicket_InvalidLine() throws DataFormatException
	{
		Ticket t = new Ticket("IncorrectLengthOfThisLIne");
	}

	//Test 3: Tests the other constructor which is designed to construct a ticket of the specified parameters,
   // (all parameters will be verified for integrity before callling this constructor)
	@Test
	public void testTicket2() {
		Ticket t = new Ticket("MyEvent", "UserA", 100, 1000);
		assertTrue( t.eventName.equals("MyEvent") );
		assertTrue( t.username.equals("UserA") );
		assertTrue( t.quantity == 100 );
		assertTrue( t.price == 1000 );
	}

	//Test 4: Tests the toString Method
	@Test
	public void testToString() {
		Ticket t = new Ticket("EventName          ", "TheUsername1122", 130, 8920);
		assertTrue( t.toString().equals("EventName           TheUsername1122 130 089.20") );
	}

	//Test 5: Tests equals method, to check if two ticket objects have the same eventname and seller
	@Test
	public void testEquals_Equal() {
		Ticket t1 = new Ticket("GrandEvent", "Seller1", 100, 1000);
		Ticket t2 = new Ticket("GrandEvent", "Seller1", 101,  999);
		
		assertTrue( t1.equals( t2 ) );
	}

	//Test 6: Tests equals method, to check if two ticket objects that are different will result in false.
	@Test
	public void testEquals_NotEqual() {
		Ticket t1 = new Ticket("GrandEvent", "Seller1", 100, 1000);
		Ticket t2 = new Ticket("AnotherEvent", "Seller1", 101,  999);
		
		assertTrue( !t1.equals( t2 ) );
	}	

}
