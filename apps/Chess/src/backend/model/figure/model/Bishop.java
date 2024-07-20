package backend.model.figure.model;

import backend.business.modelLogic.behavior.Behavior;
import backend.model.figure.value.Coord;
import backend.model.figure.value.PlayerColor;

import static backend.model.figure.value.FigureType.BISHOP;

public final class Bishop extends AbstractFigure {
    public Bishop(Coord coord, PlayerColor color,  Behavior behavior) {
        super(BISHOP, coord, color, behavior);
    }

}
