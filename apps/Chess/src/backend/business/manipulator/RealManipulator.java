package backend.business.manipulator;

import backend.business.analize.analizator.AnalizatorBase;
import backend.model.game.ChessGame;

public class RealManipulator extends ManipulatorBase implements Manipulator {
    public RealManipulator(ChessGame game, AnalizatorBase analizator) {
        super(game, analizator);
    }
}
