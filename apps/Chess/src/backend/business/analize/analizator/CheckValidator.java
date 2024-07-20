package backend.business.analize.analizator;

import backend.business.analize.event.MoveEvent;
import backend.model.game.ChessGame;
import java.util.List;

//usuwa ruchy powodujące szacha
public class CheckValidator {

    private CheckSimulator simulator;

    private ChessGame game;

    public CheckValidator(CheckSimulator simulator) {
        this.simulator = simulator;
    }

    void removeMovesCausingCheck(List<MoveEvent> moves, ChessGame game) {
        setData(game);
        moves.removeIf(this::checkIfCauseCheck);
    }

    private void setData(ChessGame game) {
        this.game = game;
    }

    private boolean checkIfCauseCheck(MoveEvent move) {
        if (move.isCastling()) {
           return castlingCheck(move);
        }
        return normalCheck(move);
    }

    private boolean normalCheck(MoveEvent move) {
        return simulator.wouldCauseYouCheck(move, game);
    }

    private boolean castlingCheck(MoveEvent move) {
        if (normalCheck(move)) return true;
        return checkCastlingPassingMove(move);
    }

    private boolean checkCastlingPassingMove(MoveEvent move) {
      return   simulator.castlingPassingCauseCheck(move, game);
    }

}
