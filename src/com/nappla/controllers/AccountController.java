package com.nappla.controllers;

import com.nappla.daos.AccountDAO;
import com.nappla.exceptions.AccountNotFound;
import com.nappla.exceptions.EmptyValueException;
import com.nappla.exceptions.NotEnoughCashException;
import com.nappla.exceptions.UnexpectedLimitException;
import com.nappla.models.Account;
import com.nappla.views.Menu;
import com.nappla.views.WarningDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AccountController {
    private Account account;
    private Menu menu;

    public AccountController(Account account, Menu menu) {
        this.account = account;
        this.menu = menu;

        this.menu.setAccount(account);
        this.menu.cashDepositListener(new CashDepositListener());
        this.menu.cashExtractListener(new CashExtractListener());
        this.menu.increaseLimitListener(new LimitIncreaseListener());
    }

    class CashDepositListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            double amount = menu.getCashDeposit();
            try {
                AccountDAO accountDAO = new AccountDAO();
//                account.canDeposit(amount);
                accountDAO.cashDeposit(account, amount);
                String password = JOptionPane.showInputDialog(menu, "Insira sua senha");
                Account accountUp = accountDAO.getAccount(account.getAccountNumber(), Integer.parseInt(password));
                menu.setAccount(accountUp);
                menu.repaint();
            } catch (AccountNotFound e) {
                new WarningDialog(menu, "Conta não encontrada");
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }
    class CashExtractListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            double amount = menu.getCashExtract();
            try {
                AccountDAO accountDAO = new AccountDAO();
                account.canExtract(amount);
                accountDAO.cashExtract(account, amount);
                String password = JOptionPane.showInputDialog(menu,"Insira sua senha");
                Account accountUp = accountDAO.getAccount(account.getAccountNumber(), Integer.parseInt(password));
                menu.setAccount(accountUp);
                menu.repaint();
            } catch (NotEnoughCashException e){
                new WarningDialog(menu, "Saldo insuficiente");
            }
            catch (Exception e){
                new WarningDialog(menu, "Senha incorreta ou conta não encontrada");
                e.printStackTrace();
            }
        }
    }

    class LimitIncreaseListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int limit = menu.getIncreaseLimit();
            try {
                AccountDAO accountDAO = new AccountDAO();
                account.canIncreaseLimit(limit);
                accountDAO.increaseLimit(account, limit);
                String password = JOptionPane.showInputDialog(menu,"Insira sua senha");
                Account accountUp = accountDAO.getAccount(account.getAccountNumber(), Integer.parseInt(password));
                menu.setAccount(accountUp);
                menu.repaint();
            } catch (UnexpectedLimitException e){
                new WarningDialog(menu, "Insira um limite maior que o atual");
            }catch ( AccountNotFound e){
                new WarningDialog(menu, "Conta não econtrada");
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }
}
