package backend.business.modelLogic.behavior;

import backend.business.analize.event.MoveEvent;
import backend.model.figure.model.Figure;
import backend.model.figure.value.Coord;
import java.util.List;
import static backend.business.analize.event.MovingStyle.ATTACK_MOVE;

public class KingBehavior extends BehaviorDecorator {
    public KingBehavior(Behavior behavior) {
        super(behavior);
    }

    @Override
    public List<MoveEvent> generateMoves(Figure figure) {
        List<MoveEvent> moves = super.generateMoves(figure);

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Coord destination = new Coord(figure.getRow() + i, figure.getColumn() + j);
                addIfValid(figure, destination, ATTACK_MOVE, moves);
            }
        }
        return moves;
    }
}
