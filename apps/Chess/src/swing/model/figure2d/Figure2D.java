package swing.model.figure2d;

import swing.Renderable;
import swing.Tickable;

public interface Figure2D extends Renderable, Tickable {
    void move();

    boolean isMoving();

    void checkIfBeat();
}
