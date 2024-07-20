package pl.monopoly.model;

public class Player {
    private int field;
    private static int nextId = 0;
    private final int id;
    private int money = 2_000;
    private final Game game;

    // create
    public Player(Game game) {

        this.game = game;
        id = nextId;
        nextId++;
    }

    // methods

    public void move(int movesNumber){

        field += movesNumber;

        if (field > 40) {

            money += 100;
            field -= 40;
        }

    }

    public void pay(int price) {

        money -= price;

    }

    // get/set
    public int getId() {
        return id;
    }

    public int getFieldNumber() {
        return field;
    }

    public int getMoney() {
        return money;
    }

    public PlayerColor getColor() {

        return switch (id) {
            case 0 -> PlayerColor.RED;
            case 1 -> PlayerColor.BLUE;
            case 2 -> PlayerColor.GREEN;
            case 3 -> PlayerColor.PURPLE;
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean isSingle() {
        return game.playerSingle(this);
    }

    public boolean isBankrupt() {
        return money < 0;
    }

    public static void resetId() {
        nextId = 0;
    }

    @Override
    public String toString() {
        return "Player " + id;
    }
}
