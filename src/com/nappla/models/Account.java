package com.nappla.models;

import com.nappla.*;
import com.nappla.daos.AccountDAO;
import com.nappla.exceptions.AccountNotFound;
import com.nappla.exceptions.EmptyValueException;
import com.nappla.exceptions.NotEnoughCashException;
import com.nappla.exceptions.UnexpectedLimitException;

import java.sql.*;
import java.util.ArrayList;

public class Account {
    private int accountNumber;
    private String name;
    private int password;
    private double balance;
    private int limit;
    private boolean isAdmin;
    private ArrayList<Account> accounts = new ArrayList<>();

    private static Connection conn = null;
    private static Statement stmt = null;
    public static String tableName = "accounts";

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getId() throws AccountNotFound {
        // Will retrive the id of the account
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.getAccount(accountNumber, password);
        return accountNumber;
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

    public void setAccounts(ArrayList<Account> accounts) {
    }

    public void canDeposit(double amount) throws EmptyValueException {
        if (amount > 0) {
//            balance += amount;
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
