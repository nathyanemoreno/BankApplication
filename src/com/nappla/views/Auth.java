package com.nappla.views;

import com.nappla.components.*;
import com.nappla.components.Button;
import com.nappla.components.Label;
import com.nappla.components.TextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Auth extends JFrame {
    public static final int LOGIN_TAB = 0;
    public static final int CREATE_ACCOUNT_TAB = 1;
    private JFormattedTextField newAccountNumber;
    private JFormattedTextField name;
    private JButton createAccountButton;
    private JFormattedTextField loginAccountNumber;
    private JButton loginButton;
    private JPanel authPanel;
    private JLabel accountNumberLabel;
    private JLabel numberLabel;
    private JLabel nameLabel;
    private JTabbedPane jTabbedPanel;
    private JPanel secondPane;
    private JPasswordField passwordField;
    private JLabel passwordLabel;
    private JPasswordField passwordLoginField;
    private JLabel passwordLoginLabel;

    public Auth() {
        super("NapplaBank");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(new Color(0xF3F3F3));
        setContentPane(authPanel);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {

    }

    public void loginActionListener(ActionListener actionListener){
        loginButton.addActionListener(actionListener);
    }

    public void createAccountActionListener(ActionListener actionListener){
        createAccountButton.addActionListener(actionListener);
    }

    public String getName() {
        return name.getText();
    }

    public int getNewAccountNumber() {
        return Integer.parseInt(newAccountNumber.getText());
    }

    public int getLoginAccountNumber() {
        return Integer.parseInt(loginAccountNumber.getText());
    }

    public int getPasswordField() {
        return Integer.parseInt(String.valueOf(passwordField.getPassword()));
    }

    public int getPasswordLoginField() {
        return Integer.parseInt(String.valueOf(passwordLoginField.getPassword()));
    }

    public void onClose() {
        dispose();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        name = new TextField();
        newAccountNumber = new TextField();
        loginAccountNumber = new TextField();
        passwordField = new PasswordField();
        passwordLoginField = new PasswordField();
        createAccountButton = new Button();
        loginButton = new Button();
        accountNumberLabel = new Label();
        passwordLabel = new Label();
        numberLabel = new Label();
        nameLabel = new Label();
        passwordLoginLabel = new Label();
        jTabbedPanel = new Tab();
    }

    public void setTabFocus(int tabIndex) {
        jTabbedPanel.setSelectedIndex(tabIndex);
    }
}
