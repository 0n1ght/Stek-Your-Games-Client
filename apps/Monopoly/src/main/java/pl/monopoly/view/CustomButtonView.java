package pl.monopoly.view;

import java.awt.*;

public abstract class CustomButtonView {

    final int width;
    final int height;
    final int positionX;
    final int positionY;

    public CustomButtonView(int width, int height, int positionX, int positionY) {
        this.width = width;
        this.height = height;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public abstract void render(Graphics g);

    public Rectangle getBounds() {
        return new Rectangle(positionX+Display.getRelativeX(), positionY+Display.getRelativeY(), width, height);
    }

    public abstract void click();
}
