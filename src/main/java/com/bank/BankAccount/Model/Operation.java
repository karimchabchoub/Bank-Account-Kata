package com.bank.BankAccount.Model;

import java.time.LocalDateTime;

public class Operation {

	private int operationId;
	private String operationName;
	private String operationType;
	private int accountId;
	private LocalDateTime date;
	private Double amount;
	private Double balanceBeforeOperation; 
	private Double balanceAfterOperation; 
	
	public Operation(int operationId, String operationName, String operationType, int accountId, LocalDateTime date,
			Double amount, Double balanceBeforeOperation, Double balanceAfterOperation) {
		this.operationId = operationId;
		this.operationName = operationName;
		this.operationType = operationType;
		this.accountId = accountId;
		this.date = date;
		this.amount = amount;
		this.balanceBeforeOperation = balanceBeforeOperation;
		this.balanceAfterOperation = balanceAfterOperation;
	}

	public Double getBalanceBeforeOperation() {
		return balanceBeforeOperation;
	}

	public void setBalanceBeforeOperation(Double balanceBeforeOperation) {
		this.balanceBeforeOperation = balanceBeforeOperation;
	}

	public Double getBalanceAfterOperation() {
		return balanceAfterOperation;
	}

	public void setBalanceAfterOperation(Double balanceAfterOperation) {
		this.balanceAfterOperation = balanceAfterOperation;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public int getOperationId() {
		return operationId;
	}

	public void setOperationId(int operationId) {
		this.operationId = operationId;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	
	// when date and id are equals then equals(Object obj) returns true
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		Operation other = (Operation) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (operationId == 0) {
			if (other.operationId != 0)
				return false;
		} else if (!(operationId == other.operationId))
			return false;
		return true;
	}
	
	// Here you can choose how to display the operations history
	public String displayOperationHistory() {
		return " | " + getOperationType() + " | " + getDate()
		+ " | " + getAmount() + " | "
		+ getBalanceBeforeOperation() + " | "
		+ getBalanceAfterOperation() + " | \n" ;
	}

}
