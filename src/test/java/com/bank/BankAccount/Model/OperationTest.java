package com.bank.BankAccount.Model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.bank.BankAccount.constants.ClientAccountsConstants;

public class OperationTest {
	
	private Client client;
	private Account account;
	
	// setUp create a new client and a new account for each test
	@Before
	public void setUp() {
		client = new Client("kata", "1234");
		account = new Account(ClientAccountsConstants.ACCOUNT_TYPE_SAVING,Double.valueOf(0), client);
		assertThat(account).isNotNull();
		assertThat(client).isNotNull();
		assertThat(account.getClient()).isNotNull();
		assertThat(account.getAccountId()).isNotNull();
		assertThat(client.getClientId()).isNotNull();
	}

	// We should add a new operation to the account operations list after every deposit or withdrawal
	@Test
	public void shouldAddToOperationsWhenDeposit() {
		account.setBalance(2.0);
		int numberOfOperations = account.getOperations().size();
		account.makeADeposit(1.0);
		assertEquals(numberOfOperations + 1, account.getOperations().size());
	}
}
