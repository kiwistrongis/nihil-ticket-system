
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
public class TestBuy {

	
	//Test 1:  Tests constructor with valid DTF Line
	@Test		
	public void testBuy() throws DataFormatException {
		Buy TestBuy = new Buy("EventName1222223333 UserName1122222 001 000.00");
	}
		
	//Test 2:  Tests constructor's ability to handle a remaining DTF Line of an incorrect size
	@Test(expected = DataFormatException.class)
	public void testBuy_InvalidLine() throws DataFormatException {
		Buy TestBuy = new Buy("remaining DTFLine of wrong size");
	}
	
	//Test 3:  Tests constructor's ability to identify invalid eventnames
	@Test(expected = DataFormatException.class)
	public void testBuy_InvalidEventName() throws DataFormatException {
		Buy TestBuy = new Buy("EventN*me1222223333 UserName1122222 001 000.00");
	}	

	//Test 4:  Tests constructor's ability to identify invalid usernames (invalid characters/format)
	@Test(expected = DataFormatException.class)
	public void testBuy_InvalidSeller() throws DataFormatException {
		Buy TestBuy = new Buy("EventName1222223333 UserN())1122222 001 102.99");
	}	

	//Test 5:  Tests constructor's ability to catch invalid quantity fields in DTF line
	@Test(expected = DataFormatException.class)
	public void testBuy_InvalidQuantity() throws DataFormatException {
		Buy TestBuy = new Buy("EventName1222223333 UserName1122222 0d) 102.99");
	}	

	//Test 6:  Tests constructor's ability to catch quantity values less than 0
	@Test(expected = DataFormatException.class)
	public void testBuy_InvalidQuantityLessThanZero() throws DataFormatException {
		Buy TestBuy = new Buy("EventName1222223333 UserName1122222 -29 102.99");
	}

	//IMPORTANT NOTE: ticket quantities >= 1000 can't occur due to only 3 chars for quantity

	//Test 7:  Tests constructor's ability to catch quantity values with a decimal
	@Test(expected = DataFormatException.class)
	public void testBuy_InvalidQuantityWithDecimal() throws DataFormatException {
		Buy TestBuy = new Buy("EventName1222223333 UserName1122222 2.9 102.99");
	}

	//Test 8:  Tests applyTo method, with valid conditions
	@Test
	public void testApplyTo() throws DataFormatException, TransactionException {
		Vector<Account> TestAccounts = new Vector<Account>();
		Vector<Ticket>  TestTickets  = new Vector<Ticket>();

		TestTickets.add( new Ticket("TheEvent", "BestSeller", 2, 100) );

		Buy b = new Buy("TheEvent            BestSeller      001 102.99");

		b.applyTo( TestAccounts, TestTickets );
	}

	//Test 9:  Tests applyTo method, where the eventname/seller tickets don't exist
	@Test(expected = TransactionException.class) 
	public void testApplyTo_TicketsNotFound() throws DataFormatException, TransactionException {
		Vector<Account> TestAccounts = new Vector<Account>();
		Vector<Ticket>  TestTickets  = new Vector<Ticket>();
		
		Buy b = new Buy("TheEvent            BestSeller      001 102.99");

		//Note: TestTickets intentionally does not have a ticket with the specified seller/eventname

		b.applyTo( TestAccounts, TestTickets );		
	}

	//Test 10:  Tests applyTo method, enforcing the for loop is executed twice
	@Test 
	public void testApplyTo_ForLoopIterateTwice() throws DataFormatException, TransactionException {
		Vector<Account> TestAccounts = new Vector<Account>();
		Vector<Ticket>  TestTickets  = new Vector<Ticket>();
		
		TestTickets.add( new Ticket("WrongEvent", "BestSeller", 2, 100) );
		TestTickets.add( new Ticket("TheEvent", "BestSeller", 2, 100)   );

		Buy b = new Buy("TheEvent            BestSeller      001 102.99");

		//Note: TestTickets intentionally does not have a ticket with the specified seller/eventname

		b.applyTo( TestAccounts, TestTickets );		
	}

	//Test 11:  Tests applyTo method, enforcing the for loop is executed five times (MANY)
	@Test 
	public void testApplyTo_ForLoopIterateMany() throws DataFormatException, TransactionException {
		Vector<Account> TestAccounts = new Vector<Account>();
		Vector<Ticket>  TestTickets  = new Vector<Ticket>();

		TestTickets.add( new Ticket("WrongEvent1", "BestSeller", 2, 100) );
		TestTickets.add( new Ticket("WrongEvent2", "BestSeller", 2, 100) );
		TestTickets.add( new Ticket("WrongEvent3", "BestSeller", 2, 100) );
		TestTickets.add( new Ticket("WrongEvent4", "BestSeller", 2, 100) );
		TestTickets.add( new Ticket("TheEvent",    "BestSeller", 2, 100) );	
	
		Buy b = new Buy("TheEvent            BestSeller      001 102.99");

		//Note: TestTickets intentionally does not have a ticket with the specified seller/eventname

		b.applyTo( TestAccounts, TestTickets );		
	}
}


