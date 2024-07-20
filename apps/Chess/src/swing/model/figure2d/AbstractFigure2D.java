package swing.model.figure2d;

import backend.model.figure.value.Coord;
import swing.Pixel;
import backend.model.figure.model.Figure;
import swing.Board;
import swing.display.assets.Asset;

import java.awt.*;

public abstract class AbstractFigure2D implements Figure2D{

    private Figure figure;
    private Asset asset;
    private int x;
    private int y;
    private int destinationX;
    private int destinationY;
    private double speedX;
    private double speedY;
    private boolean moving;
    private Coord oldField;
    private Coord newField;
    private boolean beat;


    public AbstractFigure2D(Figure figure) {
        this.figure = figure;
        asset = Asset.getAsset(figure.getType(), figure.getPlayer());
        moving = false;
       oldField = figure.getCoord();
        newField = figure.getCoord();
        setLocation(newField);
        beat = figure.isBeat();
    }

    @Override
    public void render(Graphics g) {
        if (beat) {
            return;
        }

        g.drawImage(asset.getAssetImage(),
                x, y,
                Board.getFieldSize(), Board.getFieldSize(),
                null);
    }


    @Override
    public void move() {
        newField = figure.getCoord();
        if (oldField == newField) {
            moving = false;
            return;
        }
        moving = true;
        calculateMove();
    }

    @Override
    public boolean isMoving() {
        return moving;
    }

    @Override
    public void tick() {
        if (!moving) {
            return;
        }
        boolean doneY = false;
        boolean doneX = false;


        if (y <= destinationY +3 && y >= destinationY -3) {
            doneY = true;
            speedY = 0;
            y = destinationY;
        } else {
            y += speedY;
        }

        if (x <= destinationX +3 && x >= destinationX -3) {
            doneX = true;
            speedX = 0;
            x = destinationX;
        } else {
            x += speedX;
        }

        if (doneX && doneY) {
            moving = false;
        }
    }


    private void setLocation(Coord coord) {
        Pixel pixel = coord.convertToPixels();
        x = pixel.getX();
        y = pixel.getY();
    }
    private void setDestination(Coord coord) {
        Pixel pixel = coord.convertToPixels();
        destinationX = pixel.getX();
        destinationY = pixel.getY();
    }



    private void calculateMove() {
        System.out.println("new destinaaation");
       setDestination(newField);
        if (x == destinationX) {
            speedX = 0;
        } else if (x - destinationX < 0) {
            speedX = 3;
        } else {
            speedX = -3;
        }
        if (y == destinationY) {
            speedY = 0;
        }else if (y - destinationY < 0) {
            speedY = 3;
        } else {
            speedY = -3;
        }
    }

    public void checkIfBeat() {
        beat = figure.isBeat();
    }

}
