package backend.business.analize.analizator;

import backend.business.analize.event.MoveEvent;
import backend.model.figure.model.Figure;
import backend.model.figure.value.Coord;
import backend.model.game.ChessGame;

//produce special informations for move for given game
public class MoveEventReader {

   public Figure getRookForCastling(MoveEvent move, ChessGame game) {
        Coord destination = move.getDestination();
        int moveColumn = destination.getColumn();
        if (moveColumn == 3) {
            return game.getFigure(new Coord(destination.getRow(), 1));
        }
        return game.getFigure(new Coord(destination.getRow(), 8));
    }
}
