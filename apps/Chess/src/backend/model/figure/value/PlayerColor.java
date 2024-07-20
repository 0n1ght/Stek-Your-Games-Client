package backend.model.figure.value;

public enum PlayerColor {
    BLACK, WHITE, NO_ONE;

    public static PlayerColor nextColor(PlayerColor color) {
        switch (color) {

            case BLACK:
                return WHITE;
            case WHITE:
                return BLACK;
            default:
                return color;
        }
    }
}
