import swing.core.GameEngine;

public final class Launcher {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        GameEngine engine = configuration.buildGame();
        engine.start();
    }
}
