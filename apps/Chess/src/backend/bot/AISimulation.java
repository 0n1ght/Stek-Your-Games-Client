package backend.bot;

import backend.business.analize.analizator.Analizator;
import backend.business.analize.analizator.SimulationAnalizator;
import backend.business.analize.event.MoveEvent;
import backend.business.analize.event.MovingStyle;
import backend.business.manipulator.SimulationManipulator;
import backend.model.figure.model.Figure;
import backend.model.figure.value.Coord;
import backend.model.figure.value.PlayerColor;
import backend.model.game.ChessGame;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static backend.business.analize.event.MovingStyle.CASTLING;
import static backend.model.figure.value.FigureType.PAWN;

public class AISimulation {

    private static final double POINTS_FOR_BEATING_STRENGTH = 100;
    private static final double POINTS_FOR_THREATENING = 25;
    private static final double POINTS_FOR_CASTLING = 10;

    private static final double POINTS_FOR_FUTURE_MOVE = 1;
    private static final int POINTS_FOR_MOVE_ON_CENTER = 1;
    private static final double PAWN_MOVE = 6;
    private static final double POINTS_FOR_FUTURE_DEFENCE = 3;
    private static final int POINTS_FOR_FIGURE_ON_CENTER = 3;

    private static final List<Coord> CENTER = List.of(new Coord(4, 4), new Coord(4, 5),
            new Coord(5, 4), new Coord(5, 5));

    private ChessGame game;
    private ChessGame copy;
    private SimulationManipulator manipulator;
    private SimulationAnalizator simulationAnalizator;
    private Analizator realAnalizator;

    private MoveEvent move;
    private Figure figure;
    private Figure enemy;
    private Coord destination;
    private Coord start;
    private MovingStyle style;


    public AISimulation(ChessGame game, SimulationManipulator manipulator, SimulationAnalizator simulationAnalizator, Analizator analizator) {
        this.game = game;
        this.manipulator = manipulator;
        this.simulationAnalizator = simulationAnalizator;
        this.realAnalizator = analizator;

        prepareGameCopy();
    }

    private void prepareGameCopy() {
        try {
            copy = game.copy();
            manipulator.setGameCopy(copy);
            simulationAnalizator.setGameCopy(copy);
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("IA can't prepare copy");
        }

    }

    public void rateMove(MoveEvent move) {
        prepareMoveData(move);
        prepareGameCopy();
        manipulator.move(move);
        giveRate();
    }

    private void prepareMoveData(MoveEvent move) {
        this.move = move;
        figure = getFigure();
        enemy = getEnemy();
        start = figure.getCoord();
        destination = move.getDestination();
        style = move.getStyle();
    }

    private void giveRate() {
        if (enemyNotDefended()) {
            addPointsForBeatingFree();
        } else if (isGoodTrade()) {
            addPointsForBeatingDefended();
        }
        removePointsForFiguresInDanger();
        addPointsForEnemyFiguresInDanger();
        addPointsForFuturePossibilities();
        addPointsForFutureDefences();
        addPointsForPawnMove();
        addPointsForCastling();
        addPointsForCenterOccupation();
    }


    private void addPointsForCenterOccupation() {
        int movesOnCenter = 0;
        int figuresOnCenter = 0;
        for (Coord coord : CENTER) {
            movesOnCenter += countMovesOnDestination(coord);
            figuresOnCenter += countFiguresOnDestination(coord);
        }
        move.addPoints(movesOnCenter * POINTS_FOR_MOVE_ON_CENTER);
        move.addPoints(figuresOnCenter * POINTS_FOR_FIGURE_ON_CENTER);
    }

    private int countMovesOnDestination(Coord destination) {
        return (int) simulationAnalizator.getAllPossibleMoves(game.getPlayer())
                .stream()
                .filter(moveEvent -> moveEvent.getDestination().equals(destination))
                .count();
    }

    private int countFiguresOnDestination(Coord destination) {
        return copy.getAllFiguresOfPlayer(game.getPlayer())
                .stream()
                .anyMatch(aFigure -> aFigure.getCoord().equals(destination))
                ? 1 : 0;

    }

    private void addPointsForCastling() {
        if (style == CASTLING) {
            move.addPoints(POINTS_FOR_CASTLING);
        }
    }

    private void addPointsForEnemyFiguresInDanger() {
        int figuresInDangerStrength = countStrengthOfFiguresInDangerAfterMove(game.lastPlayer());
        move.addPoints(figuresInDangerStrength * POINTS_FOR_THREATENING);
    }

