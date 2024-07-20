package pl.monopoly;

import pl.monopoly.view.ViewFactory;

public class Launcher {

    public static void main(String[] args) {

        ViewFactory viewFactory = new ViewFactory();
        viewFactory.createMenu();

    }

}
