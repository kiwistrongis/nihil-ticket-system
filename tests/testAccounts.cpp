#include <cstdio>

#include <algorithm>
#include <string>

#include "../assets/globals.h"
#include "../assets/Ticket.h"

using namespace std;

int main ( int argc, char** argv, char** envp) {
	input = new char[input_size];
	buffer = new char[buffer_size];
	error_string = new char[error_size];
	//load the accounts file
	char filename[] = "resources/data.cua";
	printf( "Success: %s\n", loadAccounts( filename) ?
		"true" : "false");
	//do validations
	printf( "Number of accounts: %d\n", accounts.size());
	for( int i = 0; i < accounts.size(); i++)
		printf("Account %d:{%10s,%d,%d}\n", i,
			accounts[i].username, accounts[i].type, accounts[i].credit);
}