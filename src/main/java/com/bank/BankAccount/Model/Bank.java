package com.bank.BankAccount.Model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;

import com.bank.BankAccount.constants.BankAccountConstants;

public abstract class Bank {

	public static final String bankName = BankAccountConstants.BANK_NAME;
	
	public static String getBankName() {
		return bankName;
	}

	public Bank() {
		super();
	}

	public int getNewId() {
		ZoneId zoneId = ZoneId.systemDefault();
		return Math.abs((new Random()).nextInt() + (int) LocalDateTime.now().atZone(zoneId).toEpochSecond());
	}

}
