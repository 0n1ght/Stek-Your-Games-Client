package pl.monopoly.model;

import java.util.Random;

public class Cubes {
    private final Random random = new Random();
    private int roll1, roll2;
    private final Game game;

    // create
    public Cubes(Game game) {
        this.game = game;
    }

    // methods
    public void rollTheDice() {

        roll1 = random.nextInt(6)+1;
        roll2 = random.nextInt(6)+1;

        game.actualPlayer().move(roll1+roll2);

        game.interactiveField();

        game.nextRound();

    }

    public int getRoll1() {
        return roll1;
    }
    public int getRoll2() {
        return roll2;
    }
}
