package backend.border;

import backend.business.analize.event.MoveEvent;
import backend.model.figure.value.Coord;
import backend.model.figure.value.PlayerColor;
import backend.model.figure.model.Figure;
import backend.business.analize.analizator.PossibleMoves;

import java.util.Collection;
import java.util.Set;

public interface Controller {
    Collection<Figure> getAll();

    PossibleMoves generatePossibleMoves(Figure figure);

    Figure getFigure(Coord coord);

    PlayerColor getPlayer();

    PossibleMoves getPossibleMoves(Figure figure);

    PlayerColor isCheck();

    Coord getCheckCoord();

    boolean isCheckMate();

    boolean isPat();

    void move(MoveEvent move);

    Set<Figure> getAllOfPlayer(PlayerColor player);
}
