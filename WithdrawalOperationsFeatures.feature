Feature: check operations validity

Scenario: Verify balance is changed when withdrawal
	Given we have a bank account with a balance of 50
	When we make a withdrawal of 4
	Then we would get a balance equals to 46  