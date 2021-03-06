#ifndef assets_commands_h
#define assets_commands_h

//library headers

//local headers
#include "globals.h"

void command_addCredit();
void command_addCredit_admin();
void command_buy();
void command_create();
void command_delete();
void command_refund();
void command_sell();

//basic command pseudocode:
	//get all inputs
		//stop only if input line cannot be parsed
	//validate all inputs
		//e.g. username is in the accounts list
	//if relevant to command, confirm execution of command should proceed
		//only command this applies to is buy
	//prepare relevant objects
		//e.g. new account, new tickets, etc
	//perform voidernal actions
		//e.g. accounts.add(newAccount) or tickets[4].quantity-=4
	//construct transaction
		//e.g. Transaction transaction(Transaction.create);
			//transaction.username = username;
	//push transaction
	//transactions.add(transaction);
	//clean up
	//terminate
	//return 0;
//end basic command pseudocode

#endif
