package com.nappla.views;

import com.nappla.components.Button;
import com.nappla.components.Label;
import com.nappla.components.PasswordField;
import com.nappla.components.TextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Login extends JPanel {
    private static JFrame frame;
    private JLabel accountNumberLabel;
    private JFormattedTextField loginAccountNumberField;
    private JLabel passwordLoginLabel;
    private JPasswordField passwordLoginField;
    private JButton loginButton;
    private JPanel loginPane;

    public Login() {
        setBackground(new Color(0xF3F3F3));
        setVisible(true);
        loginAccountNumberField.requestFocus();
    }

    public int getLoginAccountNumber() {
        return Integer.parseInt(loginAccountNumberField.getText());
    }

    public int getPasswordLoginField() {
        return Integer.parseInt(String.valueOf(passwordLoginField.getPassword()));
    }

    public void createUIComponents(){
        accountNumberLabel = new Label();
        loginAccountNumberField = new TextField();
        passwordLoginLabel = new Label();
        passwordLoginField = new PasswordField();
        loginButton = new Button();
    }

    public void loginActionListener(ActionListener actionListener){
        loginButton.addActionListener(actionListener);
    }
}
