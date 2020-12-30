package run;

import model.ChargingStation;
import model.Robot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    private JPanel mainPanel;
    private JButton addRobotButton;
    private JButton confirmButton;
    private JPanel viewPanel;
    private JTextField errorField;
    private JComboBox comboBox1;
    private JTextArea stationArea;
    private JTextArea robotArea;
    private JButton refreshButton;
    private JPanel robotsPanel;
    private Integer tab[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int robotCounter = 0;
    int maxBatterySize = 0;

    List<Robot> robotList = new ArrayList<>();
    ChargingStation chargingStation = new ChargingStation();

    String description = "";
    String stationDescription = "";

    public App() {
        JFrame frame = new JFrame("Roboty");

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(900, 250);
        frame.setLocationRelativeTo(null);


        stationArea.setEditable(false);
        robotArea.setEditable(false);

        refreshButton.setEnabled(false);

        errorField.setEditable(false);
        errorField.setText("ustaw wielkosc baterii robota i dodaj go");
        for (Integer i : tab
        ) {
            comboBox1.addItem(i);
        }

        addRobotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                robotCounter++;

                int batterySize = Integer.parseInt(comboBox1.getSelectedItem().toString());
                errorField.setText("dodano robota nr " + robotCounter + " o rozmiarze baterii rownej " + batterySize);
                if (batterySize > maxBatterySize) maxBatterySize = batterySize;

                Robot tempRobot = new Robot(String.valueOf(robotCounter), batterySize, chargingStation);

                robotList.add(tempRobot);
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRobotButton.setEnabled(false);
                confirmButton.setEnabled(false);
                chargingStation.setStationSize(maxBatterySize);
                for (Robot r : robotList) {
                    Thread tempThread = new Thread(r);
                    tempThread.start();
                    description = description + r.toString() + "\n";
                    robotArea.setText(description);
                }
                stationArea.setText(chargingStation.toString());
                refreshButton.setEnabled(true);
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                description = "";
                for (Robot r : robotList) {
                    description = description + r.toString() + "\n";
                    robotArea.setText(description);
                }
                stationArea.setText(chargingStation.toString());
            }
        });
    }


    public static void main(String[] args) {
        App app = new App();
    }
}
