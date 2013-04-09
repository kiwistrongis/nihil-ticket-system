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
	public Sell( String s) throws DataFormatException {
		//read remaining portion of line from merged dtf, extracting the necesary parameters
		//of this sell transact
		
		//Ensure s is of the correct length
		if( s.length() != Ticket.eventName_size + 1 + Account.username_size + 1 + quantity_size + 1 + price_size )
			throw new DataFormatException("Sell: string s is of an incorrect size");
		
		//Get eventName of tickets being sold
		this.eventName  = s.substring(0, Ticket.eventName_size-1).trim();
		for(int i = 0;  i<this.eventName.length();  i++)
		{
			try{
				if( !this.eventName.substring(i,1).matches("[a-zA-Z0-9_]+") ) throw new DataFormatException("Sell: Invalid eventname formate"); 
			} catch(PatternSyntaxException e) {
				System.out.println(" Sell: eventname regular expression is invalid");				
			}
		}
			
		//Get username of seller (selling these tickets)
		this.seller     = s.substring(Ticket.eventName_size+1, Ticket.eventName_size+1 + Account.username_size-1).trim();
		
		try {
			this.numTickets = Integer.parseInt( s.substring( Ticket.eventName_size+1 + Account.username_size + 1, Ticket.eventName_size+1 + Account.username_size + 1 + 2) );
		} catch(NumberFormatException e) {
					
		}
		
		
		//extract integer portion of ticket price  (in hundreds of cents)
		this.ticketPrice = 100* Integer.parseInt( s.substring( Ticket.eventName_size+1 + Account.username_size + 1 + 3 + 1, Ticket.eventName_size+1 + Account.username_size + 1 + 3 + 1 + 2 ) );   
		
		//now extract decimal portion adding it to ticket price (# of cents)
		this.ticketPrice += Integer.parseInt( s.substring( Ticket.eventName_size+1 + Account.username_size + 1 + 3 + 1, Ticket.eventName_size+1 + Account.username_size + 1 + 3 + 1 + 2 ) );

		//validate all fields
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
		
		//Create a new ticket object representing the tickets to be sold, with the parameters set up
		//during the constructor, then add this ticket object to the vector of tickets.
		tickets.add( new Ticket( this.eventName, this.seller, this.quantity_size, this.ticketPrice));
		
	}

}
