package backend.border;


import backend.business.analize.analizator.AnalizatorBase;
import backend.business.manipulator.Manipulator;
import backend.model.game.ChessGame;

public class ControllerImpl extends AbstractController {

    public ControllerImpl(Manipulator manipulator, ChessGame chessGame, AnalizatorBase analizator) {
        super(manipulator, chessGame, analizator);
    }
}
