package swing.input.players;

import backend.bot.Bot;
import backend.model.Move;
import backend.model.figure.value.PlayerColor;
import swing.states.general.StateObserver;
import swing.states.general.StateStatus;
import swing.states.general.StateType;

import java.util.Random;

public class BotCommunicator extends Player implements StateObserver {

    private Bot bot;
    private boolean ready;


    public BotCommunicator(ClickCommand command, PlayerColor player, Bot bot) {
        super(command, player);
        this.bot = bot;
        ready = true;
    }


    @Override
    public void gameStateChanged(StateStatus status) {
        if (status.getPlayer() != player || !ready || status.getType() != StateType.WAITING) return;
        ready = false;
        move();
        ready = true;
    }

    private void move() {
            Move move = bot.computeMove();
            Random random = new Random();
//            Thread.sleep(random.nextInt(500)+100);
            click(move.getStart());
//            Thread.sleep(random.nextInt(500)+250);


            click(move.getDestination());

    }
}
