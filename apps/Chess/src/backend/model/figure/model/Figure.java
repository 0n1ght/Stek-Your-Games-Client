package backend.model.figure.model;

import backend.business.analize.event.MoveEvent;
import backend.model.figure.value.BoardSide;
import backend.model.figure.value.Coord;
import backend.model.figure.value.FigureType;
import backend.model.figure.value.PlayerColor;

import java.util.List;

public interface Figure  {

    boolean isNullObject();

    Coord getCoord();

    PlayerColor getPlayer();

    FigureType getType();

    List<MoveEvent> generateMoves();

    BoardSide getSide();

    int getRow();

    int getColumn();

    boolean isBeat();

    void beat();

    Figure copy() throws CloneNotSupportedException;

    void move(Coord newCoords);

    void unBeat();

    int getStrength();

    boolean isBeaten();

    boolean hasMoved();

    boolean canBeEnPassanted();

    int compare(Figure other);
}
