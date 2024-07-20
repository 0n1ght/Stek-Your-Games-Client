package swing.states.states;

import backend.border.Controller;
import backend.business.analize.analizator.PossibleMoves;
import swing.states.general.State;
import swing.states.general.StateManager;
import swing.states.general.StateType;
import backend.model.figure.model.Figure;

public abstract class AbstractState implements State {

    private final StateType type;
    final StateManager stateManager;
    final Controller controller;


    public AbstractState(StateType type, StateManager stateManager, Controller controller) {
        this.type = type;
        this.stateManager = stateManager;
        this.controller = controller;
    }


    @Override
    public StateType getType() {
        return type;
    }

    Figure getSelected() {
        return stateManager.getSelected();
    }

    void setSelected(Figure figure) {
        stateManager.setSelected(figure);
    }


    //todo remove
    @Override
    public PossibleMoves getPossibleMoves() {
        return stateManager.getPossibleMoves();
    }

}







