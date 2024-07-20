package backend.business.analize.analizator;

import backend.business.analize.event.MoveEvent;
import backend.model.figure.model.Figure;
import backend.model.figure.value.Coord;
import backend.model.figure.value.PlayerColor;

import java.util.List;

public interface Analizator {
    List<Figure> getAttackers(PlayerColor player, Coord destination);

    boolean isPat();

    boolean isCheckPlayer(PlayerColor player);

    boolean isCheckMate();

    List<MoveEvent> getAllDefendingMoves(PlayerColor player);

    boolean isAttacked(PlayerColor player, Coord destination);
}
