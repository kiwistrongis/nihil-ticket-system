package tests;

//local imports
import assets.*;
//library imports
import java.util.zip.DataFormatException;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class TestAccount {
	public static void main( String[] args) throws DataFormatException{
		String line = "manager         AA 001000.00";
		Account manager = new Account( line);
		System.out.println(line);
		System.out.println(manager);}

	@Test
	public void testRead() throws DataFormatException {
		String line = "manager         AA 001000.00";
		Account account = new Account( line);
		assertTrue( line.equals( account.toString()));
	}

	@Test
	public void testWrite() throws DataFormatException {
		Account account = new Account(
			"manager", Account.Admin, 100);
		assertEquals( account, new Account( account.toString()));
	}

	@Test
	public void testEquals_unequal() {
		assertFalse(
			new Account("name", 0, 0).equals(
			new Account("asdf", 0, 0)));
	}
	@Test
	public void testEquals_normal() {
		assertEquals(
			new Account("name", 0, 0),
			new Account("name", 0, 0));
	}
	@Test
	public void testEquals_creditdiff() {
		assertEquals(
			new Account("name", 0, 0),
			new Account("name", 0, 1));
	}
	@Test
	public void testEquals_typediff() {
		assertEquals(
			new Account("name", 0, 0),
			new Account("name", 1, 0));
	}
	@Test
	public void testEquals_alldiff() {
		assertEquals(
			new Account("name", 0, 0),
			new Account("name", 1, 1));
	}
}