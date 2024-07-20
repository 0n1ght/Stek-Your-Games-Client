package backend.business.analize.analizator;

import backend.business.analize.event.MoveEvent;
import backend.business.manipulator.SimulationManipulator;
import backend.model.figure.model.Figure;
import backend.model.figure.value.Coord;
import backend.model.game.ChessGame;

import static backend.business.analize.event.MovingStyle.MOVE;


//sprawdza czy ruch spowoduje szacha
public class CheckSimulator {


    private SimulationManipulator manipulator;
    private SimulationAnalizator analizator;
    private MoveEventReader reader;

    private ChessGame original;
    private ChessGame simulation;

    public CheckSimulator(SimulationManipulator manipulator, SimulationAnalizator analizator, MoveEventReader reader) {
        this.manipulator = manipulator;
        this.analizator = analizator;
        this.reader = reader;
    }

    public boolean wouldCauseYouCheck(MoveEvent move, ChessGame original) {
        setData(original);
        prepareSimulation();
        manipulator.move(move);
        return standardMoveCheck();
    }

    public boolean castlingPassingCauseCheck(MoveEvent move,ChessGame game) {
        Coord start = move.getStart();
        Figure rook = reader.getRookForCastling(move, game);
        int kingMoveColumn = whichColumnWouldBeKing(rook);
        MoveEvent intermediateMove = new MoveEvent(start, new Coord(move.getDestination().getRow(),
                kingMoveColumn), MOVE);

        return wouldCauseYouCheck(intermediateMove, game);
    }

    private void setData(ChessGame original) {
        this.original = original;
    }

    private void prepareSimulation() {
        try {
            simulation = original.copy();
            manipulator.setGameCopy(simulation);
            analizator.setGameCopy(simulation);

        } catch (CloneNotSupportedException e) {
            System.err.println("Cannot clone object");
        }
    }

    private boolean standardMoveCheck() {
        return simulation.isCheck() == original.getPlayer();
    }

    private int whichColumnWouldBeKing(Figure rook) {
        if (rook.getColumn() == 1) {
           return  4;
        }
        return 6;
    }

}
