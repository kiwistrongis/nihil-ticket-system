package jsrc;

//local imports
import assets.*;
import transaction.*;
//library imports
import java.util.Vector;
import java.util.zip.DataFormatException;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class TestAddCredit{

	//Tests constructors ability to identify a valid input
	@Test
	public void testAddCreditConstruct_Valid() throws DataFormatException{
		AddCredit TestAddCredit = new AddCredit("UserName1122222 FS 000100.00");
	}
	//Test constructors ability to indentify an invalid usename
	@Test
	public void testAddCreditConstruct_InvalidUseName() throws DataFormatException{
		AddCredit TestAddCredit = new AddCredit("UserNa&e1122222 FS 000100.00");
	}
	//Tests constructors ability to catch an invalid usertype
	@Test
	public void testAddCreditConstruct_InvalidUserType() throws DataFormatException{
		AddCredit TestAddCredit = new AddCredit("UserName1122222 RS 000100.00");
	}
	//Tests constructors ability to catch an invalid credit value
	@Test
	public void testAddCreditConstruct_InvalidCreditValue() throws DataFormatException{
		AddCredit TestAddCredit = new AddCredit("UserName1122222 FS 0001k0.00");
	}
	//Tests constructors ability to prevent overcrediting per session
	@Test
	public void testAddCreditConstruct_InvalidCreditAmounnt() throws DataFormatException{
		AddCredit TestAddCredit = new AddCredit("UserName1122222 FS 010000.00");
	}
	//Tests constructors ability to reject incorrect string formats
	@Test
	public void testAddCreditConstruct_InvalidStringLength() throws DataFormatException{
		AddCredit TestAddCredit = new AddCredit("UserName11222227 RSW 000100.000");
	}
	//Tests constructors ability to reject zero addition
	@Test
	public void testAddCreditConstruct_ZeroValue() throws DataFormatException{
		AddCredit TestAddCredit = new AddCredit("UserName11222227 FS 000000.00");
	}
	//Tests constructors ability to catch decimal values
	@Test
	public void testAddCreditConstruct_DecimalInserted() throws DataFormatException{
		AddCredit TestAddCredit = new AddCredit("UserName1122222 FS 000100.99");
	}
}
