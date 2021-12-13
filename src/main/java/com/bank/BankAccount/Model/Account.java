package com.bank.BankAccount.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bank.BankAccount.constants.ClientAccountsConstants;
import com.bank.BankAccount.constants.ClientOperationsContants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Account extends Bank{

	private int accountId;
	private String accountType;
	private Client client;
	private Double balance;
	private List<Operation> operations;
	
	public Account(Client client) {
		super();
		this.client = client;
		setOperations(new ArrayList<Operation>());
		setBalance(0.0);
		client.saveNewAccount(this);
	}
	
	public Account(String accountType, Double balance, Client client) {
		super();
		this.accountId = getNewId();
		this.accountType = accountType;
		this.balance = balance;
		this.client = client;
		this.operations = new ArrayList<Operation>();
		
		while(client.getAccounts().size() > 0 &&
				client.getAccounts().stream().filter(data -> data.getAccountId() == getAccountId()).collect(Collectors.toList()).size() > 0
				) {
			setAccountId(getNewId());
		}
		client.saveNewAccount(this);
	}
	
	public Double getBalance() {
		return balance;
	}
	
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}


	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public void saveNewOperation(Operation operation) {
		List<Operation> newOperations = getOperations();
		newOperations.add(operation);
		setOperations(newOperations);
	}
	
	// if another operation in the same account has the same id then we generate a new operation Id
	public void checkIfAnotherOperationHasTheSameId(Operation operation) {
		while(getOperations().size() > 0 &&
				getOperations().stream().filter(data -> data.getOperationId() == operation.getOperationId()).collect(Collectors.toList()).size() > 0
				) {
			operation.setOperationId(getNewId());
		}
	}

	// Function that allows client to check his operations history 
	public void consultPreviousOperations(Client client) {

		log.info("--- Welcome to bank " + getBankName() + " ---");
		log.info("--- Account operations log started = " + getAccountId() + " for " + client.getClientName() + " ---");

		StringBuilder accountLog = new StringBuilder();
		
		accountLog.append("\n \n --- Account operations log --- \n");
		accountLog.append("\n | Operation Type | Operation Date | Amount | Balance before operation | Balance after operation | \n");
		getOperations().stream().forEach((operation) -> {
			accountLog.append(operation.displayOperationHistory());
		});

		log.info(accountLog.toString());
		log.info("*** Account operations finish, thank you, see you soon ***");

	}

	// Function that allows client to retrieve an amount from his account  
	public void makeAWithdrawal(Double amount) {

		log.info("--- Make a withdrawal for client " + getClient().getClientName() + " and account " + getAccountType());

		Double balanceBeforeOperation = getBalance();
		
		// do retrieve if you have enough money else return a warning message
		if(balanceBeforeOperation > amount) {
			
			log.info(" Valid request : ");
			
			Double balanceAfterOperation = getBalance() - amount;
			setBalance(balanceAfterOperation);
			Operation operation = new Operation(getNewId(),
					getAccountId() + " withdrawal amount equals to " + amount,
					ClientOperationsContants.RETRIEVE_OPERATION_TYPE, getAccountId(), LocalDateTime.now(), amount,
					balanceBeforeOperation, balanceAfterOperation);
			checkIfAnotherOperationHasTheSameId(operation);
			saveNewOperation(operation);
			log.info(" Done with success. New Balance is : " + balanceAfterOperation);
		} else {
			log.warn(ClientAccountsConstants.AMOUNT_EXCEED_BALANCE);
		}
		
	}

	// Function that allows client to deposit an amount to his account  
	public void makeADeposit(Double amount) {

		log.info("--- Make a desposit for client " + getClient().getClientName() + " and account " + getAccountType());

		Double balanceBeforeOperation = getBalance();
		Double balanceAfterOperation = getBalance() + amount;
		setBalance(balanceAfterOperation);
		Operation operation = new Operation(getNewId(), getAccountId() + " desposit amount equals to " + amount,
				ClientOperationsContants.DEPOSIT_OPERATION_TYPE, getAccountId(), LocalDateTime.now(), amount,
				balanceBeforeOperation, balanceAfterOperation);
		checkIfAnotherOperationHasTheSameId(operation);
		saveNewOperation(operation);

		log.info(" Done with success. New Balance is : " + balanceAfterOperation);

	}

}
