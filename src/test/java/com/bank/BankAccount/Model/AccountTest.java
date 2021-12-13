package com.bank.BankAccount.Model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.bank.BankAccount.constants.ClientAccountsConstants;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

	private Account account;
	private Client client;

	// setUp create a new client and a new account for each test
	@Before
	public void setUp() {
		client = new Client("kata", "1234");
		account = new Account(ClientAccountsConstants.ACCOUNT_TYPE_SAVING, Double.valueOf(0), client);
		assertThat(account).isNotNull();
		assertThat(client).isNotNull();
		assertThat(account.getClient()).isNotNull();
		assertThat(account.getAccountId()).isNotNull();
		assertThat(client.getClientId()).isNotNull();
	}

	// check if new balance equals old balance + amount after deposit
	@Test
	public void shouldUpdateBalanceWhenDeposit() {
		account.setBalance(2.0);
		account.makeADeposit(4.0);
		assertEquals(Double.valueOf(6), account.getBalance());
	}

	// check if new balance equals old balance - amount after withdrawal
	@Test
	public void shouldUpdateBalanceWhenWithdrawal() {
		account.setBalance(2.0);
		account.makeAWithdrawal(1.0);
		assertEquals(Double.valueOf(1), account.getBalance());
	}

	// check if a new operation was added to the account after withdrawal
	@Test
	public void shouldAddToOperationsWhenWithdarwal() {
		account.setBalance(6.0);
		int numberOfOperations = account.getOperations().size();
		account.makeAWithdrawal(4.0);
		assertEquals(numberOfOperations + 1, account.getOperations().size());
	}

	// check if a new operation was added to the account after deposit
	@Test
	public void shouldAddToOperationsWhenDeposit() {
		account.setBalance(6.0);
		int numberOfOperations = account.getOperations().size();
		account.makeADeposit(4.0);
		assertEquals(numberOfOperations + 1, account.getOperations().size());
	}

	/*
	 * check if new balance equals old balance after withdrawal of an amount greater
	 * than balance
	 */
	@Test
	public void shouldNotAddToOperationsWhenWithdarwalIsFailed() {
		account.setBalance(2.0);
		int numberOfOperations = account.getOperations().size();
		account.makeAWithdrawal(4.0);
		assertEquals(numberOfOperations, account.getOperations().size());
	}

	// test operation history display
	@Test
	public void testOperationsDisplay() {
		account.setBalance(2.0);
		account.makeADeposit(19.0);
		account.makeAWithdrawal(0.0);
		account.makeAWithdrawal(2.0);
		account.makeAWithdrawal(8.0);
		account.consultPreviousOperations(client);
	}

	// do Deposit and withdrawal tests with double accounts in one client
	@Test
	public void checkIfDoubleAccountWorksPerfectly() {
		client.getAccounts().get(0).makeADeposit(40.0);
		client.getAccounts().get(1).makeADeposit(4.0);
		client.getAccounts().get(0).makeAWithdrawal(16.0);
		client.getAccounts().get(1).makeAWithdrawal(1.0);
		assertEquals(Double.valueOf(24), client.getAccounts().get(0).getBalance());
		assertEquals(Double.valueOf(3), client.getAccounts().get(1).getBalance());
	}
}
