package backend.model.factory;

import backend.model.figure.value.Coord;

import java.util.ArrayList;
import java.util.List;

public class CoordsFactory {

    private static Coord empty;

    public List<Coord> buildBoardCoords() {
        List<Coord> coords = new ArrayList<>();
        for (int row = 1; row <= 8; row++) {
            for (int column = 1; column <= 8; column++) {
                Coord coord = new Coord(row, column);
                coords.add(coord);
            }
        }
        return coords;
    }

    public static Coord getEmptyCoord() {
        if (empty == null) {
            empty = new Coord(Integer.MIN_VALUE, Integer.MIN_VALUE);
        }
        return empty;
    }
}
