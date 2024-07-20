package pl.monopoly.view;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {

    public MyButton(String name, int x, int y, int width, int height) {

        setText(name);
        setFont(new Font("Serif", Font.ITALIC, 30));
        setForeground(Color.WHITE);
        setBackground(Color.RED);
        setBounds(x, y, width, height);
        setFocusable(false);
        initSound();
        setBorder(BorderFactory.createLineBorder(new Color(140, 0, 0), 3));
        setContentAreaFilled(false);
        setOpaque(true);
        setFocusPainted(false);
    }

    private void initSound() {

        addActionListener(e -> SoundPlayer.playSound(Sound.BUTTON_CLICK));
    }
}
