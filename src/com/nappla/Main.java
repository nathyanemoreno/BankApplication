package com.nappla;

import javax.swing.*;
import java.awt.*;

public class Main {

    public Main() {
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new Auth("Nappla Bank");
            } catch (Exception e) {
                new WarningDialog(null, "Error");
            }
        });
    }
}