package swing.input.players;

import backend.model.figure.value.Coord;
import backend.model.figure.value.PlayerColor;

public abstract class Player {


    private ClickCommand command;
    PlayerColor player;

    protected Player(ClickCommand command, PlayerColor player) {
        this.command = command;
        this.player = player;
    }

   final void click(Coord coord) {
        command.clickOnCoords(coord,player);
    }
}
