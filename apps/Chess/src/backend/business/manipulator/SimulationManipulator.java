package backend.business.manipulator;

import backend.model.game.ChessGame;

public interface SimulationManipulator extends Manipulator {

    void setGameCopy(ChessGame copy);

}
