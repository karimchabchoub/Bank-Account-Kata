package com.bank.BankAccount.BDDTests.WithdrawalOperationsTests;

import org.junit.Assert;
import com.bank.BankAccount.Model.Client;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AccountWithdrawalBDDTest {
	
	Client client = new Client("Kata", "1234");
	
	@Given("^we have a bank account with a balance of 50$")
	public void we_have_a_bank_account_with_a_balance_of_50() throws Throwable {
		client.getAccounts().get(0).setBalance(50.0);
	}

	@When("^we make a withdrawal of (\\d+)$")
	public void we_make_a_withdrawal_of(int arg1) throws Throwable {
		client.getAccounts().get(0).makeAWithdrawal(Double.valueOf(arg1));
	}

	@Then("^we would get a balance equals to 46$")
	public void we_would_get_a_balance_equals_to_46() throws Throwable {
		Assert.assertEquals(Double.valueOf(46), client.getAccounts().get(0).getBalance());
	}
}
