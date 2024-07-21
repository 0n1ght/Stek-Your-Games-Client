package pl.monopoly.view;

import pl.monopoly.model.SettingsState;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MenuView {
    private final ViewFactory viewFactory;
    private final JFrame startMenu = new JFrame("Monopoly");

    public MenuView(ViewFactory viewFactory) {
        this.viewFactory = viewFactory;
    }

    public void displayMenu() {
        // images
        addResizedImage("/img/logoMonopoly.png", 400, 120, 50, 0, startMenu);
        addResizedImage("/img/redCubesImage1.png", 140, 140, 10, 130, startMenu);
        addResizedImage("/img/redCubesImage1.png", 140, 140, 10, 250, startMenu);
        addResizedImage("/img/redCubesImage2.png", 140, 140, 335, 130, startMenu);
        addResizedImage("/img/redCubesImage2.png", 140, 140, 335, 250, startMenu);

        // buttons
        MyButton myButton1 = new MyButton("PLAY", 168, 140, 150, 75);
        myButton1.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        myButton1.addActionListener(e -> {
            startMenu.setVisible(false);
            try {
                viewFactory.createGame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            MusicPlayer.stop();
            if (SettingsState.getInstance().isSoundPlaying()) {
                MusicPlayer.play();
            }
        });
        startMenu.add(myButton1);

        MyButton myButton2 = new MyButton("SETTINGS", 168, 230, 150, 75);
        myButton2.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        myButton2.addActionListener(e -> {
            startMenu.setVisible(false);
            viewFactory.createSettings();
        });
        startMenu.add(myButton2);

        MyButton myButton3 = new MyButton("QUIT", 168, 320, 150, 75);
        myButton3.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        myButton3.addActionListener(e -> startMenu.dispose());
        startMenu.add(myButton3);

        // menu
        startMenu.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/icon.png"))).getImage());
        startMenu.setDefaultCloseOperation(EXIT_ON_CLOSE);
        startMenu.setResizable(false);
        startMenu.setSize(500, 500);
        startMenu.setLocationRelativeTo(null);
        startMenu.setLayout(null);
        addResizedImage("/img/menuBackgroundImage.png", 500, 500, 0, 0, startMenu);
        startMenu.setVisible(true);
    }

    public void addResizedImage(String resourcePath, int width, int height, int x, int y, JFrame frame) {
        JLabel label = new JLabel();
        Image image = new ImageIcon(Objects.requireNonNull(getClass().getResource(resourcePath))).getImage();
        Image scaledImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaledImage));
        label.setBounds(x, y, width, height);
        frame.add(label);
    }
}
