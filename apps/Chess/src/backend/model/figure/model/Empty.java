package backend.model.figure.model;

import backend.business.analize.event.MoveEvent;
import backend.model.factory.CoordsFactory;
import backend.model.figure.value.BoardSide;
import backend.model.figure.value.Coord;
import backend.model.figure.value.FigureType;
import backend.model.figure.value.PlayerColor;
import backend.business.modelLogic.behavior.BehaviorImpl;

import java.util.ArrayList;
import java.util.List;

public class Empty extends AbstractFigure {
    public Empty() {
        super(FigureType.UNKNOWN, CoordsFactory.getEmptyCoord(), PlayerColor.NO_ONE,  new BehaviorImpl());
    }


    @Override
    public boolean isNullObject() {
        return true;
    }

    @Override
    public List<MoveEvent> generateMoves() {
        return new ArrayList<>();
    }

    @Override
    public void move(Coord newCoord) {

    }

    @Override
    public void beat() {
    }

    @Override
    public boolean isBeat() {
        return false;
    }

    @Override
    public BoardSide getSide() {
        return BoardSide.UNKNOWN;
    }

    @Override
    public int getRow() {
        return super.getRow();
    }

    @Override
    public int getColumn() {
        return super.getColumn();
    }

    @Override
    public FigureType getType() {
        return super.getType();
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
