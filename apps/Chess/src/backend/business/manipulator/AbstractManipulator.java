package backend.business.manipulator;

import backend.business.analize.analizator.Analizator;
import backend.business.analize.event.MoveEvent;
import backend.model.game.ChessGame;

//zmienianie stanu planszy w trakcie wykonania ruchu
public abstract class AbstractManipulator implements Manipulator {

     ChessGame game;
     Analizator analizator;

    AbstractManipulator(ChessGame game, Analizator analizator) {
        this.analizator = analizator;
        this.game = game;
    }

    final public void move(MoveEvent move) {
        game.move(move);
        game.tryPromotingPawn();
        setCheck();
        setCheckMate();
        setPat();
        nextPlayer();
    }

    abstract void nextPlayer();

    abstract void setCheck();

    abstract void setPat();

    abstract void setCheckMate();

    public void setGame(ChessGame game) {
        this.game = game;
    }

}
