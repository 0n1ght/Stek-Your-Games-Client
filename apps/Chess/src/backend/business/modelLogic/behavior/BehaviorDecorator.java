package backend.business.modelLogic.behavior;

import backend.business.analize.event.MoveEvent;
import backend.model.figure.model.Figure;
import java.util.List;

public abstract class BehaviorDecorator extends AbstractBehavior {

    private Behavior behavior;

    BehaviorDecorator(Behavior behavior) {
        this.behavior = behavior;
    }

    @Override
    public List<MoveEvent> generateMoves(Figure figure) {
        return behavior.generateMoves(figure);
    }
}
