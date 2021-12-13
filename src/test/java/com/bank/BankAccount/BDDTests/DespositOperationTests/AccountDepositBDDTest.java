package com.bank.BankAccount.BDDTests.DespositOperationTests;

import org.junit.Assert;
import com.bank.BankAccount.Model.Client;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AccountDepositBDDTest {
	
	Client client ;
	
	@Given("^we have a bank account with a balance of 0$")
	public void we_have_a_bank_account_with_a_balance_of_0() throws Throwable {
		client = new Client("Kata", "1234");
	}

	@When("^we make a deposit of (\\d+)$")
	public void we_make_a_deposit_of(int arg1) throws Throwable {
		client.getAccounts().get(0).makeADeposit(Double.valueOf(arg1));
	}

	@Then("^we would get a balance equals to 50$")
	public void we_would_get_a_balance_equals_to_50() throws Throwable {
		Assert.assertEquals(Double.valueOf(50), client.getAccounts().get(0).getBalance());
	}

}
