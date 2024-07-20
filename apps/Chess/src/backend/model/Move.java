package backend.model;

import backend.model.figure.value.Coord;

import java.util.Objects;

public class Move {

    protected final Coord start;
     protected final Coord destination;

    public Move(Coord start, Coord destination) {
        this.start = start;
        this.destination = destination;
    }

    public Coord getStart() {
        return start;
    }


    public Coord getDestination() {
        return destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return start.equals(move.start) &&
                destination.equals(move.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, destination);
    }


    @Override
    public String toString() {
        return "Move{" +
                "selected=" + start +
                ", destination=" + destination +
                '}';
    }
}
