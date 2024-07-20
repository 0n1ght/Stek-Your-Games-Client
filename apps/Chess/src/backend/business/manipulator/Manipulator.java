package backend.business.manipulator;

import backend.business.analize.event.MoveEvent;

public interface Manipulator {
    void move(MoveEvent move);
}
