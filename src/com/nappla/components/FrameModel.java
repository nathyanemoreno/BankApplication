package com.nappla.components;

import javax.swing.*;
import java.awt.*;

public class FrameModel extends JFrame {
    public FrameModel () {
        BorderLayout gridLayout = new BorderLayout(16, 16);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(gridLayout);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(0xF3F3F3));
    }
}
