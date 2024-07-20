package pl.monopoly.view;

import pl.monopoly.model.Player;

import javax.swing.*;
import java.awt.*;

public final class PlayerView {
    private final Player player;
    private boolean marked;

    public PlayerView(Player player) {
        this.player = player;
    }

    public void render(Graphics g) {

        if (player.getMoney() < 0) {
            return;
        }

        int distance1 = 0, distance2 = 0, normalField = 73, bigFieldAddition = 15;

        if (player.getFieldNumber() <= 10) {

            distance1 = -normalField * player.getFieldNumber();

        } else if (player.getFieldNumber() > 10 && player.getFieldNumber() <= 20) {

            distance1 = -normalField * 10;
            distance2 = -normalField * (player.getFieldNumber() - 10);

        } else if (player.getFieldNumber() > 20 && player.getFieldNumber() <= 30) {

            distance1 = -normalField * 10 + normalField * (player.getFieldNumber() - 20);
            distance2 = -normalField * 10;

        } else if (player.getFieldNumber() > 30) {

            distance2 = -normalField * 10 + normalField * (player.getFieldNumber() - 30);

        }

        if (player.getFieldNumber() > 0) {
            distance1 -= bigFieldAddition;
        }
        if (player.getFieldNumber() >= 10) {
            distance1 -= bigFieldAddition;
        }
        if (player.getFieldNumber() > 10) {
            distance2 -= bigFieldAddition;
        }
        if (player.getFieldNumber() >= 20) {
            distance2 -= bigFieldAddition;
        }
        if (player.getFieldNumber() > 20) {
            distance1 += bigFieldAddition;
        }
        if (player.getFieldNumber() >= 30) {
            distance1 += bigFieldAddition;
        }
        if (player.getFieldNumber() > 30) {
            distance2 += bigFieldAddition;
        }

        ImageIcon playerIcon = switch (player.getColor()) {
            case RED -> new ImageIcon("src\\main\\resources\\img\\playersImages\\red.png");
            case BLUE -> new ImageIcon("src\\main\\resources\\img\\playersImages\\blue.png");
            case GREEN -> new ImageIcon("src\\main\\resources\\img\\playersImages\\green.png");
            case PURPLE -> new ImageIcon("src\\main\\resources\\img\\playersImages\\purple.png");
        };
        ImageIcon arrowImage = new ImageIcon("src\\main\\resources\\img\\arrowImage.png");
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        g.setColor(Color.GREEN);

        int[] xModifier = {-10, +10, -10, +10};
        int[] yModifier = {-10, -10, +10, +10};

        if (player.isSingle()) {

            g.drawImage(playerIcon.getImage(), 790 + distance1, 790 + distance2, 50, 50, null);
            if (marked)
                g.drawImage(arrowImage.getImage(), 810 + distance1, 765 + distance2, 20, 25, null);
        } else {
            g.drawImage(playerIcon.getImage(), 790 + xModifier[player.getId()] + distance1, 790 + yModifier[player.getId()] + distance2, 50, 50, null);
            if (marked)
                g.drawImage(arrowImage.getImage(), 810 + xModifier[player.getId()] + distance1, 765 + yModifier[player.getId()] + distance2, 20, 25, null);
        }
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public Player getPlayer() {
        return player;
    }
}
