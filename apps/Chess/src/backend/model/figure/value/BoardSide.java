package backend.model.figure.value;

public enum BoardSide {
    TOP(1,2,4),
    BOTTOM(8,7,5),
    UNKNOWN(-1,-1,-1);

    private int figuresRow;
    private int pawnsRow;
    private int pawnsDoubleMoveRow;

    BoardSide(int figuresRow, int pawnsRow, int pawnsDoubleMoveRow) {
        this.figuresRow = figuresRow;
        this.pawnsRow = pawnsRow;
        this.pawnsDoubleMoveRow = pawnsDoubleMoveRow;
    }

    public int getPawnsRow() {
        return pawnsRow;
    }

    public int getFiguresRow() {
        return figuresRow;
    }

    public int getPawnsDoubleMoveRow() {
        return pawnsDoubleMoveRow;
    }

    public static BoardSide getOtherSide(BoardSide side) {
        switch (side) {

            case TOP:
                return BOTTOM;
            case BOTTOM:
                return TOP;
            default:
                throw new IllegalArgumentException("Not valid side object");
        }
    }

    public BoardSide otherSide() {
        return getOtherSide(this);
    }
}
