package pl.monopoly.view;

import pl.monopoly.model.*;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.swing.ImageIcon;

public class Gameplay {
    private final List<PlayerView> playerViewList = new LinkedList<>();
    private final List<BuyAbleFieldView> buyAbleFieldViewList = new ArrayList<>();
    private final List<CustomButtonView> customButtonViewList = new ArrayList<>();
    private final ScoreView scoreView;
    private static Graphics graphics;
    protected static int colorIndex = 0;

    // Create
    public Gameplay(MouseManager manager, ViewFactory viewFactory) throws IOException {

        Game game = new Game(viewFactory, this);

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < SettingsState.getInstance().getPlayersNumber(); i++) {
            Player player = new Player(game);
            players.add(player);
            playerViewList.add(new PlayerView(player));
        }

        game.addPlayers(players);
        scoreView = new ScoreView(players);
        CubesView cubesView = new CubesView(new Cubes(game));
        SettingsInGameButtonView settingsButtonView = new SettingsInGameButtonView();
        customButtonViewList.addAll(List.of(settingsButtonView, cubesView));
        manager.setCustomButtonViewList(customButtonViewList);

        for (Field field : game.getBoard().getFields()) {
            if (field.isBuyAble()) {
                buyAbleFieldViewList.add(new BuyAbleFieldView((BuyAbleField) field));
            }
        }
    }

    public void render(Graphics g) {
        graphics = g;
        setBackgroundColor(colorIndex);
        graphics.fillRect(0, 0, Display.getWidth(), Display.getHeight());
        graphics.translate(Display.getRelativeX(), Display.getRelativeY());

        // Use ImageIcon to load images from the JAR
        Image image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/board.png"))).getImage();
        graphics.drawImage(image, 0, 0, null);

        for (CustomButtonView customButtonView : customButtonViewList) {
            customButtonView.render(g);
        }

        for (BuyAbleFieldView buyAbleFieldView : buyAbleFieldViewList) {
            buyAbleFieldView.renderX(g);
            buyAbleFieldView.renderHouse(g);
        }

        for (PlayerView playerView : playerViewList) {
            playerView.render(g);
        }

        scoreView.render(g);
    }

    public static void setBackgroundColor(int colorIndex) {
        switch (colorIndex) {
            case 0 -> graphics.setColor(new Color(102, 255, 102));
            case 1 -> graphics.setColor(new Color(51, 153, 255));
            case 2 -> graphics.setColor(new Color(255, 102, 102));
            case 3 -> graphics.setColor(new Color(255, 255, 0));
        }
        graphics.fillRect(0, 0, Display.getWidth(), Display.getHeight());
    }

    public void switchLight(int playerId) {
        for (PlayerView playerView : playerViewList) {
            playerView.setMarked(playerView.getPlayer().getId() == playerId);
        }
    }
}
