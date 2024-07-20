package backend.business.modelLogic.behavior;

import backend.business.analize.event.MoveEvent;
import backend.model.figure.model.Figure;
import backend.model.figure.value.BoardSide;
import backend.model.figure.value.Coord;
import java.util.List;

import static backend.business.analize.event.MovingStyle.EN_PASSANT;

public class EnPassantBehavior extends BehaviorDecorator {

    public EnPassantBehavior(Behavior behavior) {
        super(behavior);
    }

    @Override
    public List<MoveEvent> generateMoves(Figure figure) {
        List<MoveEvent> moves = super.generateMoves(figure);
        int modifier = determineBoardSideModifier(figure);
        Coord leftDestination = new Coord(figure.getRow() + modifier, figure.getColumn() - 1);
        Coord rightDestination = new Coord(figure.getRow()+ modifier,figure.getColumn()+1);

        addIfValid(figure,leftDestination,EN_PASSANT,moves);
        addIfValid(figure,rightDestination,EN_PASSANT,moves);
        return moves;
    }

    private int determineBoardSideModifier(Figure figure) {
        return figure.getSide() == BoardSide.BOTTOM ? -1 : 1;
    }

}
