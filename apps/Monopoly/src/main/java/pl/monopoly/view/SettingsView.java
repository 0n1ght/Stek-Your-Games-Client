package pl.monopoly.view;

import pl.monopoly.model.SettingsState;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class SettingsView {
    private final JFrame settingsFrame = new JFrame("Monopoly");
    private final JComboBox<String> playersComboBox = new JComboBox<>(new String[] {"4", "3", "2"}); //todo dynamiczna budowa tablicy na podstawie danych z settings(z informacja max i min ilosc graczy)
    private final JCheckBox musicCheckBox = new JCheckBox();
    private final ViewFactory viewFactory;
    private final SettingsState settingsState = SettingsState.getInstance();

    public SettingsView(ViewFactory viewFactory) {
        this.viewFactory = viewFactory;
    }

    public void displaySettings() {

        settingsFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        settingsFrame.setResizable(false);
        settingsFrame.setIconImage(new ImageIcon("src\\main\\resources\\img\\icon.png").getImage());
        settingsFrame.setSize(250, 300);
        settingsFrame.setLocationRelativeTo(null);
        settingsFrame.setLayout(null);


        JLabel playersComboBoxText = new JLabel("Players number:");
        playersComboBoxText.setBounds(15, 15, 110, 20);

        playersComboBox.setFocusable(false);
        playersComboBox.setBackground(Color.RED);
        playersComboBox.setBounds(130, 10, 65, 30);
        playersComboBox.addActionListener(e -> {
            settingsState.setPlayersNumber(
                    Integer.parseInt((String) Objects.requireNonNull(playersComboBox.getSelectedItem())));
            System.out.println("addScoreBoard();");
        });


        JLabel backgroundComboBoxText = new JLabel("Background color:");
        backgroundComboBoxText.setBounds(15, 70, 110, 20);

        JComboBox<String> backgroundComboBox = new JComboBox<>(new String[] {"green", "blue", "red", "yellow"});
        backgroundComboBox.setFocusable(false);
        backgroundComboBox.setBackground(Color.RED);
        backgroundComboBox.setBounds(130, 65, 65, 30);
        backgroundComboBox.addActionListener(e -> Gameplay.colorIndex = backgroundComboBox.getSelectedIndex());


        JLabel label3 = new JLabel("Background music:");
        label3.setBounds(15, 125, 110, 20);

        musicCheckBox.setFocusable(false);
        musicCheckBox.setBounds(140, 126, 20, 20);
        musicCheckBox.addActionListener(e -> SettingsState.getInstance().setSoundPlaying(musicCheckBox.isSelected()));
        musicCheckBox.setSelected(SettingsState.getInstance().isSoundPlaying());


        settingsFrame.getContentPane().setBackground(Color.WHITE);
        settingsFrame.add(playersComboBoxText);
        settingsFrame.add(playersComboBox);
        settingsFrame.add(backgroundComboBoxText);
        settingsFrame.add(backgroundComboBox);
        settingsFrame.add(label3);
        settingsFrame.add(musicCheckBox);

        MyButton myButton = new MyButton("BACK", 5, 205, 100, 50);
        myButton.addActionListener(e -> {settingsFrame.setVisible(false);
            viewFactory.createMenu();});
        settingsFrame.add(myButton);

        settingsFrame.setVisible(true);

    }
}
