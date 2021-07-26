package com.nappla;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Auth extends JFrame {
    private JFormattedTextField newAccountNumber;
    private JFormattedTextField username;
    private JButton createAccountButton;
    private JFormattedTextField loginAccountNumber;
    private JButton loginButton;
    private JPanel mainPanel;
    private JLabel enterAccountNumberLabel;
    private JLabel numberLabel;
    private JLabel nameLabel;
    private JTabbedPane jTabbedPanel;
    private JPanel secondPane;
    private final Bank bank;

    public Auth(String title) {
        super(title);
        this.bank = Bank.getInstance();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(new Color(0xF3F3F3));
        setContentPane(mainPanel);
        pack();
        setVisible(true);

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!Objects.equals(username.getText(), "")) {
                    try {
                        Account account = bank.createAccount(Integer.parseInt(newAccountNumber.getText()), username.getText());
//                        System.out.println(account.getName());
                        new WarningDialog(mainPanel, "Conta criada");
                        jTabbedPanel.setSelectedIndex(0);
                    } catch (AccountCreationException e) {
                        new DialogError(mainPanel, new Label("Erro ao criar conta", 18));
                    } catch (AccountExistis accountExistis) {
                        new DialogError(mainPanel, new Label("Conta já existe", 18));
                    } catch (AccountNotFound accountNotFound) {
                        new WarningDialog(mainPanel, "Conta não encontrada");
                    } catch (NumberFormatException e) {
                        new WarningDialog(mainPanel, "Preencha todos os campos");
                    }
                } else {
                    new WarningDialog(mainPanel, "Preencha todos os campos");
                }
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Account account = bank.getAccountInstance(Integer.parseInt(loginAccountNumber.getText()));
                    JFrame menu = new Menu(account);
                    menu.setVisible(true);
                    onClose();
                } catch (AccountNotFound e) {
                    new WarningDialog(mainPanel, "Conta não encontrada");
                } catch (NumberFormatException e) {
                    new WarningDialog(mainPanel, "Por favor insira um número de conta válido");
                }
            }
        });
    }

    public static void main(String[] args) {
    }

    private void onClose() {
        dispose();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        enterAccountNumberLabel = new Label();
        newAccountNumber = new TextField();
        username = new TextField();
        nameLabel = new Label();
        numberLabel = new Label();
        loginButton = new Button();
        createAccountButton = new Button();
        loginAccountNumber = new TextField();
        jTabbedPanel = new Tab();
    }
}
