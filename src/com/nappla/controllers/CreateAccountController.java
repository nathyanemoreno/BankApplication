package com.nappla.controllers;

import com.nappla.daos.AccountDAO;
import com.nappla.models.Account;
import com.nappla.views.Auth;
import com.nappla.views.WarningDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountController {
    private Account account;
    private Auth auth;

    public CreateAccountController(Account account, Auth auth) {
        this.account = account;
        this.auth = auth;

        this.auth.createAccountActionListener(new CreateAccountListener());
    }

    class CreateAccountListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int accountNumber = auth.getNewAccountNumber();
            int password = auth.getPasswordField();
            String name = auth.getName();

            AccountDAO accountDAO = new AccountDAO();
            Account account = accountDAO.insertAccount(accountNumber,name,password);

            new WarningDialog(auth,"Conta criada com sucesso");

            auth.setTabFocus(Auth.LOGIN_TAB);

        }
    }

}
