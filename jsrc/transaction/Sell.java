package transaction;

//library imports
import java.util.Vector;
import java.util.zip.DataFormatException;

//local imports
import assets.*;
import java.util.regex.*;

/** 
 * @class Sell
 * @brief Represents a 'sell' transaction.
 **/
public class Sell extends Transaction {
	
	//static field that identifies the transaction to the server
	public static final int code = 4;

	public static final int quantity_size  =  3;
	public static final int price_size     =  6;
	
	//Sell Transaction Parameters
	public String eventName;	//eventname of which the tickets are being sold to
	public String seller;		//username of seller
	public int numTickets;		//number of tickets to sell
	public int ticketPrice;		//in cents
	
	/**
	 * Constructs a sell transaction object, using the (remainder of a) text from a line of the
	 * merged transaction file.
	 */
	public Sell(String Line) throws DataFormatException {
		//read remaining portion of line from merged dtf, extracting the necesary parameters
		//of this sell transact

		System.out.println();
		System.out.println(Line);
		
		//Ensure s is of the correct length
		if( Line.length() != Ticket.eventName_size + 1 + Account.username_size + 1 + quantity_size + 1 + price_size )
			throw new DataFormatException("Sell: string s is of an incorrect size");
		
		//Get eventName of tickets being sold
		this.eventName = Line.substring(0, Ticket.eventName_size).trim();
		try {
			if( !this.eventName.matches("[a-zA-Z0-9 ]+") ) throw new DataFormatException("Sell: Invalid characters in event name"); 
		} catch(PatternSyntaxException e) {
			System.out.println(" Sell: eventname regular expression is invalid");	
			throw new DataFormatException("Regular expression for eventname is invalid."); //will also stop this constructor from silently returning 
		}		
			
		//Get username of seller (selling these tickets)
		this.seller = Line.substring(Ticket.eventName_size+1, Ticket.eventName_size+1 + Account.username_size).trim();
		try {
			if( !this.seller.matches("[a-zA-Z0-9 ]+") ) throw new DataFormatException("Sell: Invalid characters in seller name"); 
		} catch(PatternSyntaxException e) {
			System.out.println(" Sell: seller regular expression is invalid");	
			throw new DataFormatException("Regular expression for seller is invalid."); //will also stop this constructor from silently returning 
		}			
		
		//Extract quantity of tickets to sell
		try {
			this.numTickets = Integer.parseInt( Line.substring( Ticket.eventName_size+1 + Account.username_size + 1, Ticket.eventName_size+1 + Account.username_size + 1 + 3) );
		} catch(NumberFormatException e) {
			throw new DataFormatException("Sell: Invalid Ticket Field");	
		}
		if( this.numTickets < 0 || this.numTickets > 999 ) throw new DataFormatException("Sell: Invalid number of tickets to sell");
			
		//Extract ticket price (by extracting integer portion than decimal portion)
		System.out.println(  );
		try {
			this.ticketPrice = 100* Integer.parseInt( Line.substring( Ticket.eventName_size+1 + Account.username_size + 1 + 3 + 1, Ticket.eventName_size+1 + Account.username_size + 1 + 3 + 1 + 3 ) );   
			this.ticketPrice += Integer.parseInt( Line.substring( Ticket.eventName_size+1 + Account.username_size + 1 + 3 + 1 + 4, Ticket.eventName_size+1 + Account.username_size + 1 + 3 + 1 + 4 + 2 ) );
		} catch(NumberFormatException e) {
			throw new DataFormatException("Sell: Invalid format of ticket price");
		}
		if( TicketPrice < 0 || TicketPrice > 999.99 ) throw new DataFormatException("Sell: Ticketprice must be between 0 and 999.99");
		
		
	}
	
	/**
	 * Applies this sell transaction to the given vectors of tickets and accounts.
	 * 
	 * @param accounts	Vector of account classes, representing the systems currently
	 *                  available user accounts.
	 * @param tickets   Vector of tickets representing the systems current set of
	 *                  tickets being sold from various sellers.
	 */
	public void applyTo(Vector<Account> accounts, Vector<Ticket> tickets) throws TransactionException {
		
		//TO DO   LOTS OF ERROR CHECKING
		
		//Create a new ticket object representing the tickets to be sold, with the parameters set up
		//during the constructor, then add this ticket object to the vector of tickets.
		tickets.add( new Ticket( this.eventName, this.seller, this.quantity_size, this.ticketPrice));
		
		
	}

}
