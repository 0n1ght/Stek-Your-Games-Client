package backend.model.figure.model;

import backend.business.modelLogic.behavior.Behavior;
import backend.model.figure.value.Coord;
import backend.model.figure.value.PlayerColor;

import static backend.model.figure.value.FigureType.PAWN;

public  final class Pawn extends AbstractFigure {

    private boolean enPassantPossible;

    public Pawn(Coord coord, PlayerColor player,  Behavior behavior) {
        super(PAWN, coord, player, behavior);
        enPassantPossible = false;
    }


    //TODO simplify
    @Override
    public void move(Coord newCoord) {
        if (!moved && newCoord.getRow() == side.getPawnsDoubleMoveRow()) {
            enPassantPossible = true;
        } else {
            enPassantPossible = false;
        }
        super.move(newCoord);
    }

    @Override
    public boolean canBeEnPassanted() {
        return enPassantPossible;
    }
}
