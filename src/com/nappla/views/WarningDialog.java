package com.nappla.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WarningDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonClose;
    private JPanel labelPane;
    private JLabel labelMessage;

    public WarningDialog(Component jComponentMessage, String message) {
        setSize(300, 200);
        setLocationRelativeTo(jComponentMessage);
        setResizable(false);
        setLocationRelativeTo(jComponentMessage);
        setContentPane(contentPane);
        setTitle("Warning");
        setModal(true);
        getRootPane().setDefaultButton(buttonClose);

        labelMessage.setText(message);
        buttonClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onClose();
            }
        });

        // call onClose() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onClose();
            }
        });

        // call onClose() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onClose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
        setVisible(true);
    }

    private void onClose() {
        // add your code here
        dispose();
    }

    public static void main(String[] args) {
    }

}
