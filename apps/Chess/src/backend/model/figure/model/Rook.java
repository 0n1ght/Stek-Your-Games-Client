package backend.model.figure.model;

import backend.business.modelLogic.behavior.Behavior;
import backend.model.figure.value.Coord;
import backend.model.figure.value.PlayerColor;

import static backend.model.figure.value.FigureType.ROOK;

public final class Rook extends AbstractFigure {


    public Rook(Coord coord, PlayerColor player,  Behavior behavior) {
        super(ROOK, coord, player, behavior);
    }

}
