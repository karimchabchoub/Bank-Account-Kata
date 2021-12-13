package com.bank.BankAccount.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bank.BankAccount.constants.ClientAccountsConstants;

public class Client extends Bank{
	
	private int clientId;
	private String clientName;
	private String clientPassword;
	private LocalDateTime date;
	private List<Account> accounts;
	
	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	// A client must at least have one account
	public Client(String clientName, String clientPassword) {
		super();
		int clientId = getNewId();
		this.clientId = clientId;
		this.clientName = clientName;
		this.clientPassword = clientPassword;
		this.date = LocalDateTime.now();
		this.accounts = new ArrayList<Account>();
		new Account(ClientAccountsConstants.ACCOUNT_TYPE_DEFAULT, 0.0, this );
	}
	
	public void saveNewAccount(Account account) {
		List<Account> newAccounts = getAccounts();
		newAccounts.add(account);
		setAccounts(newAccounts);
	}
	
	// create new account in a existent client and check if it has a unique account id 
	public void createNewAccount(String accountType, Double balance){
		new Account(accountType, balance, this);
	}
	
	// when date and id are equals then equals(Object obj) returns true
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		Client other = (Client) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (clientId == 0) {
			if (other.clientId != 0)
				return false;
		} else if (!(clientId == other.clientId))
			return false;
		return true;
	}
	
}
