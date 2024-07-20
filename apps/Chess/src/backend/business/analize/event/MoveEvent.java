package backend.business.analize.event;

import backend.model.Move;
import backend.model.factory.CoordsFactory;
import backend.model.figure.value.Coord;

import java.util.Objects;

import static backend.business.analize.event.MovingStyle.DEFENCE;

public class MoveEvent extends Move implements Comparable<MoveEvent> {

    private MovingStyle style;
    private double rate;
    private static MoveEvent empty;

    public MoveEvent(Coord selected, Coord destination, MovingStyle style) {
        super(selected, destination);
        this.style = style;
        rate = 0;
    }

    public MovingStyle getStyle() {
        return style;
    }

    public boolean isAttack() {
        return style.canAttack();
    }

    public boolean isMove() {
        return style.canMove();
    }

    public boolean isCastling() {
        return style == MovingStyle.CASTLING;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void addPoints(double rate) {
        this.rate += rate;
    }

    public double getRate() {
        return rate;
    }


    @Override
    public int compareTo(MoveEvent o) {
        return Double.compare(o.rate, rate);
    }

    @Override
    public String toString() {
        return "FullMove{" +
                ", rate=" + rate +
                "selected=" + start +
                ", destination=" + destination +
                '}';
    }

    public boolean isValidMove() {
        return !this.equals(empty);
    }

    public static MoveEvent getEmpty() {
        if (Objects.isNull(empty)) {
            empty = new MoveEvent(CoordsFactory.getEmptyCoord(), CoordsFactory.getEmptyCoord(), MovingStyle.UNKNOWN);
        }
        return empty;
    }

    public boolean isDefence() {
        return style == DEFENCE;
    }

    public void setStyle(MovingStyle style) {
        this.style = style;
    }

    public void removePoints(double points) {
        rate -= points;
    }
}
