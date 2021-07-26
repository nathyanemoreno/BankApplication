package com.nappla;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Menu extends JFrame {
    private JLabel salutationMessage;
    private JPanel mainPanel;
    private JButton cashExtractButton;
    private JButton depositButton;
    private JButton increaseLimitButton;
    private JFormattedTextField extractAmountTextField;
    private JFormattedTextField depositAmountTextField;
    private JFormattedTextField increaseLimitTextField;
    private JLabel accountBalance;
    private JLabel accountLimit;
    private JLabel feedbackLabel;
    private JLabel limitLabel;
    private JLabel balanceLabel;
    private JButton signOutButton;
    private final Account account;
    private final Bank bank;
    private final Menu menu;

    public Menu(Account account) {
        super("Menu");
        this.bank = Bank.getInstance();
        this.account = account;
        menu = this;
        salutationMessage.setText("Olá " + account.getName());
        accountBalance.setText(String.valueOf(account.getBalance()));
        accountLimit.setText(String.valueOf(account.getLimit()));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(new Color(0xF3F3F3));
        setContentPane(mainPanel);
        pack();

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

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    bank.accountCashDeposit(account.getId(), Double.parseDouble(depositAmountTextField.getText()));
//                    System.out.println(String.valueOf(account.getBalance()));
                    accountBalance.setText(String.valueOf(account.getBalance()));
                } catch (EmptyValueException e) {
                    new WarningDialog(mainPanel, "Insira um valor maior que 0");
                } catch (AccountNotFound accountNotFound) {
                    new WarningDialog(mainPanel, "Conta não encontrada");
                } catch (NumberFormatException e) {
                    new WarningDialog(mainPanel, "Insira um valor");
                }
            }
        });

        cashExtractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    bank.accountCashExtract(account.getId(), Double.parseDouble(extractAmountTextField.getText()));
//                    System.out.println(String.valueOf(account.getBalance()));
                    accountBalance.setText(String.valueOf(account.getBalance()));
                } catch (EmptyValueException e) {
                    new WarningDialog(mainPanel, "Insira um valor maior que 0");
                } catch (AccountNotFound accountNotFound) {
                    new WarningDialog(mainPanel, "Conta não encontrada");
                } catch (NotEnoughCashException e) {
                    new WarningDialog(mainPanel, "Saldo insuficiente");
                } catch (NumberFormatException e) {
                    new WarningDialog(mainPanel, "Insira um valor");
                }
            }
        });

        increaseLimitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    bank.increaseLimit(account.getId(), Integer.parseInt(increaseLimitTextField.getText()));
                    System.out.println(account.getLimit());
                    accountLimit.setText(String.valueOf(account.getLimit()));
                } catch (EmptyValueException e) {
                    new WarningDialog(mainPanel, "Insira um valor maior que 0");
                } catch (AccountNotFound accountNotFound) {
                    new WarningDialog(mainPanel, "Conta não encontrada");
                } catch (NumberFormatException e) {
                    new WarningDialog(mainPanel, "Insira um valor");
                } catch (UnexpectedLimitException e) {
                    new WarningDialog(mainPanel, "Insira um valor maior que o seu limite atual");
                }
            }
        });

        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int jOptionPane = JOptionPane.showConfirmDialog(menu, "Tem certeza que deseja sair?");
                if (jOptionPane == JOptionPane.YES_OPTION) {
                    Auth authFrame = new Auth("Nappla Bank");
                    onClose();
                }
            }
        });
    }

    public void main(String[] args) {
    }


    private void createUIComponents() {
        salutationMessage = new Label("Olá");
        feedbackLabel = new Label();
        limitLabel = new Label();
        balanceLabel = new Label();
        accountBalance = new Label();
        accountLimit = new Label();
        cashExtractButton = new Button();
        depositButton = new Button();
        increaseLimitButton = new Button();
        depositAmountTextField = new TextField();
        extractAmountTextField = new TextField();
        increaseLimitTextField = new TextField();
        signOutButton = new Button();
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
