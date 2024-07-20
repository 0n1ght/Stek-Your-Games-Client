package backend.model.figure.model;

import backend.business.modelLogic.behavior.Behavior;
import backend.model.figure.value.Coord;
import backend.model.figure.value.PlayerColor;

import static backend.model.figure.value.FigureType.KNIGHT;

public final class Knight extends AbstractFigure {


    public Knight(Coord coord, PlayerColor player,  Behavior behavior) {
        super(KNIGHT, coord, player, behavior);
    }

}
