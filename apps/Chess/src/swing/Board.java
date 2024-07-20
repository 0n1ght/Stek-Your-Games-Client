package swing;

import swing.display.Display;

public final class Board {

    public static int getFieldSize() {
        return Display.getWidth() / 8;
    }
}
