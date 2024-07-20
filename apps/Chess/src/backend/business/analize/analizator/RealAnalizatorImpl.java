package backend.business.analize.analizator;

import backend.model.game.ChessGame;

public class RealAnalizatorImpl extends AnalizatorBase implements Analizator {
    public RealAnalizatorImpl(MoveValidator validator, ChessGame game) {
        super(validator, game);
    }
}
