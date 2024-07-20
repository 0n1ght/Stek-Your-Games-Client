package backend.business.modelLogic.behavior;

import backend.business.analize.event.MoveEvent;
import backend.model.figure.model.Figure;
import backend.model.figure.value.BoardSide;
import backend.model.figure.value.Coord;
import java.util.List;

import static backend.business.analize.event.MovingStyle.MOVE;

public class PawnBehavior extends BehaviorDecorator {
    public PawnBehavior(Behavior behavior) {
        super(behavior);
    }

    @Override
    public List<MoveEvent> generateMoves(Figure figure) {
        List<MoveEvent> moves = super.generateMoves(figure);
        addStraightMoves(figure,moves);
        return moves;
    }

    private void addStraightMoves(Figure figure,List<MoveEvent> moves) {
        int steppes = 1;

        if (figure.getSide().getPawnsRow() == figure.getRow()) {
            steppes++;
        }
        int modifier = 1;

        if (figure.getSide() == BoardSide.BOTTOM) {
            modifier = -1;
        }

        for (int step = 1; step <=steppes ; step++) {
            int row = figure.getRow() + (step * modifier);
            Coord destination = new Coord(row, figure.getColumn());
            addIfValid(figure, destination, MOVE, moves);
        }
    }

}
