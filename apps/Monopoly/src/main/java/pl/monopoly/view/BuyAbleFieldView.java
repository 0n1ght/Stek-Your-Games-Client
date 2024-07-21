package pl.monopoly.view;

import pl.monopoly.model.BuyAbleField;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public record BuyAbleFieldView(BuyAbleField field) {

    public void renderX(Graphics g) {
        if (field.getOwner() == null) {
            return;
        }

        ImageIcon image = switch (field.getOwner().getId()) {
            case 0 -> new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/XImages/redXImage.png")));
            case 1 -> new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/XImages/blueXImage.png")));
            case 2 -> new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/XImages/greenXImage.png")));
            case 3 -> new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/XImages/PurpleXImage.png")));
            default -> throw new IllegalStateException("Unexpected value: " + field.getOwner().getId());
        };

        int distance1 = 0, distance2 = 0, normalField = 73, bigFieldAddition = 15;

        if (field.getNumber() <= 10) {
            distance1 = -normalField * field.getNumber();
        } else if (field.getNumber() <= 20) {
            distance1 = -normalField * 10 + 41;
            distance2 = -normalField * (field.getNumber() - 10) + 40;
        } else if (field.getNumber() <= 30) {
            distance1 = -normalField * 10 + normalField * (field.getNumber() - 20);
            distance2 = -normalField * 10 + 82;
        } else {
            distance1 = -43;
            distance2 = -normalField * 10 + normalField * (field.getNumber() - 30) + 40;
        }

        if (field.getNumber() > 0) distance1 -= bigFieldAddition;
        if (field.getNumber() >= 10) distance1 -= bigFieldAddition;
        if (field.getNumber() > 10) distance2 -= bigFieldAddition;
        if (field.getNumber() >= 20) distance2 -= bigFieldAddition;
        if (field.getNumber() > 20) distance1 += bigFieldAddition;
        if (field.getNumber() >= 30) distance1 += bigFieldAddition;
        if (field.getNumber() > 30) distance2 += bigFieldAddition;

        g.drawImage(image.getImage(), 807 + distance1, 770 + distance2, 28, 18, null);
    }

    public void renderHouse(Graphics g) {
        if (field.getOwner() == null || field.getHouses() < 1) {
            return;
        }

        ImageIcon crossImage = new ImageIcon(getClass().getResource("/img/houseImage.png"));

        int distance1 = 0, distance2 = 0, normalField = 73, bigFieldAddition = 15;

        if (field.getNumber() <= 10) {
            distance1 = -normalField * field.getNumber();
        } else if (field.getNumber() <= 20) {
            distance1 = -normalField * 10;
            distance2 = -normalField * (field.getNumber() - 10);
        } else if (field.getNumber() <= 30) {
            distance1 = -normalField * 10 + normalField * (field.getNumber() - 20);
            distance2 = -normalField * 10;
        } else {
            distance2 = -normalField * 10 + normalField * (field.getNumber() - 30);
        }

        if (field.getNumber() > 0) distance1 -= bigFieldAddition;
        if (field.getNumber() >= 10) distance1 -= bigFieldAddition;
        if (field.getNumber() > 10) distance2 -= bigFieldAddition;
        if (field.getNumber() >= 20) distance2 -= bigFieldAddition;
        if (field.getNumber() > 20) distance1 += bigFieldAddition;
        if (field.getNumber() >= 30) distance1 += bigFieldAddition;
        if (field.getNumber() > 30) distance2 += bigFieldAddition;

        if (field.getHouses() >= 1) {
            g.drawImage(crossImage.getImage(), 785 + distance1, 780 + distance2, 35, 35, null);
        }
        if (field.getHouses() >= 2) {
            g.drawImage(crossImage.getImage(), 820 + distance1, 780 + distance2, 35, 35, null);
        }
        if (field.getHouses() >= 3) {
            g.drawImage(crossImage.getImage(), 785 + distance1, 820 + distance2, 35, 35, null);
        }
        if (field.getHouses() >= 4) {
            g.drawImage(crossImage.getImage(), 820 + distance1, 820 + distance2, 35, 35, null);
        }
    }
}
