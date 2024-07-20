package backend.bot;

import backend.border.Controller;
import backend.model.Move;
import backend.model.figure.value.PlayerColor;

public abstract class AbstractBot {

     Controller controller;
     PlayerColor player;
     AISimulation simulation;

    public AbstractBot(Controller controller, PlayerColor player, AISimulation simulation) {
        this.controller = controller;
        this.player = player;
        this.simulation = simulation;
    }

    public final Move computeMove() {
        return findBestMove();
    }

    abstract Move findBestMove();

}
