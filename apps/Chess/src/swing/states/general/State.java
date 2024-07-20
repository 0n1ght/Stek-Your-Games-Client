package swing.states.general;

import backend.business.analize.analizator.PossibleMoves;
import backend.model.figure.value.Coord;

public interface State {

    void initiateState();

    void onClick(Coord coord);

    StateType getType();

    PossibleMoves getPossibleMoves();
}


