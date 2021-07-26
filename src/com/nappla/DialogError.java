package com.nappla;

import javax.swing.*;
import java.awt.*;

public class DialogError extends JDialog {
    public DialogError (Component jComponentMessage, Component message) {
        BorderLayout borderLayout = new BorderLayout();
        this.setTitle("Error");
        this.setLayout(borderLayout);
        this.setSize(300,200);
        this.setLocationRelativeTo(jComponentMessage);
        this.add(message, BorderLayout.CENTER);
        this.setResizable(false);
        this.setVisible(true);
    }
}
