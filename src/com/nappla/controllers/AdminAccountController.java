package com.nappla.controllers;

import com.nappla.AccountType;
import com.nappla.components.Label;
import com.nappla.daos.AccountDAO;
import com.nappla.models.Account;
//import com.nappla.models.AccountsTableModel;
import com.nappla.models.GenericTableModel;
import com.nappla.views.Auth;
import com.nappla.views.Menu;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminAccountController {
    Account account;
    Menu menu;

    public AdminAccountController(Account account, Menu menu){
        this.account = account;
        this.menu = menu;

        this.menu.addAccountListener(new AddAccountListener());
    }

    static class AddAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            Auth auth = new Auth();
            Account account = new Account();
            CreateAccountController createAccountController = new CreateAccountController(account, auth);
//            AbstractTableModel accountsTableModel = new GenericTableModel<>(new AccountDAO()) {
//                @Override
//                public boolean isCellEditable(int rowIndex, int columnIndex) {
//                    return columnIndex > 1;
//                }
//            };

//            Label jName = new Label("Name");
//            JTextField name = new JTextField();
//            Label jPassword = new Label("Password");
//            JPasswordField password = new JPasswordField();
//
//            Object[] obj = {jName, name, jPassword, password};
//            int result = JOptionPane.showConfirmDialog(null, obj, "Criar conta", JOptionPane.OK_CANCEL_OPTION);
//            if(result == JOptionPane.OK_OPTION){
//                String nameValue = name.getText();
//                char[] passwordValue = password.getPassword();
//                Account account = new Account();
//                account.setName(nameValue);
//                account.setPassword(Integer.parseInt(String.valueOf(passwordValue)));



//                try {
//                    AccountDAO accountDAO = new AccountDAO();
//                    accountDAO.create(account);
//                } catch (SQLException sqlException) {
//                    sqlException.printStackTrace();
//                }
//            }
        }
    }
}
