package com.nappla.components;

import javax.swing.*;
import java.awt.*;

public class TabPanel extends JTabbedPane {
    public TabPanel() {
        setFont(new Font("Ubuntu", Font.BOLD, 16));
        setForeground(new Color(0x1E1E1E));
        setBackground(new Color(0xF1F1F1));
        setBorder(null);
    }

    @Override
    protected void fireStateChanged() {
        super.fireStateChanged();
        setBackgroundAt(getSelectedIndex(),new Color(0xF1F1F1));
    }
}
