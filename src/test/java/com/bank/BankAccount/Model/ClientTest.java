package com.bank.BankAccount.Model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.bank.BankAccount.constants.ClientAccountsConstants;

public class ClientTest {

	private Client client;

	// setUp create a new client for each test
	@Before
	public void setUp() {
		client = new Client("kata", "1234");
		assertThat(client).isNotNull();
		assertThat(client).isNotNull();
		assertThat(client.getClientId()).isNotNull();
	}

	// Check if a new created client has at least one account
	@Test
	public void checkIfClientHasAtLeastOneAccount() {
		assertEquals(1, client.getAccounts().size());
	}

	// Check if a new created account in a client works perfectly
	@Test
	public void checkIfCreateNewAccountWorksPerfectly() {
		client.createNewAccount(ClientAccountsConstants.ACCOUNT_TYPE_SAVING, Double.valueOf(0));
		assertEquals(2, client.getAccounts().size());
	}
	
}
