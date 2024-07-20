package backend.model.figure.model;

import backend.business.modelLogic.behavior.Behavior;
import backend.model.figure.value.Coord;
import backend.model.figure.value.PlayerColor;

import static backend.model.figure.value.FigureType.KING;

public final class King extends AbstractFigure {
    public King(Coord coord, PlayerColor color,  Behavior behavior) {
        super(KING, coord, color, behavior);
    }

}
