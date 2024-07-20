package swing.factory;

import backend.model.figure.value.Coord;
import swing.model.field.Field;
import swing.model.field.FieldImpl;

import java.awt.*;
import java.util.*;

import static java.awt.Color.*;

//build field objects
public final class FieldFactory {

    private static final Color WHITE_FIELD = GRAY;
    private static final Color BLACK_FIELD = DARK_GRAY;

    private static Field empty;

    public  Map<Coord, Field> buildFields() {
        Map<Coord, Field> fields = new HashMap<>();
        for (int row = 1; row <= 8; row++) {
            for (int column = 1; column <= 8; column++) {
                Coord coord = new Coord(row, column);
                fields.put(coord, new FieldImpl(coord, getColorForCoords(coord)));
            }
        }
        return fields;
    }

    private Color getColorForCoords(Coord coord) {
        if (isNumberEven(coord.getRow()) != isNumberEven(coord.getColumn())) {
            return BLACK_FIELD;
        }
        return WHITE_FIELD;
    }

    private boolean isNumberEven(int number) {
        return number % 2 == 0;
    }


    public static Field getEmptyField() {
        if (Objects.isNull(empty)) {
            empty = new FieldImpl(new Coord(-1,' '),Color.CYAN);
        }
        return empty;
    }
}
