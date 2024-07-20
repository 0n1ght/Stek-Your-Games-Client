package backend.business.analize.analizator;

import backend.business.analize.event.MoveEvent;
import backend.model.figure.model.Figure;
import backend.model.game.ChessGame;

import java.util.ArrayList;
import java.util.List;

//generuje listę możliwych ruchów
public abstract class MoveValidator {

    private BasicValidator basic;

    ChessGame game;
    List<MoveEvent> moves;

    public MoveValidator(BasicValidator basic) {
        this.basic = basic;
        moves = new ArrayList<>();
    }

    final public PossibleMoves computePossibleMoves(ChessGame game, Figure figure) {
        setData(game);
        computeSimpleMoves(figure);
        applyCheckStrategy();
        return buildPossibleMoves();
    }

    private void computeSimpleMoves(Figure figure) {
        computeAllMoves(figure);
    }

    private void computeAllMoves(Figure figure) {
        List<MoveEvent> figureBehavior = figure.generateMoves();
        for (MoveEvent move : figureBehavior) {
            addIfPossible(move);
        }
    }

    private void addIfPossible(MoveEvent move) {
        basic.addIfPossible(move,moves,game);
    }

    private PossibleMoves buildPossibleMoves() {
        return new PossibleMoves(moves);
    }

    abstract void applyCheckStrategy();

    private void setData(ChessGame game) {
        this.game = game;
        moves = new ArrayList<>();
    }

}
