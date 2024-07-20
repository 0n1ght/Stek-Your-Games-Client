package pl.monopoly.view;

import pl.monopoly.model.SettingsState;

import javax.swing.*;
import java.awt.*;

public class SettingsInGameView {
    private final JFrame settingsFrame = new JFrame("Monopoly");
    private final JCheckBox musicCheckBox = new JCheckBox();

    public void displaySettings() {

        settingsFrame.setResizable(false);
        settingsFrame.setIconImage(new ImageIcon("src\\main\\resources\\img\\icon.png").getImage());
        settingsFrame.setSize(250, 300);
        settingsFrame.setLocationRelativeTo(null);
        settingsFrame.setLayout(null);


        JLabel backgroundComboBoxText = new JLabel("Background color:");
        backgroundComboBoxText.setBounds(15, 15, 110, 20);

        JComboBox<String> backgroundComboBox = new JComboBox<>(new String[] {"green", "blue", "red", "yellow"});
        backgroundComboBox.setFocusable(false);
        backgroundComboBox.setBackground(Color.RED);
        backgroundComboBox.setBounds(130, 15, 65, 30);
        backgroundComboBox.addActionListener(e -> Gameplay.colorIndex = backgroundComboBox.getSelectedIndex());


        JLabel label3 = new JLabel("Background music:");
        label3.setBounds(15, 70, 110, 20);

        musicCheckBox.setFocusable(false);
        musicCheckBox.setBounds(130, 70, 20, 20);
        musicCheckBox.addActionListener(e -> SettingsState.getInstance().setSoundPlaying(musicCheckBox.isSelected()));
        musicCheckBox.setSelected(SettingsState.getInstance().isSoundPlaying());


        settingsFrame.getContentPane().setBackground(Color.WHITE);
        settingsFrame.add(backgroundComboBoxText);
        settingsFrame.add(backgroundComboBox);
        settingsFrame.add(label3);
        settingsFrame.add(musicCheckBox);

        MyButton myButton = new MyButton("BACK", 5, 205, 100, 50);
        myButton.addActionListener(e -> {
            settingsFrame.setVisible(false);
            if (musicCheckBox.isSelected()) {
                MusicPlayer.stop();
                MusicPlayer.play();
            } else {
                MusicPlayer.stop();
            }
        });
        settingsFrame.add(myButton);

        settingsFrame.setVisible(true);
    }
}
