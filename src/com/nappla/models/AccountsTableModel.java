package com.nappla.models;

import com.nappla.daos.AccountDAO;
import com.nappla.exceptions.AccountNotFound;

//import javax.swing.event.TableModelEvent;
//import javax.swing.event.TableModelListener;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Random;
//
//public class AccountsTableModel extends GenericTableModel<Account> implements TableModelListener{
//
//    AccountDAO DAO;
//    List<Account> accounts;
//
//    public AccountsTableModel(AccountDAO dao){
//        super(dao);
//        this.DAO = dao;
//        accounts = dao.retrieve();
//
//        this.addTableModelListener(this);
//    }
//
//    @Override
//    public boolean isCellEditable(int rowIndex, int columnIndex) {
//        return columnIndex > 1;
//    }
//
//    @Override
//    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        Account account = accounts.get(rowIndex);
//        switch (columnIndex){
////          Not necessary to change the accountnumber
////            case 1:
////                account.setAccountNumber((int)aValue);
////                break;
//            case 2:
//                account.setName((String)aValue);
//                break;
//            case 3:
//                account.setBalance(Double.parseDouble((String) aValue));
//                break;
//            case 4:
//                account.setLimit(Integer.parseInt((String) aValue));
//                break;
//            case 5:
//                account.setIsAdmin(Boolean.parseBoolean((String) aValue));
//                break;
//        }
//        fireTableCellUpdated(rowIndex,columnIndex);
//    }
//
//    @Override
//    public void tableChanged(TableModelEvent tableModelEvent) {
//        int i = tableModelEvent.getFirstRow();
//        Account account = accounts.get(i);
//        DAO.update(account);
//    }
//
//    public void addAccount(String name, int password) throws SQLException {
//        Account account = new Account();
//
//        Random random = new Random();
//        int accountNumber = getRandomNumberInRange(1,9999);
//        account.setAccountNumber(accountNumber);
//        account.setName(name);
//        account.setPassword(password);
//
//        AccountDAO accountDAO = new AccountDAO();
//        accountDAO.create(account);
//        fireTableDataChanged();
//    }
//
//    private static int getRandomNumberInRange(int min, int max) {
//
//        if (min >= max) {
//            throw new IllegalArgumentException("Valor máximo excedido");
//        }
//
//        Random r = new Random();
//        return r.nextInt((max - min) + 1) + min;
//    }
//}
