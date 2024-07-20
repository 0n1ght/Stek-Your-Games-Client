package backend.model.figure.model;

import backend.business.analize.event.MoveEvent;
import backend.model.figure.value.BoardSide;
import backend.model.figure.value.Coord;
import backend.model.figure.value.FigureType;
import backend.model.figure.value.PlayerColor;
import backend.business.modelLogic.behavior.Behavior;

import java.util.List;

public abstract class AbstractFigure implements Figure, Cloneable {


    private final FigureType figureType;
    private final PlayerColor player;
    private final Behavior behavior;
    private Coord actualCoord;

    final BoardSide side;
    private boolean beat;
    boolean moved;

    AbstractFigure(FigureType figureType, Coord actualCoord, PlayerColor player, Behavior behavior) {
        this.figureType = figureType;
        this.actualCoord = actualCoord;
        this.player = player;
        this.behavior = behavior;

        side = deduceSide();
        beat = false;
        moved = false;
    }


    @Override
    public int compare(Figure other) {
        return other.getStrength()- getStrength();
    }

    private BoardSide deduceSide() {
        return getRow() <= 4 ? BoardSide.TOP : BoardSide.BOTTOM;
    }

    @Override
    public boolean isNullObject() {
        return false;
    }

    @Override
    public List<MoveEvent> generateMoves() {
        return behavior.generateMoves(this);
    }

    @Override
    public void move(Coord newCoord) {
        actualCoord = newCoord;
        moved = true;
    }

    @Override
    public boolean hasMoved() {
        return moved;
    }

    @Override
    public void beat() {
        beat = true;
    }

    @Override
    public void unBeat() {
        beat = false;
    }
    //TODO wywalić mediator i można zostawić shallow copy

    @Override
    public Figure copy() throws CloneNotSupportedException {
       return (Figure) clone();
    }

    @Override
    public boolean isBeat() {
        return beat;
    }

    @Override
    public BoardSide getSide() {
        return side;
    }

    @Override
    public PlayerColor getPlayer() {
        return player;
    }

    @Override
    public Coord getCoord() {
        return actualCoord;
    }

    @Override
    public int getRow() {
        return actualCoord.getRow();
    }

    @Override
    public int getColumn() {
        return actualCoord.getColumn();
    }

    @Override
    public FigureType getType() {
        return figureType;
    }

    @Override
    public int getStrength() {
        return figureType.getStrength();
    }

    @Override
    public boolean isBeaten() {
        return beat;
    }

    @Override
    public boolean canBeEnPassanted() {
        return false;
    }

    @Override
    public String toString() {
        return "AbstractFigure{" +
                "figureType=" + figureType +
                ", coord=" + actualCoord +
                ", player=" + player +
                '}';
    }
}
