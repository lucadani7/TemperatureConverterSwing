package com.lucadani;

import com.lucadani.gui.TemperatureConverterUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TemperatureConverterUI().setVisible(true));
    }
}