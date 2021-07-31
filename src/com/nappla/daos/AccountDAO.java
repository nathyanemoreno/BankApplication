package com.nappla.daos;

import com.nappla.AccountType;
import com.nappla.exceptions.AccountNotFound;
import com.nappla.models.Account;
import com.nappla.views.WarningDialog;

import java.sql.*;
import java.util.ArrayList;

public class AccountDAO implements AccountType {
    private Connection conn;
    private String url = "jdbc:postgresql://localhost:5432/bank";
    private String tableName = "accounts";
    private ArrayList<Account> accounts = new ArrayList<>();

    public AccountDAO() {
        try {
            conn = DriverManager.getConnection(url, "postgres", "postgres");
            System.out.println("conectado");
        } catch (SQLException excecao) {
            new WarningDialog(null, "Erro ao conectar ao banco de dados");
        }
    }

    public Account insertAccount(int accountNumber, String name, int password) {
        String sql = "INSERT INTO accounts(id, name, password, accnumber) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, accountNumber);
            stmt.setString(2, name);
            stmt.setInt(3, password);
            stmt.setInt(4, accountNumber);
            stmt.execute();
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        return null;
    }

    public Account getAccount(int accountNumber, int password) throws AccountNotFound {
        String sql = "SELECT * FROM accounts WHERE accnumber = ? AND password = ?";
        try {
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, accountNumber);
            stmt.setInt(2, password);
            ResultSet result = stmt.executeQuery();
            ResultSetMetaData data = result.getMetaData();
            int columnsNumber = data.getColumnCount();
            Account account = new Account();
            if (result.next()) {
                    setInfoAccount(result, columnsNumber, account);
            } else {
                throw new AccountNotFound();
            }
            result.close();
            stmt.close();
            return account;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        throw new AccountNotFound();
    }

    @Override
    public ArrayList<Account> getAccounts() {
            String sql = "SELECT * FROM " + tableName;
            try {
                PreparedStatement stmt = conn.prepareCall(sql);
                ResultSet result = stmt.executeQuery();
                ResultSetMetaData data = result.getMetaData();
                int columnsNumber = data.getColumnCount();
                    while (result.next()) {
                        Account account = new Account();
                        setInfoAccount(result, columnsNumber, account);
                        accounts.add(account);
                    }
                result.close();
                stmt.close();
                return accounts;
            } catch (SQLException sqlExcept) {
                sqlExcept.printStackTrace();
            }
        return null;
    }

    private void setInfoAccount(ResultSet result, int columnsNumber, Account account) throws SQLException {
        for (int i = 1; i < columnsNumber; i++) {
            account.setName(result.getString("name"));
            account.setBalance(result.getDouble("balance"));
            account.setLimit(result.getInt("acclimit"));
            account.setIsAdmin(result.getBoolean("is_admin"));
            account.setAccountNumber(result.getInt("accnumber"));
//                            String column = result.getString(i);
//                            System.out.println(column);
        }
    }

    public void cashDeposit(Account account, double amount) throws SQLException {
        String sql = "UPDATE " + tableName + " SET balance = CASE WHEN ? > 0 THEN balance + ? ELSE balance END WHERE accnumber = ?";
        operateAccount(account, amount, sql);
    }

    public void cashExtract(Account account, double amount) throws SQLException {
        String sql = "UPDATE " + tableName + " SET balance = CASE WHEN ? <= balance THEN balance - ? ELSE balance END WHERE accnumber = ?";
        operateAccount(account, amount, sql);
    }

    private void operateAccount(Account account, double amount, String sql) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(sql);
        try{
            stmt.setDouble(1, amount);
            stmt.setDouble(2, amount);
            stmt.setInt(3, account.getAccountNumber());
            stmt.execute();
            stmt.close();
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public void increaseLimit(Account account, int limit) throws SQLException {
        String sql = "UPDATE " + tableName + " SET acclimit = CASE WHEN ? > acclimit THEN ? ELSE acclimit END WHERE accnumber = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        try{
            stmt.setInt(1, limit);
            stmt.setInt(2, limit);
            stmt.setInt(3, account.getAccountNumber());
            stmt.execute();
            stmt.close();
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
