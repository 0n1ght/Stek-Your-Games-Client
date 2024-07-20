package pl.monopoly.view;

import pl.monopoly.model.Player;

import java.awt.*;
import java.util.List;

public record ScoreView(List<Player> playerList) {

    public void render(Graphics g) {
        int x = Display.getWidth() - 730 - Display.getRelativeX() * 2;
        int y = Display.getHeight() - 705 - Display.getRelativeY() * 2;

        g.setFont(new Font("Comic Sans MS", Font.BOLD, 24));

        g.setColor(new Color(204, 0, 0));
        if (playerList.get(0).getMoney() > 0) {
            g.drawString("red: " + playerList.get(0).getMoney() + "$", x, y + 23);
        } else {
            g.drawString("red: " + "----", x, y + 23);
        }

        g.setColor(new Color(0, 0, 225));
        if (playerList.size() >= 2 && playerList.get(1).getMoney() > 0) {
            g.drawString("blue: " + playerList.get(1).getMoney() + "$", x, y + 55);
        } else {
            g.drawString("blue: " + "----", x, y + 55);
        }

        g.setColor(new Color(0, 153, 0));
        if (playerList.size() >= 3 && playerList.get(2).getMoney() > 0) {
            g.drawString("green: " + playerList.get(2).getMoney() + "$", x, y + 87);
        } else {
            g.drawString("green: " + "----", x, y + 87);
        }

        g.setColor(new Color(102, 0, 153));
        if (playerList.size() >= 4 && playerList.get(3).getMoney() > 0) {
            g.drawString("purple: " + playerList.get(3).getMoney() + "$", x, y + 119);
        } else {
            g.drawString("purple: " + "----", x, y + 119);
        }
    }
}
