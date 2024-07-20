package backend.business.analize.analizator;

import backend.business.analize.event.MoveEvent;
import backend.business.analize.event.MovingStyle;
import backend.model.figure.model.Figure;
import backend.model.figure.value.BoardSide;
import backend.model.figure.value.Coord;
import backend.model.game.ChessGame;

import java.util.List;

import static backend.business.analize.event.MovingStyle.*;

public class BasicValidator {

    private CollisionDetector detector;
    private MoveEventReader reader;

    private ChessGame game;
    private MoveEvent move;
    private List<MoveEvent> moves;
    private Figure figure;
    private Coord destination;

    public BasicValidator(CollisionDetector detector, MoveEventReader reader) {
        this.detector = detector;
        this.reader = reader;
    }

    void addIfPossible(MoveEvent move, List<MoveEvent> moves, ChessGame game) {
        setData(move, moves, game);
        validateMove();
    }

    private void setData(MoveEvent move, List<MoveEvent> moves, ChessGame game) {
        this.move = move;
        this.moves = moves;
        this.game = game;
        this.figure = game.getFigure(move.getStart());
        destination = move.getDestination();
    }

    private void validateMove() {
        MovingStyle style = move.getStyle();

        if (style == CASTLING) {
            addMoveIfCastlingPossible();
            return;
        }

        if (style == EN_PASSANT) {
            addEnPassantMove();
            return;
        }

        if (detector.isNotInRange(game,figure, destination, style)) return;


        if (!game.hasFigure(destination) && isMovable(style)) {
            addMove(MOVE); // todo teraz nie trzeba tworzyć nowego fullmove wystarczy setem zmienic z attack-move na move
            return;
        }
        addIfAttackable(style);
        addIfDefending(style);
    }

    private void addMoveIfCastlingPossible() {
        Figure rook = reader.getRookForCastling(move,game);

        if (game.isCheck() == game.getPlayer()) {
            return;
        }
        if (rook.isNullObject()) {
            return;
        }
        if (rook.hasMoved()) {
            return;
        }
        if (detector.isNotInRange(game,figure, rook.getCoord(), CASTLING)) {
            return;
        }
        addMove(CASTLING);
    }



    private void addEnPassantMove() {
        if (enPassantPossible()) {
            addMove(EN_PASSANT);
        } else {
            addIfAttackable(EN_PASSANT);
        }
    }
    //todo to change if command pattern added to moves engine

    private boolean enPassantPossible() {
        if (hasEnemy(destination)) {
            return false;
        }
        BoardSide otherSide = figure.getSide().otherSide();
        int figureRow = figure.getRow();
        int enPassantRow = otherSide.getPawnsDoubleMoveRow();

        if (figureRow != enPassantRow) {
            return false;
        }

        Coord enemyInRange = new Coord(enPassantRow, destination.getColumn());
        if (!hasEnemy(enemyInRange)) {
            return false;
        }

        Figure enemy = game.getFigure(enemyInRange);
        if (!enemy.equals(game.getLastPlayed())) {
            return false;
        }

        return enemy.canBeEnPassanted();
    }


    private void addMove(MovingStyle style) {
        move.setStyle(style);
        moves.add(move);
    }

    private void addIfAttackable(MovingStyle style) {
        if (hasEnemy(destination) && isAttackable(style)) {
            addMove(ATTACK);
        }
    }

    private void addIfDefending(MovingStyle style) {
        if (hasFriend(destination) && isAttackable(style)) {
            addMove(DEFENCE);
        }
    }

    //todo SPRAWDZIC CZY DALEJ MAJA SENS

    private boolean isMovable(MovingStyle style) {
        return style.canMove();
    }
    private boolean isAttackable(MovingStyle style) {
        return style.canAttack();
    }

    private boolean hasEnemy(Coord destination) {
        if (!game.hasFigure(destination)) return false;
        return figure.getPlayer() != game.getFigure(destination).getPlayer();
    }

    private boolean hasFriend(Coord destination) {
        if (!game.hasFigure(destination)) return false;
        return figure.getPlayer() == game.getFigure(destination).getPlayer();
    }


}
