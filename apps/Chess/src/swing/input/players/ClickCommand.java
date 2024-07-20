package swing.input.players;

import backend.model.figure.value.Coord;
import backend.model.figure.value.PlayerColor;
import swing.states.general.StateManager;

public class ClickCommand {
    private StateManager stateManager;

    public ClickCommand(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    void clickOnCoords(Coord coord, PlayerColor player) {
        if(stateManager.getPlayer()!= player) return;
        stateManager.onClick(coord);
    }
}
