package org.dreambot.utilities;

import org.dreambot.api.utilities.Logger;
import org.dreambot.utilities.muling.MuleClient;
import org.dreambot.utilities.muling.MuleServer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUI {

    private static boolean isMule;


    public static void showGUI() {
        JFrame frame = new JFrame();
        frame.setTitle("Automated Gold Farm");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(new Dimension(300, 200));
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel settingPanel = new JPanel();
        settingPanel.setLayout(new GridLayout(0, 2));

        JCheckBox muleCheckBox = new JCheckBox("Is Mule");
        muleCheckBox.addActionListener(e -> isMule = muleCheckBox.isSelected());
        settingPanel.add(muleCheckBox);

        frame.getContentPane().add(settingPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton button = new JButton("Start script");
        button.addActionListener(e -> {
            Settings settings = Global.getInstance().getSettings();
            settings.setRunning(true);
            settings.setMule(isMule);
            frame.dispose();

            if (settings.isRunning()) {
                settings.setShouldInitialize(true);
                if (settings.isMule()) {
                    try {
                        MuleServer.getInstance().start(12345);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        MuleClient.getInstance().startConnection("127.0.0.1", 12345);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        buttonPanel.add(button);

        button = new JButton("Another button");
        button.addActionListener(e -> Logger.log("Another button clicked..."));
        buttonPanel.add(button);

        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }
}
