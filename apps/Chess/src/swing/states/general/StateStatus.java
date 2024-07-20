package swing.states.general;

import backend.model.figure.value.Coord;
import backend.business.analize.analizator.PossibleMoves;
import backend.model.figure.model.Figure;
import backend.model.figure.value.PlayerColor;

public interface StateStatus {
    PlayerColor getPlayer();
    Figure getSelected();
    StateType getType();
    PossibleMoves getPossibleMoves();
    Coord getCoordIfCheck();
    boolean isCheckMate();
    boolean isPat();
}
