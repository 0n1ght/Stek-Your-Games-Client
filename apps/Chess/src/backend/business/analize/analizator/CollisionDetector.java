package backend.business.analize.analizator;

import backend.business.analize.event.MovingStyle;
import backend.model.figure.model.Figure;
import backend.model.figure.value.Coord;
import backend.model.game.ChessGame;
import backend.model.factory.CoordsFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static backend.business.analize.event.MovingStyle.TELEPORT;

//sprawdza czy na drodze ruchu są jakieś figury
//todo basic refactor
public class CollisionDetector {
    //can be refactored to strategy pattern if we need other variants of colision detections


    private Figure moving;
    private Coord destination;
    private List<Coord> coordsBetweenMoveAndFigure;

    private ChessGame game;

    public CollisionDetector() {
        destination = CoordsFactory.getEmptyCoord();
        coordsBetweenMoveAndFigure = Collections.emptyList();
    }


    boolean isNotInRange(ChessGame game, Figure moving, Coord destination, MovingStyle style) {
        initializeData(game,moving, destination);
        if (checkTypeIfCanSkipRangeTest(style)) {
            return false;
        }
        coordsBetweenMoveAndFigure = generateCoordsBetween();
        return isFigureBetween();
    }

    private void initializeData(ChessGame game,Figure moving, Coord destination) {
        this.game = game;
        this.moving = moving;
        this.destination = destination;
    }

    private boolean checkTypeIfCanSkipRangeTest(MovingStyle style) {
        return style == TELEPORT;
    }

    private List<Coord> generateCoordsBetween() {
        Coord selectedCoord = moving.getCoord();

        if (selectedCoord.sameRowOrColumn(destination)) {
            return generateFieldsBetweenStraightMove();
        }
        return generateFieldsBetweenSlantMove();

    }


    private List<Coord> generateFieldsBetweenStraightMove() {
        List<Coord> coordsBetween = new ArrayList<>();
        Coord selected = moving.getCoord();
        boolean horizontal = true;
        int selectedNum = selected.getColumn();
        int moveNum = destination.getColumn();
        int stable = selected.getRow();

        //sprawdza czy pion czy poziom
        if (selected.isSameColumn(destination)) {
            horizontal = false;
        }
        if (!horizontal) {
            selectedNum = selected.getRow();
            moveNum = destination.getRow();
            stable = selected.getColumn();
        }

        //iteruje po coordach pomiędzy
        for (int i = Math.min(selectedNum, moveNum) + 1;
             i < Math.max(selectedNum, moveNum); i++) {
            Coord coord = new Coord(stable, i);
            if (!horizontal) {
                coord = coord.swap();
            }
            coordsBetween.add(coord);
        }
        return coordsBetween;
    }

    private List<Coord> generateFieldsBetweenSlantMove() {
        List<Coord> coordsBetween = new ArrayList<>();
        Coord selected = moving.getCoord();
        boolean rightToLeft = false;
        Coord more = selected.whoHasMoreRows(destination);
        Coord less = selected.whoHasLessRows(destination);
        if (less.getColumn() > more.getColumn()) {
            rightToLeft = true;
        }
        for (int row = less.getRow() + 1, column = less.getColumn();
             row < more.getRow(); row++) {
            if (rightToLeft) {
                column--;
            } else {
                column++;
            }
            Coord coord = new Coord(row, column);
            coordsBetween.add(coord);

        }
        return coordsBetween;
    }

    private boolean isFigureBetween() {
        for (Coord coord : coordsBetweenMoveAndFigure) {
            if (game.hasFigure(coord)) {
                return true;
            }
        }
        return false;
    }



}
