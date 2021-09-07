package com.nappla.controllers;

import com.nappla.daos.AccountDAO;
import com.nappla.models.Account;
import com.nappla.views.Auth;
import com.nappla.views.CreateAccount;
import com.nappla.views.WarningDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CreateAccountController {
    private Account account;
    private Auth auth;
    private CreateAccount createAccount;

    public CreateAccountController(Account account, Auth auth) {
        this.account = account;
        this.auth = auth;
        this.createAccount = auth.getCreateAccount();

        this.createAccount.createAccountActionListener(new CreateAccountListener());
    }

    class CreateAccountListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Account account = new Account();
            int accountNumber = createAccount.getNewAccountNumber();
            int password = createAccount.getPasswordField();
            String name = createAccount.getName();

            account.setAccountNumber(accountNumber);
            account.setPassword(password);
            account.setName(name);

            AccountDAO accountDAO = new AccountDAO();
            try {
                accountDAO.create(account);
                new WarningDialog(auth,"Conta criada com sucesso");
            } catch (SQLException sqlException) {
                new WarningDialog(auth,"Erro ao criar conta");
                sqlException.printStackTrace();
            }
            auth.setTabFocus(Auth.LOGIN_TAB);

        }
    }

}
