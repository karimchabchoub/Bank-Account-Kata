package com.bank.BankAccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bank.BankAccount.Model.Client;
import com.bank.BankAccount.constants.ClientAccountsConstants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class BankAccountApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(BankAccountApplication.class, args);
		
		// Simple test case with deposit , withdrawal and consulting previous operations
		Client client = new Client("Kataa", "1234");
		client.createNewAccount(ClientAccountsConstants.ACCOUNT_TYPE_SAVING, Double.valueOf(0));

		log.info(" start process for ... " + client.getClientName());

		client.getAccounts().get(0).makeADeposit(40.0);
		client.getAccounts().get(1).makeADeposit(4.0);

		client.getAccounts().get(0).makeAWithdrawal(16.0);
		client.getAccounts().get(1).makeAWithdrawal(1.0);

		client.getAccounts().get(0).consultPreviousOperations(client);
		client.getAccounts().get(1).consultPreviousOperations(client);

		log.info(" finish ... ");
	}

}
