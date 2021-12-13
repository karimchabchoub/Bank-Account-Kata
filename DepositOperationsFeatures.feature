Feature: check operations validity

Scenario: Verify balance is changed when deposit
	Given we have a bank account with a balance of 0 
	When we make a deposit of 50
	Then we would get a balance equals to 50  
	
