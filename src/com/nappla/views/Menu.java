package com.nappla.views;

import com.nappla.components.Button;
import com.nappla.components.Label;
import com.nappla.components.TextField;
import com.nappla.controllers.CreateAccountController;
import com.nappla.controllers.LoginController;
import com.nappla.daos.AccountDAO;
import com.nappla.exceptions.EmptyValueException;
import com.nappla.models.Account;
import com.nappla.models.GenericTableModel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class Menu extends JFrame {
    private JPanel menuTab;
    private JButton btnCashExtract;
    private JButton btnCashDeposit;
    private JButton btnIncreaseLimit;
    private JFormattedTextField extractAmountTextField;
    private JFormattedTextField depositAmountTextField;
    private JFormattedTextField increaseLimitTextField;
    private JLabel accountBalance;
    private JLabel accountLimit;
    private JLabel feedbackLabel;
    private JLabel salutation;
    private JLabel nameLabel;
    private JLabel limitLabel;
    private JLabel balanceLabel;
    private JButton signOutButton;
    private JTable accountsTable;
    private JTabbedPane menuTabbedPanel;
    private JPanel menuPanel;
    private JScrollPane scrollPane;
    private JButton btnAddAccount;
    private JPanel tablePanel;
    private JPanel adminPanel;
    private Menu menu;
    private Account account;

    public Menu() {
        super("Menu");

        prepareGUI();
        signOutButton.addActionListener(actionEvent -> {
            Auth auth = new Auth();
            Account account = new Account();

            LoginController loginController = new LoginController(account, auth);
            CreateAccountController createAccountController = new CreateAccountController(account, auth);
            onClose();
        });
    }

    private void prepareGUI(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(new Color(0xF3F3F3));
        setContentPane(menuPanel);
        pack();
        setVisible(true);
    }

    public void setAccount(Account account){
        this.account = account;
        salutation.setText("Olá ");
        nameLabel.setText(account.getName());
        accountBalance.setText(String.valueOf(account.getBalance()));
        setLimit(account.getLimit());
    }

    public void cashExtractListener(ActionListener actionListener){
        btnCashExtract.addActionListener(actionListener);
    }

    public void cashDepositListener(ActionListener actionListener) {
        btnCashDeposit.addActionListener(actionListener);
    }

    public void increaseLimitListener(ActionListener actionListener){
        btnIncreaseLimit.addActionListener(actionListener);
    }

    public void addAccountListener(ActionListener actionListener) {
        btnAddAccount.addActionListener(actionListener);
    }

    public JTabbedPane getMenu() {
        return menuTabbedPanel;
    }

    public JLabel getBalanceLabel(){
        return balanceLabel;
    }

    public double getCashDeposit() throws EmptyValueException {
        if(!depositAmountTextField.getText().equals("")){
            return Double.parseDouble(depositAmountTextField.getText());
        }else{
            throw new EmptyValueException();
        }
    }

    public double getCashExtract() throws EmptyValueException{
        if(!extractAmountTextField.getText().equals("")){
            return Integer.parseInt(extractAmountTextField.getText());
        }else{
            throw new EmptyValueException();
        }
    }

    public int getIncreaseLimit() throws EmptyValueException{
        if(!increaseLimitTextField.getText().equals("")){
            return Integer.parseInt(increaseLimitTextField.getText());
        }else{
            throw new EmptyValueException();
        }
    }

    public void setBalance(double balance) {
        accountBalance.setText(String.valueOf(balance));
    }

    public void setLimit(double limit) {
        accountLimit.setText(String.valueOf(limit));
    }

    public void setName(double name) {
        nameLabel.setText(String.valueOf(name));
    }

    public void renderAdminTab(){
        AccountDAO accountDAO = new AccountDAO();
        AbstractTableModel accountsTableModel = new GenericTableModel<>(new AccountDAO()) {

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex > 1;
            }
        };
        accountsTable.setModel(accountsTableModel);
        accountsTable.setAutoCreateRowSorter(true);
    }

    private void renderFields(){
        increaseLimitTextField.addKeyListener(new MyKeyAdapter(this) {
            public void keyPressed(KeyEvent e) {
                keyPressed(e, increaseLimitTextField, feedbackLabel);
            }
        });

        extractAmountTextField.addKeyListener(new MyKeyAdapter(this) {
            public void keyPressed(KeyEvent e) {
                keyPressed(e, extractAmountTextField, feedbackLabel);
            }
        });

        depositAmountTextField.addKeyListener(new MyKeyAdapter(this) {
            public void keyPressed(KeyEvent e) {
                keyPressed(e, depositAmountTextField, feedbackLabel);
            }
        });
    }

    public void main(String[] args) {

    }


    private void createUIComponents() {
        salutation = new Label("Olá");
        nameLabel = new Label();
        feedbackLabel = new Label();
        limitLabel = new Label();
        balanceLabel = new Label();
        accountBalance = new Label();
        accountLimit = new Label();
        btnCashExtract = new Button();
        btnCashDeposit = new Button();
        btnIncreaseLimit = new Button();
        depositAmountTextField = new TextField();
        extractAmountTextField = new TextField();
        increaseLimitTextField = new TextField();
        signOutButton = new Button();
        accountsTable = new JTable();
        scrollPane = new JScrollPane();
        btnAddAccount = new Button();
    }

    private void onClose() {
        dispose();
    }

    private static class MyKeyAdapter extends KeyAdapter {
        private MyKeyAdapter(Component component) {

        }

        public void keyPressed(KeyEvent ke, JFormattedTextField field, JLabel label) {
            if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                field.setEditable(true);
                label.setText("");
            } else {
                field.setEditable(false);
                label.setForeground(Color.RED);
                label.setText("Only numeric values allowed");
            }
        }
    }
}
