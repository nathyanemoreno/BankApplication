package com.nappla.components;

import com.nappla.exceptions.AccountCreationException;
import com.nappla.exceptions.AccountNotFound;
import com.nappla.IButtonClickEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton implements ActionListener {
    public IButtonClickEvent btnClickEvent;
    public Button(String label, IButtonClickEvent e) {
        setText(label);
        setFont(new Font(getName(), Font.BOLD, 16));
        setSize(100,32);
        setBounds(16,16, 230, 48);
        setBackground(new Color(0x31313A));
        setForeground(Color.WHITE);
        setBorderPainted(false);
        setFocusPainted(false);
        addActionListener(this);

        this.btnClickEvent = e;
    }

    public Button(String label) {
        setText(label);
        setFont(new Font(this.getName(), Font.BOLD, 16));
        setSize(100, 32);
        setBounds(16, 16, 230, 48);
        setBackground(new Color(0x31313A));
        setForeground(Color.WHITE);
        setBorderPainted(false);
        setFocusPainted(false);
    }

    public Button() {
        setFont(new Font(this.getName(), Font.BOLD, 16));
        setSize(100, 32);
        setBounds(16, 16, 230, 48);
        setBackground(new Color(0x31313A));
        setForeground(Color.WHITE);
        setBorderPainted(false);
        setFocusPainted(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            this.btnClickEvent.onClick();
        } catch (AccountCreationException | AccountNotFound ex) {
            ex.printStackTrace();
        }
    }
}
