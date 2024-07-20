package backend.business.analize.analizator;

import backend.model.game.ChessGame;

public class SimulationAnalizatorImpl extends AnalizatorBase implements SimulationAnalizator {
    public SimulationAnalizatorImpl(MoveValidator validator, ChessGame game) {
        super(validator, game);
    }

    @Override
    public void setGameCopy(ChessGame copy) {
        game = copy;
    }
}
