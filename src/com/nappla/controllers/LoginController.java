package com.nappla.controllers;

import com.nappla.components.DialogError;
import com.nappla.components.Label;
import com.nappla.daos.AccountDAO;
import com.nappla.exceptions.AccountCreationException;
import com.nappla.exceptions.AccountExistis;
import com.nappla.exceptions.AccountNotFound;
import com.nappla.models.Account;
import com.nappla.views.Auth;
import com.nappla.views.Menu;
import com.nappla.views.WarningDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class LoginController {
    private Account account;
    private Auth auth;

    public LoginController(Account account, Auth auth) {
        this.account = account;
        this.auth = auth;

        this.auth.loginActionListener(new LoginListener());
    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int accountNumber;
            int password;

            try {
                accountNumber = auth.getLoginAccountNumber();
                password = auth.getPasswordLoginField();
                AccountDAO accountDAO = new AccountDAO();
                Account account = accountDAO.getAccount(accountNumber, password);

                Menu menu = new Menu();
                AccountController accountController = new AccountController(account, menu);

                if(!account.getIsAdmin()){
//                     Remove adminpanel
                    menu.getMenu().remove(1);
                } else{
                    auth.onClose();
                    ArrayList<Account> accounts =  accountDAO.getAccounts();
                    menu.renderAdminTab(accounts);
                }

            } catch (AccountNotFound e) {
                    new WarningDialog(auth, "Conta não encontrada");
                } catch (NumberFormatException e) {
                    new WarningDialog(auth, "Por favor insira um número de conta válido");
                }
//
//
////                        Account account = account.createAccount(
////                                Integer.parseInt(newAccountNumber.getText()),
////                                username.getText(),
////                                Integer.parseInt(String.valueOf(passwordField.getPassword())));
////                        System.out.println(account.getName());
//
//                new WarningDialog(auth, "Conta criada");
////                        jTabbedPanel.setSelectedIndex(0);
//            } catch (AccountCreationException e) {
//                new DialogError(auth, new Label("Erro ao criar conta", 18));
//            } catch (AccountExistis accountExistis) {
//                new DialogError(auth, new Label("Conta já existe", 18));
//            } catch (AccountNotFound accountNotFound) {
//                new WarningDialog(auth, "Conta não encontrada");
//            } catch (NumberFormatException e) {
//                new WarningDialog(auth, "Preencha todos os campos");
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        } else {
//            new WarningDialog(auth, "Preencha todos os campos");
//        }
        }
    }
}

