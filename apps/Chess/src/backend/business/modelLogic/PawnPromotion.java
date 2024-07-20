package backend.business.modelLogic;

import backend.model.factory.FigureFactory;
import backend.model.figure.model.Figure;
import backend.model.figure.value.BoardSide;
import backend.model.game.ChessGameImpl;
import backend.model.repository.FigureRepository;

import static backend.model.figure.value.FigureType.PAWN;

public class PawnPromotion {

    private ChessGameImpl game;
    private FigureRepository repository;


   public void tryPromotingPawn(ChessGameImpl game, FigureRepository repository) {
       this.game = game;
       this.repository = repository;
        Figure pawn = getPromotedPawn();
        promote(pawn);
    }

   private Figure getPromotedPawn() {
        Figure lastPlayed = game.getLastPlayed();
        if (!lastPlayed.getType().equals(PAWN)) {
            return FigureFactory.getEmpty();
        }
        BoardSide side = lastPlayed.getSide();
        BoardSide opponentSide = BoardSide.getOtherSide(side);
        if (opponentSide.getFiguresRow() != lastPlayed.getRow()) {
            return FigureFactory.getEmpty();
        }
        return lastPlayed;
    }

   private void promote(Figure pawn) {
        if (pawn.isNullObject()) {
            return;
        }
        pawn.beat();
        Figure strongest = repository.getStrongestBeatenFigure(game.getPlayer());
        if (strongest.isNullObject()) {
            return;
        }
        strongest.unBeat();
        strongest.move(pawn.getCoord());
        repository.updateCoord(strongest);
    }

}
