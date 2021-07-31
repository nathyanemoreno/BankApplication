package com.nappla.components;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {
    public Label (String label, int size) {
        setFont(new Font(getName(), Font.PLAIN, size));
        setHorizontalAlignment(JLabel.CENTER);
        setText(label);
    }

    public Label (String label) {
        setFont(new Font(this.getName(), Font.PLAIN, 18));
        setHorizontalAlignment(JLabel.CENTER);
        setText(label);
    }

    public Label () {
        setFont(new Font(this.getName(), Font.PLAIN, 18));
        setHorizontalAlignment(JLabel.CENTER);
    }
}
