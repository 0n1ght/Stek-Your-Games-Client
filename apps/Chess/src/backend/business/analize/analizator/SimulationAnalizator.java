package backend.business.analize.analizator;

import backend.business.analize.event.MoveEvent;
import backend.model.figure.model.Figure;
import backend.model.figure.value.PlayerColor;
import backend.model.game.ChessGame;

import java.util.List;

public interface SimulationAnalizator extends Analizator {
    void setGameCopy(ChessGame copy);

    int countMoves(PlayerColor player);

    int countDefences(PlayerColor player);

    List<Figure> getFiguresInDanger(PlayerColor player);

    List<MoveEvent> getAllPossibleMoves(PlayerColor player);

}
