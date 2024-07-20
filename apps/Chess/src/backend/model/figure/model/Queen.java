package backend.model.figure.model;

import backend.business.modelLogic.behavior.Behavior;
import backend.model.figure.value.Coord;
import backend.model.figure.value.PlayerColor;

import static backend.model.figure.value.FigureType.QUEEN;

public final class Queen extends AbstractFigure {


    public Queen(Coord coord, PlayerColor player,  Behavior behavior) {
        super(QUEEN, coord, player, behavior);
    }

}
