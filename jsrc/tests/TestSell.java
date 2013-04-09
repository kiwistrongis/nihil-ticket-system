package tests;

import assets.*;
import transaction.*;
import java.util.zip.DataFormatException;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestSell {
	
	@Test
	public void testSellConstructor_Valid() throws DataFormatException {
		Sell TestSell = new Sell("EventName1222223333 UserName1122222 001 102.99");
	}
	
	/*
	@Test(expected = DataFormatException.class)
	public void testSellConstructor_Valid() throws DataFormatException {
		Sell TestSell = new Sell("EventName1222223333 UserName1122222 001 102.99");
	}	
	
	*/
	
}