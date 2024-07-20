package swing.input;

import swing.display.Display;
import swing.Pixel;
import swing.Tickable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//zbiera informacje z myszy
public final class MouseManager implements MouseListener, Tickable {

    private Display display;
    private InputInterpreter interpreter;

    private boolean leftButton;

    public MouseManager(Display display, InputInterpreter interpreter) {
        this.display = display;
        this.interpreter = interpreter;
        display.addListener(this);
    }


    @Override
    public void tick() {
        interpreter.updateMousePosition(getMousePixel());
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftButton = true;
            interpreter.click();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftButton = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private Pixel getMousePixel() {
        PointerInfo info = MouseInfo.getPointerInfo();
        Point location = info.getLocation();
        SwingUtilities.convertPointFromScreen(location, display.getCanvas());
        Rectangle rectangle = new Rectangle();

        return new Pixel(location.x, location.y);

    }


}
