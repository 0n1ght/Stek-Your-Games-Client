package swing.input.players;

import backend.model.figure.value.Coord;
import backend.model.figure.value.PlayerColor;
import swing.input.ClickObserver;

public class OfflinePlayer extends Player implements ClickObserver {

    public OfflinePlayer(ClickCommand command, PlayerColor player) {
        super(command, player);
    }
    @Override
    public void onClick(Coord coord) {
        click(coord);
    }
}
