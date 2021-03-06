package transaction;

//library imports
import java.util.Vector;
import java.util.zip.DataFormatException;
import java.util.regex.*;

//local imports
import assets.*;

/** 
 * @class 	Buy
 * @brief 	Represents a 'buy' transaction.
 **/
public class Buy extends Transaction {
	//static field that identifies the transaction to the server
	public static final int code = 3;

	public static final int quantity_size  =  3;
	public static final int price_size     =  6;

	//Parameters for the Buy Transaction
	public String eventName;	//eventname of which the tickets to
	public String seller;		//username of seller
	public int numTickets;		//number of tickets to buy
	
	/**
	 * Constructs a buy transaction given the text line from a merged DTF (minus the code at the beginning
	 * that was extracted by NTSServer).
	 */
	public Buy(String Line) throws DataFormatException {
		//read remaining portion of line from merged dtf, extracting the necesary parameters
		//of this buy transact

		//Ensure s is of the correct length
		if( Line.length() != Ticket.eventName_size + 1 + Account.username_size + 1 + quantity_size + 1 + price_size )
			throw new DataFormatException("Buy: string s is of an incorrect size");		
		
		//Get eventName of which to buy tickets to
		this.eventName = Line.substring(0, Ticket.eventName_size).trim();
		try {
			if( !this.eventName.matches("[a-zA-Z0-9 ]+") ) throw new DataFormatException("Buy: Invalid characters in event name"); 
		} catch(PatternSyntaxException e) {
			System.out.println(" Buy: eventname regular expression is invalid");	
			throw new DataFormatException("Regular expression for eventname is invalid."); //will also stop this constructor from silently returning 
		}	

		//Get username of seller (selling these tickets being purchased)
		this.seller = Line.substring(Ticket.eventName_size+1, Ticket.eventName_size+1 + Account.username_size).trim();
		try {
			if( !this.seller.matches("[a-zA-Z0-9 ]+") ) throw new DataFormatException("Buy: Invalid characters in seller name"); 
		} catch(PatternSyntaxException e) {
			System.out.println("Buy: seller regular expression is invalid");	
			throw new DataFormatException("Regular expression for seller is invalid."); //will also stop this constructor from silently returning 
		}			

		//Extract quantity of tickets to Buy
		try {
			this.numTickets = Integer.parseInt( Line.substring( Ticket.eventName_size+1 + Account.username_size + 1, Ticket.eventName_size+1 + Account.username_size + 1 + 3) );
		} catch(NumberFormatException e) {
			throw new DataFormatException("Buy: Invalid Ticket Field");	
		}
		if( this.numTickets < 0 || this.numTickets > 999 ) throw new DataFormatException("Buy: Invalid number of tickets to sell");
	}
	
	/**
	 * Applies this buy transaction, updating the given vectors of accounts and available tickets 
	 * accordingly.
    *
	 * Important Note: the user's credit will be deducted by the immediately following refund transaction
	 */
	public void applyTo(Vector<Account> accounts, Vector<Ticket> tickets)
			throws TransactionException {	

		//Find a ticket object in the vector of tickets with enough 
		//tickets to the specified event from the required seller
		for(int i = 0;  i<tickets.size();  i++){
			Ticket current = tickets.get(i);
			//This ticket (ticket i) has enough of the desired tickets
			if(current.username.equals(this.seller) 
					&& current.eventName.equals(this.eventName) 
					&& current.quantity >= this.numTickets){
				//Update vector, removing purchased tickets
				tickets.get(i).quantity -= this.numTickets; 
				return;
			}
		}
		//If control flow reaches here, no ticket object with the required tickets was found
		throw new TransactionException("No Matching ticket with enough tickets for sale was found");
	}
}
