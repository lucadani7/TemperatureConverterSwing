package com.lucadani;

import javax.swing.*;
import java.awt.*;

public class TemperatureConverterUI extends JFrame {
    private final JTextField tempInput;
    private final JComboBox<String> conversionSelector;
    private final JLabel resultLabel;

    public TemperatureConverterUI() {
        setTitle("Temperature Converter");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Universal Temperature Converter", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        tempInput = new JTextField();
        String[] options = {
                "Celsius to Fahrenheit",
                "Fahrenheit to Celsius",
                "Celsius to Kelvin",
                "Kelvin to Celsius",
                "Fahrenheit to Kelvin",
                "Kelvin to Fahrenheit"
        };
        conversionSelector = new JComboBox<>(options);
        inputPanel.add(new JLabel("Enter Temperature:"));
        inputPanel.add(tempInput);
        inputPanel.add(new JLabel("Conversion Type:"));
        inputPanel.add(conversionSelector);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        add(inputPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel("Result: ", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        convertButton.addActionListener(e -> convertTemperature());
        bottomPanel.add(convertButton);
        bottomPanel.add(resultLabel);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void convertTemperature() {
        try {
            double input = Double.parseDouble(tempInput.getText());
            String type = (String) conversionSelector.getSelectedItem();
            assert type != null;
            double result = switch (type) {
                case "Celsius to Fahrenheit"    -> TemperatureUtils.celsiusToFahrenheit(input);
                case "Fahrenheit to Celsius"    -> TemperatureUtils.fahrenheitToCelsius(input);
                case "Celsius to Kelvin"        -> TemperatureUtils.celsiusToKelvin(input);
                case "Kelvin to Celsius"        -> TemperatureUtils.kelvinToCelsius(input);
                case "Fahrenheit to Kelvin"     -> TemperatureUtils.fahrenheitToKelvin(input);
                case "Kelvin to Fahrenheit"     -> TemperatureUtils.kelvinToFahrenheit(input);
                default -> throw new IllegalArgumentException("Unknown conversion type: " + type);
            };

            String unit = switch (type) {
                case "Celsius to Fahrenheit", "Kelvin to Fahrenheit" -> "°F";
                case "Fahrenheit to Celsius", "Kelvin to Celsius"    -> "°C";
                case "Celsius to Kelvin", "Fahrenheit to Kelvin"     -> "K";
                default -> "";
            };
            resultLabel.setText(String.format("Result: %.2f %s", result, unit));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
