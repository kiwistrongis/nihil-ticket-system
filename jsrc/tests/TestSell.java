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
public class TestSell {
	
	
	//Test 1:  Tests constructor with valid DTF Line
	@Test		
	public void testSell() throws DataFormatException {
		Sell TestSell = new Sell("EventName1222223333 UserName1122222 001 102.99");
	}
		
	//Test 2:  Tests constructor's ability to handle a remaining DTF Line of an incorrect size
	@Test(expected = DataFormatException.class)
	public void testSell_InvalidLine() throws DataFormatException {
		Sell TestSell = new Sell("remaining DTFLine of wrong size");
	}
	
	//Test 3:  Tests constructor's ability to identify invalid eventnames
	@Test(expected = DataFormatException.class)
	public void testSell_InvalidEventName() throws DataFormatException {
		Sell TestSell = new Sell("EventN*me1222223333 UserName1122222 001 102.99");
	}	

	//Test 4:  Tests constructor's ability to identify invalid usernames (invalid characters/format)
	@Test(expected = DataFormatException.class)
	public void testSell_InvalidSeller() throws DataFormatException {
		Sell TestSell = new Sell("EventName1222223333 UserN())1122222 001 102.99");
	}	

	//Test 5:  Tests constructor's ability to catch invalid quantity fields in DTF line
	@Test(expected = DataFormatException.class)
	public void testSell_InvalidQuantityChars() throws DataFormatException {
		Sell TestSell = new Sell("EventName1222223333 UserName1122222 0d) 102.99");
	}	

	//Test 6:  Tests constructor's ability to catch quantity values less than 0
	@Test(expected = DataFormatException.class)
	public void testSell_QuantityLessThanZero() throws DataFormatException {
		Sell TestSell = new Sell("EventName1222223333 UserName1122222 -29 102.99");
	}

	//IMPORTANT NOTE: ticket quantities >= 1000 can't occur due to only 3 chars for quantity

	//Test 7:  Tests constructor's ability to catch quantity values with a decimal
	@Test(expected = DataFormatException.class)
	public void testSell_QuantityWithDecimal() throws DataFormatException {
		Sell TestSell = new Sell("EventName1222223333 UserName1122222 2.9 102.99");
	}

	//Test 8:  Tests constructor's ability to catch invalid characters in integer portion of price
	@Test(expected = DataFormatException.class)
	public void testSell_InvalidIntegerTicketPrice() throws DataFormatException {
		Sell TestSell = new Sell("EventName1222223333 UserName1122222 001 1bZ.99");
	}

	//Test 9:  Tests constructor's ability to catch invalid characters in decimal portion of price
	@Test(expected = DataFormatException.class)
	public void testSell_InvalidDecimalTicketPrice() throws DataFormatException {
		Sell TestSell = new Sell("EventName1222223333 UserName1122222 001 100.Q9");
	}

	//Test 10:  Tests constructor's ability to catch negative ticket price values
	@Test(expected = DataFormatException.class)
	public void testSell_NegativeTicketPrice() throws DataFormatException {
		Sell TestSell = new Sell("EventName1222223333 UserName1122222 001 -20.09");
	}

	//Test 11:  Tests applyTo() method with valid data
	@Test
	public void testApplyTo() throws DataFormatException, TransactionException {
		Vector<Account> TestAccounts = new Vector<Account>();
		Vector<Ticket>  TestTickets  = new Vector<Ticket>();

		TestAccounts.add( new Account("UserName1122222", Account.Admin, 1000) );

		Sell TestSell = new Sell("EventName1222223333 UserName1122222 001 102.99");

		TestSell.applyTo( TestAccounts, TestTickets );
	}	

	//Test 12:  Tests applyTo() method, in which the sell transaction is 
	//          selling tickets from a seller that does not exist
	@Test(expected = TransactionException.class)
	public void testApplyTo_NonExistentSeller() throws DataFormatException, TransactionException {
		Vector<Account> TestAccounts = new Vector<Account>();
		Vector<Ticket>  TestTickets  = new Vector<Ticket>();

		//acounts will be empty, thus 'UserName1122222' does not exist 

		Sell TestSell = new Sell("EventName1222223333 UserName1122222 001 102.99");

		TestSell.applyTo( TestAccounts, TestTickets );
	}	
	
	
}



