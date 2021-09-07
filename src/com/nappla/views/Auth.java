package com.nappla.views;

import com.nappla.components.TabPanel;

import javax.swing.*;
import java.awt.*;

public class Auth extends JFrame {
    public static final int LOGIN_TAB = 0;
    public static final int CREATE_ACCOUNT_TAB = 1;
    private JPanel authPanel;
    private JTabbedPane Tabs;
    private Login login;
    private JPanel createAccountPanel;
    private CreateAccount createAccount;
    private JPanel loginPanel;

    public Auth() {
        super("NapplaBank");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(new Color(0xF3F3F3));
        setContentPane(authPanel);
        pack();
        setVisible(true);
    }

    public void onClose() {
        dispose();
    }

    public Login getLogin() {
        return login;
    }

    private void createUIComponents() {
        Tabs = new TabPanel();
        login = new Login();
    }

    public void setTabFocus(int tabIndex) {
        Tabs.setSelectedIndex(tabIndex);
    }

    public CreateAccount getCreateAccount() {
        return createAccount;
    }
}
