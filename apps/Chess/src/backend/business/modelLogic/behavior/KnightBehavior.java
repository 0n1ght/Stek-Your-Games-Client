package backend.business.modelLogic.behavior;

import backend.business.analize.event.MoveEvent;
import backend.model.figure.model.Figure;
import backend.model.figure.value.Coord;
import java.util.Arrays;
import java.util.List;

import static backend.business.analize.event.MovingStyle.TELEPORT;

public class KnightBehavior extends BehaviorDecorator{
    public KnightBehavior(Behavior behavior) {
        super(behavior);
    }

    @Override
    public List<MoveEvent> generateMoves(Figure figure) {
        List<MoveEvent> moves = super.generateMoves(figure);

        List<Integer> numbers = Arrays.asList(-2, -1, 1, 2);
        for (Integer row : numbers) {
            for (Integer column : numbers) {
                if (Math.abs(row) == Math.abs(column)) {
                    continue;
                }
                Coord destination = new Coord(figure.getRow() + row, figure.getColumn() + column);
                addIfValid(figure,destination, TELEPORT,moves);
            }
        }
        return moves;
    }
}
