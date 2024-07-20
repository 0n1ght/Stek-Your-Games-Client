package backend.business.modelLogic.behavior;

import backend.business.analize.event.MoveEvent;
import backend.model.figure.model.Figure;

import java.util.List;

public interface Behavior {
    List<MoveEvent> generateMoves(Figure figure);

}
