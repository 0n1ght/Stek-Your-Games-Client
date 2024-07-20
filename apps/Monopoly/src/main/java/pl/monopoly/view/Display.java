package pl.monopoly.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

// class which manages all visible frames
public final class Display {

    public static final int DEFAULT_WIDTH = 880;
    public static final int DEFAULT_HEIGHT = 880;
    private static JFrame frame;
    private static Canvas canvas;

    private Dimension size;

    // create
    public Display() {
        displayGame();
    }

    public void displayGame() {

        size = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        createAndSetupFrame();
        createAndSetupCanvas();
        frame.pack();
    }

    private void createAndSetupFrame() {

        frame = new JFrame("Monopoly");

        frame.setSize(size);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setIconImage(new ImageIcon("src\\main\\resources\\img\\icon.png").getImage());
        frame.setLayout(new BorderLayout());
    }

    private void createAndSetupCanvas() {
        canvas = new Canvas();

        canvas.setPreferredSize(size);
        canvas.setMaximumSize(size);
        canvas.setMinimumSize(size);
        frame.add(canvas, BorderLayout.CENTER);
        canvas.setFocusable(true);
        canvas.requestFocusInWindow();
    }

    public void addListener(MouseListener listener) {
        canvas.addMouseListener(listener);
    }

    public void showGame() {
        frame.setVisible(true);
    }

    // get/set
    public Canvas getCanvas() {
        return canvas;
    }

    public static int getHeight() {
        return canvas.getHeight();
    }

    public static int getWidth() {
        return canvas.getWidth();
    }

    public static int getRelativeX() {

        if (getWidth() > DEFAULT_WIDTH) {
            return (getWidth()-DEFAULT_WIDTH)/2;
        }

        return 0;
    }

    public static int getRelativeY() {

        if (getHeight() > DEFAULT_HEIGHT) {
            return (getHeight()-DEFAULT_HEIGHT)/2;
        }

        return 0;
    }

    public void close() {
        frame.dispose();
    }
}
