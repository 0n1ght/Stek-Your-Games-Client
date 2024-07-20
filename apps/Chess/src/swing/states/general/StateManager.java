package swing.states.general;

import backend.model.figure.value.Coord;
import backend.business.analize.analizator.PossibleMoves;
import backend.model.figure.model.Figure;
import backend.model.figure.value.PlayerColor;

public interface StateManager {
    Figure getSelected();

    void changeState(StateType waiting);

    PlayerColor getPlayer();

    void setSelected(Figure figure);

    void setPossibleMoves(PossibleMoves possibleMoves);

    PossibleMoves getPossibleMoves();

    void register(StateObserver observer);

    void onClick(Coord coord);
}

