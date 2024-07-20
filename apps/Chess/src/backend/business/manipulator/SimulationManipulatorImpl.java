package backend.business.manipulator;

import backend.business.analize.analizator.Analizator;
import backend.model.game.ChessGame;

public class SimulationManipulatorImpl extends ManipulatorBase implements SimulationManipulator {
    public SimulationManipulatorImpl(ChessGame game, Analizator analizator) {
        super(game, analizator);
    }

    @Override
    public void setGameCopy(ChessGame copy) {
        game = copy;
    }
}
