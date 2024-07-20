package backend.business.manipulator;

import backend.business.analize.analizator.Analizator;
import backend.model.game.ChessGame;

import static backend.model.figure.value.PlayerColor.*;

public abstract class ManipulatorBase extends AbstractManipulator {

    public ManipulatorBase(ChessGame game, Analizator analizator) {
        super(game, analizator);
    }

    @Override
    void nextPlayer() {
        switch (game.getPlayer()) {
            case BLACK:
                game.setPlayer(WHITE);
                break;
            case WHITE:
                game.setPlayer(BLACK);
                break;
        }
    }

    @Override
    void setPat() {
        if (analizator.isPat()) {
            game.setPat(true);
        }
    }

    @Override
    void setCheck() {
        if (analizator.isCheckPlayer(game.getPlayer())) {
            game.setCheck(game.getPlayer());
        } else if (analizator.isCheckPlayer(game.lastPlayer())) {
            game.setCheck(game.lastPlayer());
        } else {
            game.setCheck(NO_ONE);
        }
    }

    @Override
    void setCheckMate() {
        if (analizator.isCheckMate()) {
            game.setCheckMate(true);
        }
    }


}
