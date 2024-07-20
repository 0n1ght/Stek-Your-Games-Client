package backend.business.modelLogic.behavior;

import backend.business.analize.event.MoveEvent;
import backend.model.figure.model.Figure;
import backend.model.figure.value.Coord;
import java.util.List;

import static backend.business.analize.event.MovingStyle.ATTACK_MOVE;

public class BishopBehavior extends BehaviorDecorator {
    public BishopBehavior(Behavior behavior) {
        super(behavior);
    }

    @Override
    public List<MoveEvent> generateMoves(Figure figure) {
        List<MoveEvent> moves = super.generateMoves(figure);

        for (int i = 1; i <= 8; i++) {
            Coord leftDestination = new Coord(figure.getRow() + figure.getColumn() - i, i);
            Coord rightDestination = new Coord(figure.getRow() - figure.getColumn() + i, i);
            addIfValid(figure,leftDestination,ATTACK_MOVE,moves);
            addIfValid(figure,rightDestination,ATTACK_MOVE,moves);
        }
        return moves;
    }

}
