package backend.business.modelLogic.behavior;

import backend.business.analize.event.MoveEvent;
import backend.model.figure.model.Figure;
import java.util.ArrayList;
import java.util.List;

public class BehaviorImpl extends  AbstractBehavior {
    @Override
    public List<MoveEvent> generateMoves(Figure figure) {
        return new ArrayList<>();
    }
}
