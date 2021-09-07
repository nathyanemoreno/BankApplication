package com.nappla.views;

import com.nappla.components.*;
import com.nappla.components.Button;
import com.nappla.components.Label;
import com.nappla.components.TextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreateAccount extends JPanel {
    private JLabel accountNumberLabel;
    private JLabel nameLabel;
    private JFormattedTextField accountNumberField;
    private JFormattedTextField nameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton createAccountButton;
    private JPanel createAccountPane;

    public CreateAccount () {
        setBackground(new Color(0xF3F3F3));
        setVisible(true);
    }

    public String getName() {
        return nameField.getText();
    }

    public int getNewAccountNumber() {
        return Integer.parseInt(accountNumberField.getText());
    }

    public int getPasswordField() {
        return Integer.parseInt(String.valueOf(passwordField.getPassword()));
    }

    private void createUIComponents() {
        nameField = new TextField();
        accountNumberField = new TextField();
        passwordField = new PasswordField();
        createAccountButton = new Button();
        passwordLabel = new Label();
        accountNumberLabel = new Label();
        nameLabel = new Label();
    }

    public void createAccountActionListener(ActionListener actionListener){
        createAccountButton.addActionListener(actionListener);
    }
}
