package tests;


//library imports
import java.util.zip.DataFormatException;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class TestRefund{

	//Tests constructors ability to identify a valid input
	@Test
	public void testRefundConstruct_Valid() throws DataFormatException{
		Refund TestRefund = new Refund("UserName1122222 UserName3344444 000100.00");
	}
	//Test constructors ability to indentify an invalid buyer usename
	@Test
	public void testRefundConstruct_InvalidBuyerUseName() throws DataFormatException{
		Refund TestRefund = new Refund("UserNa&e1122222 UserName3344444 000100.00");
	}
	//Test constructors ability to indentify an invalid usename
	@Test
	public void testRefundConstruct_InvalidSellerUseName() throws DataFormatException{
		Refund TestRefund = new Refund("UserName1122222 UserN#me3344444 000100.00");
	}
	//Tests constructors ability to catch an invalid credit value
	@Test
	public void testRefundConstruct_InvalidCreditValue() throws DataFormatException{
		Refund TestRefund = new Refund("UserName1122222 UserName3344444 0001k0.00");
	}
	//Tests constructors ability to prevent overcrediting per session
	@Test
	public void testRefundConstruct_InvalidCreditAmounnt() throws DataFormatException{
		Refund TestRefund = new Refund("UserName1122222 UserName3344444 010000.00");
	}
	//Tests constructors ability to reject incorrect string formats
	@Test
	public void testRefundConstruct_InvalidStringLength() throws DataFormatException{
		Refund TestRefund = new Refund("UserName11222227 UserName33444445 000100.000");
	}
	//Tests constructors ability to reject zero addition
	@Test
	public void testRefundConstruct_ZeroValue() throws DataFormatException{
		Refund TestRefund = new Refund("UserName11222227 UserName3344444 000000.00");
	}
	//Tests constructors ability to catch decimal values
	@Test
	public void testRefundConstruct_DecimalInserted() throws DataFormatException{
		Refund TestRefund = new Refund("UserName1122222 UserName3344444 000100.99");
	}
}
