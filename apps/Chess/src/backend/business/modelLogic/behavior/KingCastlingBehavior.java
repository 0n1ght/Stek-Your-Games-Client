package backend.business.modelLogic.behavior;

import backend.business.analize.event.MoveEvent;
import backend.model.figure.model.Figure;
import backend.model.figure.value.Coord;
import java.util.List;

import static backend.business.analize.event.MovingStyle.CASTLING;

public class KingCastlingBehavior extends BehaviorDecorator {
    public KingCastlingBehavior(Behavior behavior) {
        super(behavior);
    }

    @Override
    public List<MoveEvent> generateMoves(Figure figure) {
        List<MoveEvent> moves = super.generateMoves(figure);
        if (figure.hasMoved()) {
            return moves;
        }
        Coord leftDestination = new Coord(figure.getRow(), figure.getColumn() - 2);
        Coord rightDestination = new Coord(figure.getRow(), figure.getColumn() + 2);
        addIfValid(figure,leftDestination,CASTLING,moves);
        addIfValid(figure,rightDestination,CASTLING,moves);
        return moves;
    }
}
