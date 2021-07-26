package com.nappla;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Bank {
    private static Bank instance;
    //    public Account account;
    private String bankName;
    private final List<Account> accounts = new ArrayList<>();

    public Bank() {

    }

    public static Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }

    private Account getAccount(int accountNumber) {
        for (Account account : accounts) {
            if (Objects.equals(account.getId(), accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public Account createAccount(int accountNumber, String name) throws AccountCreationException, AccountExistis, AccountNotFound {
        try {
            getAccountInstance(accountNumber);
            throw new AccountExistis();
        } catch (AccountNotFound e) {
            Account account = new Account(accountNumber, name);
            accounts.add(account);
            return account;
        }
    }

    public Account getAccountInstance(int accountNumber) throws AccountNotFound {
        Account account = getAccount(accountNumber);
        if (account != null) {
            return account;
        } else {
            throw new AccountNotFound();
        }
    }

    public void accountCashExtract(int accountNumber, double amount) throws EmptyValueException, NotEnoughCashException, AccountNotFound {
        Account account = getAccount(accountNumber);
        if (account != null) {
            if (amount > 0.0) {
                account.cashExtract(amount);
            } else {
                throw new EmptyValueException();
            }
        } else {
            throw new AccountNotFound();
        }
    }

    public void accountCashDeposit(int accountNumber, double amount) throws EmptyValueException, AccountNotFound {
        Account account = getAccount(accountNumber);
        if (account != null) {
            if (amount > 0.0) {
                account.cashDeposit(amount);
            } else {
                throw new EmptyValueException();
            }
        } else {
            throw new AccountNotFound();
        }
    }

    public double getBalance(int accountNumber) throws AccountNotFound {
        Account account = getAccount(accountNumber);
        return account.getBalance();
    }

    public int getLimitAccount(int accountNumber) throws AccountNotFound {
        Account account = getAccount(accountNumber);
        return account.getLimit();
    }

    public void increaseLimit(int accountNumber, int limit) throws EmptyValueException, AccountNotFound, UnexpectedLimitException {
        Account account = getAccount(accountNumber);
        if (account != null) {
            if (limit > 0) {
                account.increaseAccountLimit(limit);
            } else {
                throw new EmptyValueException();
            }
        } else {
            throw new AccountNotFound();
        }
    }

    public double getAccountBalance(int accountNumber) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            return account.getBalance();
        }
        return 0;
    }

    public String getBankName() {
        return bankName;
    }

    public List getAccounts() {
        return accounts;
    }
}
