package com.nappla;

public class Account {
    private final int id;
    private final String name;
    private double accountBalance = 0; // initial value on account
    private int accountLimit = 800; //initial limit to all accounts

    public Account(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public boolean cashExtract(double amount) throws NotEnoughCashException {
        if (amount <= this.accountBalance) {
            accountBalance -= amount;
        } else {
            throw new NotEnoughCashException();
        }
        return false;
    }

    public boolean cashDeposit(double amount) throws EmptyValueException {
        if (amount > 0) {
            accountBalance += amount;
        } else {
            throw new EmptyValueException();
        }
        return false;
    }

    public void increaseAccountLimit(int limit) throws UnexpectedLimitException {
        if (limit > accountLimit) {
//        TODO: handle bank increaseLimit avaliation from the bank
            accountLimit = limit;
        } else {
            throw new UnexpectedLimitException();
        }
    }

    public int getLimit() {
        return accountLimit;
    }

    public double getBalance() {
        return accountBalance;
    }

    public String getName() {
        return name;
    }
}
