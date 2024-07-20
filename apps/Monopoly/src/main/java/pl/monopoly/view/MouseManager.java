package pl.monopoly.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class MouseManager implements MouseListener {

    private List<CustomButtonView> customButtonViewList = new ArrayList<>();

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {

        Rectangle rectangle = new Rectangle(e.getX(), e.getY(), 1, 1);

        for (CustomButtonView customButtonView : customButtonViewList) {

            if (customButtonView.getBounds().intersects(rectangle)) {
                customButtonView.click();
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setCustomButtonViewList(List<CustomButtonView> customButtonViewList) {
        this.customButtonViewList = customButtonViewList;
    }
}