    private void removePointsForFiguresInDanger() {
        int figuresInDangerStrength = countStrengthOfFiguresInDangerAfterMove(game.getPlayer());
        move.removePoints(figuresInDangerStrength * POINTS_FOR_BEATING_STRENGTH);
    }

    private int countStrengthOfFiguresInDangerAfterMove(PlayerColor player) {
        return (int) simulationAnalizator.getFiguresInDanger(player)
                .stream()
                .map(Figure::getStrength)
                .count();
    }

    private void addPointsForPawnMove() {
        if (figure.getType() != PAWN) {
            return;
        }
        int steppes = Math.abs(start.getRow() - destination.getRow());
        move.addPoints(PAWN_MOVE);
        if (steppes > 1) {
            move.addPoints(PAWN_MOVE / 2);
        }
    }


    private boolean enemyNotDefended() {
        if (!style.canAttack()) return false;
        return !isEnemyDefended();
    }

    //stara wersja
    private boolean isGoodTrade() {
        if (!style.canAttack()) return false;
        int strengthDifference = figure.compare(enemy);
        return strengthDifference > 0;
    }

    private Figure getEnemy() {
        return game.getFigure(move.getDestination());
    }

    private Figure getFigure() {
        return game.getFigure(move.getStart());
    }

    private void addPointsForBeatingFree() {
        move.addPoints(enemy.getStrength() * POINTS_FOR_BEATING_STRENGTH);
    }

    //old version
    private void addPointsForBeatingDefended() {
        int strengthDifference = figure.compare(enemy);
        move.addPoints(countPointsForBeating(strengthDifference));
    }

    //nowa nie działająca wersja
  /*  private void addPointsForBeatingDefended() {
        int strengthDifference =countGlobalEnemyStrengthBeforeAttack() - countGlobalStrengthAfterAttack();
        move.addPoints(countPointsForBeating(strengthDifference));
    }*/

    private void addPointsForFutureDefences() {
        int defences = simulationAnalizator.countDefences(game.getPlayer());
        move.addPoints(defences * POINTS_FOR_FUTURE_DEFENCE);
    }

    private void addPointsForFuturePossibilities() {
        int moves = simulationAnalizator.countMoves(game.getPlayer());
        move.addPoints(moves * POINTS_FOR_FUTURE_MOVE);
    }

    private double countPointsForBeating(int strengthDifference) {
        return strengthDifference * POINTS_FOR_BEATING_STRENGTH;
    }

    private boolean isEnemyDefended() {
        return simulationAnalizator.isAttacked(game.getPlayer(), destination);
    }

    private boolean isAttackedByEnemy() {
        return simulationAnalizator.isAttacked(game.getPlayer(), destination);
    }

    private void removePointsForLosingFigure() {
        move.removePoints(figure.getStrength() * POINTS_FOR_BEATING_STRENGTH);
    }

    //todo przerobic na stream
    private int countGlobalStrengthAfterAttack() {
        if (!style.canAttack()) return 0;
        List<MoveEvent> ownDefences = simulationAnalizator.getAllDefendingMoves(game.getPlayer());
        ownDefences.removeIf(moveEvent -> !moveEvent.getDestination().equals(destination));
        Set<Figure> figures = new HashSet<>();
        for (MoveEvent defending : ownDefences) {
            figures.add(copy.getFigure(defending.getStart()));
        }
        System.out.println("wynik wyliczenia:");
        System.out.println(figure);
        System.out.println("te figury bronią mnie po ataku:");
        System.out.println(figures);
        int strength = 0;
        for (Figure defender : figures) {
            strength += defender.getStrength();
        }
        System.out.println("łącznie mam siłę: " + (strength + figure.getStrength()));
        return strength + figure.getStrength();
    }

    private int countGlobalEnemyStrengthBeforeAttack() {
        if (!style.canAttack()) return 0;
        List<MoveEvent> ownDefences = realAnalizator.getAllDefendingMoves(game.lastPlayer());
        ownDefences.removeIf(moveEvent -> !moveEvent.getDestination().equals(destination));
        Set<Figure> figures = new HashSet<>();
        for (MoveEvent defending : ownDefences) {
            figures.add(game.getFigure(defending.getStart()));
        }
        int strength = 0;
        for (Figure defender : figures) {
            strength += defender.getStrength();
        }
        return strength + game.getFigure(destination).getStrength();
    }


}
