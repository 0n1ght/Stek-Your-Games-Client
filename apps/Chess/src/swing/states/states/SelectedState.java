package swing.states.states;

import backend.business.analize.event.MoveEvent;
import backend.border.Controller;
import backend.business.analize.analizator.PossibleMoves;
import backend.model.figure.value.Coord;
import swing.states.general.StateManagerImpl;
import backend.model.figure.model.Figure;

import static swing.states.general.StateType.*;

public final class SelectedState extends AbstractState {


    public SelectedState(StateManagerImpl stateManager,
                         Controller controller) {

        super(SELECTED, stateManager, controller);
    }

    @Override
    public void initiateState() {
        PossibleMoves possibleMoves = controller.generatePossibleMoves(getSelected());
        stateManager.setPossibleMoves(possibleMoves);
    }

    @Override
    public void onClick(Coord coord) {
       Figure clicked = controller.getFigure(coord);
        Figure selected = getSelected();

        if (clicked.equals(selected)) {
            stateManager.changeState(WAITING);
            return;
        }

        MoveEvent move = getPossibleMoves().getByDestination(coord);
        if (!move.isValidMove()) return;

        controller.move(move);
        stateManager.changeState(MOVING);
    }
}
