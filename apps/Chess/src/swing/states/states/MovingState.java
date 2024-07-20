package swing.states.states;

import backend.border.Controller;
import backend.model.figure.value.Coord;
import swing.model.figure2d.Figure2D;
import swing.model.figure2d.Figure2DService;
import swing.states.general.StateManagerImpl;

import java.util.List;

import static swing.states.general.StateType.MOVING;
import static swing.states.general.StateType.WAITING;

public final class MovingState extends AbstractState {

    private Figure2DService service;

    public MovingState(StateManagerImpl stateManager, Controller interpreter,Figure2DService service) {
        super(MOVING, stateManager, interpreter);
        this.service = service;
    }

    @Override
    public void initiateState() {
        service.moveFigures(this);

    }

    @Override
    public void onClick(Coord coord) {
    }


    public void movingStopped() {
        service.checkIfBeat();
        stateManager.changeState(WAITING);
    }
}
