package pl.monopoly.model;

public abstract class Field {
    private static int nextNumber = 0;
    protected final int number = nextNumber++;
    protected Game game;

    // create
    public Field(Game game) {
        this.game = game;
    }

    // methods
    public abstract void action(Player player, Board board);

    public abstract boolean isBuyAble();

    // get/set
    public int getNumber() {
        return number;
    }
    public Game getGame() {return game;}
}
