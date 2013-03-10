#include "../commands.h"


/**
 * Performs the necessary operations when the user invokes the sell command.
 * This command sells a specified number of tickets to a specified event,
 * with the specified price per ticket.
 * The user is asked for the following information, in this order:
 *		1 the event name
 *		2 pricer per ticket
 *		3 number of tickets to sell
 */
void command_sell(){
//basic command pseudocode:

	//Get Event Title
	std::cout << "\nPlease enter the event of which to sell tickets for:\n";
	char* InputEventTitle = format( getLine() );

	//Get Ticket Price
	std::cout << "\nPlease enter the price per ticket:\n";
	char* InputTicketPrice = format( getLine() );

	//Get Number of Tickets to Sell
   std::cout << "\nPlease enter the number of tickets to sell:\n";
	char* InputNumOfTicket = format( getLine() );

	
	//get all inputs
		//stop only if input line cannot be parsed
	//validate all inputs
		//e.g. username is in the accounts list


	//prepare relevant objects
		//e.g. new account, new tickets, etc

	//perform internal actions
		//e.g. accounts.add(newAccount) or tickets[4].quantity-=4

	//construct transaction
		//e.g. Transaction transaction(Transaction.create);
			//transaction.username = username;
	//push transaction
	//transactions.add(transaction);
	
	return;
}




