package com.nappla.models;

import com.nappla.exceptions.EmptyValueException;
import com.nappla.exceptions.NotEnoughCashException;
import com.nappla.exceptions.UnexpectedLimitException;

import java.util.ArrayList;

public class Account {

    public Account(){
    }

    @MyColumn(position = 0, name = "ID")
    private int id;
    @MyColumn(position = 1, name = "ACCOUNT NUMBER")
    private int accountNumber;
    @MyColumn(position = 2, name = "NAME")
    private String name;
    @MyColumn(position = 3, name = "PASSWORD")
    private int password;
    @MyColumn(position = 4, name = "BALANCE")
    private double balance;
    @MyColumn(position = 5, name = "LIMIT")
    private int limit;
    @MyColumn(position = 6, name = "IS_ADMIN")
    private boolean isAdmin;

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setIsAdmin(boolean value){
        this.isAdmin = value;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccounts(ArrayList<Account> accounts) {
    }

    public void canDeposit(double amount) throws EmptyValueException {
        if (amount > 0) {
            balance += amount;
        } else {
            throw new EmptyValueException();
        }
    }

    public void canExtract(double amount) throws NotEnoughCashException {
        if (amount <= this.balance) {
            System.out.println(amount);
            System.out.println(this.balance);
//            this.balance -= amount;
        } else {
            throw new NotEnoughCashException();
        }
    }

    public void canIncreaseLimit(int limitValue) throws UnexpectedLimitException {
        if (limitValue > limit) {
//        TODO: handle bank increaseLimit avaliation from the bank
            limit = limitValue;
        } else {
            throw new UnexpectedLimitException();
        }
    }
}
