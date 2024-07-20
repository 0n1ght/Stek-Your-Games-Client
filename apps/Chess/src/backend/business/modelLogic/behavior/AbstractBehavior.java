package backend.business.modelLogic.behavior;

import backend.business.analize.event.MoveEvent;
import backend.business.analize.event.MovingStyle;
import backend.model.figure.model.Figure;
import backend.model.figure.value.Coord;
import java.util.List;

abstract class AbstractBehavior implements Behavior {


    void addIfValid(Figure figure, Coord destination, MovingStyle style, List<MoveEvent> moves) {
        Coord start = figure.getCoord();
        if (start.equals(destination)) return;
        if (destination.areNotValid()) return;
        MoveEvent move = new MoveEvent(start, destination, style);
        moves.add(move);
    }

}
