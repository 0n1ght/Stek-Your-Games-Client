package pl.monopoly.view;

import java.io.IOException;

public class ViewFactory {
    private GameEngine gameEngine;

    public void createMenu() {

        MenuView menuView = new MenuView(this);
        menuView.displayMenu();
    }

    public void createSettings() {

        SettingsView settingsView = new SettingsView(this);
        settingsView.displaySettings();
    }

    public void createGame() throws IOException {
        MouseManager manager = new MouseManager();

        Gameplay gameplay = new Gameplay(manager, this);

        Display display = new Display();
        display.addListener(manager);

        gameEngine = new GameEngine(display, gameplay);
        gameEngine.start();

        display.showGame();
    }

    public void stopGame() {
        gameEngine.stop();
    }
}
