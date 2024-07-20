package swing.states.states;

import backend.model.factory.FigureFactory;
import backend.border.Controller;
import backend.model.figure.value.Coord;
import swing.states.general.StateManagerImpl;
import backend.model.figure.model.Figure;

import static swing.states.general.StateType.SELECTED;
import static swing.states.general.StateType.WAITING;


public class WaitingState extends AbstractState {


    public WaitingState(StateManagerImpl stateManager, Controller interpreter) {
        super(WAITING, stateManager, interpreter);

    }

    @Override
    public void initiateState() {
        stateManager.setSelected(FigureFactory.getEmpty());
    }

    @Override
    public void onClick(Coord coord) {
        Figure figure = controller.getFigure(coord);
        if (figure.isNullObject() ||
                figure.getPlayer()!= stateManager.getPlayer()) {
            return;
        }
        stateManager.setSelected(figure);
        stateManager.changeState(SELECTED);
    }






}
