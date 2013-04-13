
package tests;

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
	public void testBuyConstructor_Valid() throws DataFormatException {
		Buy TestBuy = new Buy("EventName1222223333 UserName1122222 001 000.00");
	}
		
	//Test 2:  Tests constructor's ability to handle a remaining DTF Line of an incorrect size
	@Test(expected = DataFormatException.class)
	public void testBuyConstructor_InvalidLineLength() throws DataFormatException {
		Buy TestBuy = new Buy("remaining DTFLine of wrong size");
	}
	
	//Test 3:  Tests constructor's ability to identify invalid eventnames
	@Test(expected = DataFormatException.class)
	public void testBuyConstructor_InvalidEventNameCharacters() throws DataFormatException {
		Buy TestBuy = new Buy("EventN*me1222223333 UserName1122222 001 000.00");
	}	

	//Test 4:  Tests constructor's ability to identify invalid usernames (invalid characters/format)
	@Test(expected = DataFormatException.class)
	public void testBuyConstructor_InvalidSellerCharacters() throws DataFormatException {
		Buy TestBuy = new Buy("EventName1222223333 UserN())1122222 001 102.99");
	}	

	//Test 5:  Tests constructor's ability to catch invalid quantity fields in DTF line
	@Test(expected = DataFormatException.class)
	public void testBuyConstructor_InvalidQuantityCharacters() throws DataFormatException {
		Buy TestBuy = new Buy("EventName1222223333 UserName1122222 0d) 102.99");
	}	

	//Test 6:  Tests constructor's ability to catch quantity values less than 0
	@Test(expected = DataFormatException.class)
	public void testBuyConstructor_InvalidQuantityLessThanZero() throws DataFormatException {
		Buy TestBuy = new Buy("EventName1222223333 UserName1122222 -29 102.99");
	}

	//IMPORTANT NOTE: ticket quantities >= 1000 can't occur due to only 3 chars for quantity

	//Test 7:  Tests constructor's ability to catch quantity values with a decimal
	@Test(expected = DataFormatException.class)
	public void testBuyConstructor_InvalidQuantityWithDecimal() throws DataFormatException {
		Buy TestBuy = new Buy("EventName1222223333 UserName1122222 2.9 102.99");
	}




}


