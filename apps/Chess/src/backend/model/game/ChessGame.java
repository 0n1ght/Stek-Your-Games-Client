package backend.model.game;

import backend.business.analize.event.MoveEvent;
import backend.model.figure.value.Coord;
import backend.model.figure.value.FigureType;
import backend.model.figure.value.PlayerColor;
import backend.model.figure.model.Figure;

import java.util.Collection;
import java.util.Set;

public interface ChessGame {

    ChessGame copy() throws CloneNotSupportedException;

    PlayerColor getPlayer();

    void setPlayer(PlayerColor player);

    void setCheck(PlayerColor player);

    PlayerColor isCheck();

    boolean hasFigure(Coord coord);

    Figure getFigure(Coord coord);

    Figure getFigureByType(FigureType type, PlayerColor player);

    Set<Figure> getAllFiguresOfPlayer(PlayerColor player);

    Collection<Figure> getAll();

    PlayerColor lastPlayer();

    Figure getLastPlayed();

    Collection<Figure> getAllFiguresOfActualPlayer();

    void setPat(boolean pat);

    boolean isPat();

    boolean isCheckMate();

    void setCheckMate(boolean checkMate);

    void move(MoveEvent move);

    void tryPromotingPawn();
}
