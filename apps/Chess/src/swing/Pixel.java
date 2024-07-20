package swing;

import backend.model.figure.value.Coord;

public final class Pixel {
    private final int x;
    private final int y;

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coord convertToCoords() {
        return new Coord(
                y / Board.getFieldSize() + 1,
                x / Board.getFieldSize() + 1
        );
    }

    @Override
    public String toString() {
        return "Pixel{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
