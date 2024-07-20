package backend.business.modelLogic.behavior;

import backend.business.analize.event.MoveEvent;
import backend.model.figure.model.Figure;
import backend.model.figure.value.Coord;
import java.util.List;

import static backend.business.analize.event.MovingStyle.ATTACK_MOVE;

public class RookBehavior extends BehaviorDecorator {


    public RookBehavior(Behavior behavior) {
        super(behavior);
    }

    @Override
    public List<MoveEvent> generateMoves(Figure figure) {
        List<MoveEvent> moves = super.generateMoves(figure);

        for (int row = 1,  collumn = 1; row <= 8; row++, collumn++) {
            Coord rowDestination = new Coord(row, figure.getColumn());
            Coord columnDestination = new Coord(figure.getRow(), collumn);
            addIfValid(figure, rowDestination, ATTACK_MOVE, moves);
            addIfValid(figure, columnDestination, ATTACK_MOVE, moves);
        }
        return moves;
    }
}
