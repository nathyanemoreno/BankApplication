package com.nappla.controllers;

import com.nappla.daos.AccountDAO;
import com.nappla.exceptions.AccountNotFound;
import com.nappla.models.Account;
import com.nappla.views.Auth;
import com.nappla.views.Login;
import com.nappla.views.Menu;
import com.nappla.views.WarningDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private Account account;
    private Auth auth;
    private Login login;
    private JPanel loginPane;

    public LoginController(Account account, Auth auth) {
        this.account = account;
        this.auth = auth;
        this.login = auth.getLogin();

        login.loginActionListener(new LoginListener());
    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int accountNumber;
            int password;

            try {
                accountNumber = login.getLoginAccountNumber();
                password = login.getPasswordLoginField();
                AccountDAO accountDAO = new AccountDAO();
                Account account = accountDAO.getAccount(accountNumber, password);

                Menu menu = new Menu();
                AccountController accountController = new AccountController(account, menu);

                if (!account.getIsAdmin()) {
//                     Remove adminpanel
                    menu.getMenu().remove(1);
                } else {
                    auth.onClose();
                    AdminAccountController adminAccountController = new AdminAccountController(account, menu);
                    menu.renderAdminTab();
                }

            } catch (AccountNotFound e) {
                new WarningDialog(login, "Conta não encontrada");
            } catch (NumberFormatException e) {
                new WarningDialog(login, "Por favor insira um número de conta válido");
            }
        }
    }
}

