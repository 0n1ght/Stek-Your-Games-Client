package backend.model.figure.value;

import swing.Board;
import swing.Pixel;
import java.util.Objects;

public final class Coord {
    private final int row;
    private final int column;

    public Coord(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Pixel convertToPixels() {
        int x = convertColumnToPixel(column);
        int y = convertRowToPixel(row);
        return new Pixel(x, y);
    }

    private int convertRowToPixel(int number) {
        int y = 0;
        for (int i = 1; i < number; i++) {
            y += Board.getFieldSize();
        }
        return y;
    }

    private int convertColumnToPixel(int column) {
        int x = 0;
        for (int i = 1; i < column; i++) {
            x += Board.getFieldSize();
        }
        return x;
    }

    public Coord whoHasMoreRows(Coord other) {
        return row > other.row ? this : other;
    }
    public Coord whoHasLessRows(Coord other) {
        return row < other.row ? this : other;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return row == coord.row &&
                column == coord.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "Coords{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    public Coord swap() {
        return new Coord(column, row);
    }

    public boolean areNotValid() {
        return row <= 0 || row > 8 || column <= 0 || column > 8;
    }

    public boolean isSameColumn(Coord other) {
        return column == other.column;
    }

    public boolean sameRowOrColumn(Coord other) {
        return isSameRow(other) || isSameColumn(other);
    }

    private boolean isSameRow(Coord other) {
        return row == other.row;
    }
}
