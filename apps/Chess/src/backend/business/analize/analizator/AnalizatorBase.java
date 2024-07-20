package backend.business.analize.analizator;

import backend.business.analize.event.MoveEvent;
import backend.model.figure.model.Figure;
import backend.model.figure.value.Coord;
import backend.model.figure.value.PlayerColor;
import backend.model.game.ChessGame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static backend.model.figure.value.FigureType.KING;
import static backend.model.figure.value.PlayerColor.NO_ONE;

//todo basic refactor
public class AnalizatorBase implements Analizator {

    private MoveValidator validator;
    ChessGame game;


    public AnalizatorBase(MoveValidator validator, ChessGame game) {
        this.validator = validator;
        this.game = game;
    }

    public PossibleMoves getPossibleMoves(Figure figure) {
        return computePossibleMoves(figure);
    }

    public PossibleMoves getDefendingMoves(Figure figure) {
        return computeDefendingMoves(figure);
    }

    public int countMoves(PlayerColor player) {
        return getAllPossibleMoves(player).size();
    }

    public int countDefences(PlayerColor player) {
        return getAllDefendingMoves(player).size();
    }

    //todo refactor!
    //todo getFiguresAttacking metoda żeby to zadziałało
    public List<Figure> getFiguresInDanger(PlayerColor player) {
        return game.getAllFiguresOfPlayer(player)
                .stream()
                .filter(figure -> isAttacked(player, figure.getCoord()) &&
                        (!isFigureDefended(player, figure)
                                || getSmallestAttackerStrength(player, figure.getCoord()) < figure.getStrength()))
                .collect(Collectors.toList());

    }

    public boolean isAttacked(PlayerColor askingPlayer, Coord coord) {
      return   !getAttackers(askingPlayer, coord).isEmpty();
    }

    private PossibleMoves computePossibleMoves(Figure figure) {
        PossibleMoves possibleMoves = computeAllMoves(figure);
        possibleMoves.removeDefendingMoves();
        return possibleMoves;
    }

    private PossibleMoves computeDefendingMoves(Figure figure) {
        PossibleMoves possibleMoves = computeAllMoves(figure);
        possibleMoves.retainOnlyDefendingMoves();
        return possibleMoves;
    }

    private PossibleMoves computeAllMoves(Figure figure) {
        return validator.computePossibleMoves(game, figure);
    }


    //todo refactor
    public int getSmallestAttackerStrength(PlayerColor askingPlayer, Coord coord) {
        List<Figure> attackers = getAttackers(askingPlayer, coord);
        return attackers.stream()
                .mapToInt(Figure::getStrength)
                .min()
                .orElse(0);

    }

    public List<Figure> getAttackers(PlayerColor askingPlayer, Coord coord) {
        PlayerColor attacker = PlayerColor.nextColor(askingPlayer);
        Collection<Figure> allFigures = game.getAllFiguresOfPlayer(attacker);
        List<Figure> attackers = new ArrayList<>();
        for (Figure figure : allFigures) {
            PossibleMoves possibleMoves = computePossibleMoves(figure);
            if (possibleMoves.isAttackPossible(coord)) {
                attackers.add(figure);
            }
        }
        return attackers;
    }


    public boolean isFigureDefended(PlayerColor player, Figure figure) {
        return getAllDefendingMoves(player)
                .stream()
                .anyMatch(moveEvent -> moveEvent.getDestination().equals(figure.getCoord()));
    }
   /* public boolean isFigureDefended(PlayerColor askingPlayer, Coord coord) {
        Collection<Figure> attackers = game.getAllFiguresOfPlayer(askingPlayer);
        for (Figure figure : attackers) {
            PossibleMoves possibleMoves = getDefendingMoves(figure);
            if (possibleMoves.hasMoveOn(coord)) {
                return true;
            }
        }
        return false;
    }*/

    //sprawdza stan planszy
    public boolean isCheckPlayer(PlayerColor player) {
        Figure king = game.getFigureByType(KING, player);
        return isAttacked(player, king.getCoord());
    }

    //sprawdza stan planszy
    public boolean isPat() {
        if (game.isCheck() != NO_ONE) {
            return false;
        }
        Collection<Figure> figures = game.getAllFiguresOfActualPlayer();
        for (Figure figure : figures) {
            if (canFigureMove(figure)) {
                return false;
            }
        }
        return true;
    }

    //sprawdza stan planszy
    private boolean canFigureMove(Figure figure) {
        PossibleMoves moves = computePossibleMoves(figure);
        return moves.areThereAnyMoves();
    }

    //sprawdza stan planszy

    public boolean isCheckMate() {
        if (game.isCheck() != game.getPlayer()) {
            return false;
        }
        Collection<Figure> figures = game.getAllFiguresOfActualPlayer();
        for (Figure figure : figures) {
            if (canFigureMove(figure)) {
                return false;
            }
        }
        return true;
    }

    public List<MoveEvent> getAllPossibleMoves(PlayerColor player) {
        return getPossibleMovesList(player)
                .stream()
                .flatMap(possibleMoves -> possibleMoves.getAll().stream())
                .collect(Collectors.toList());
    }


    private List<PossibleMoves> getPossibleMovesList(PlayerColor player) {
        return game.getAllFiguresOfPlayer(player)
                .stream()
                .map(this::getPossibleMoves)
                .collect(Collectors.toList());
    }

    public List<MoveEvent> getAllDefendingMoves(PlayerColor player) {
        return getDefendingPossibleMovesList(player)
                .stream()
                .flatMap(possibleMoves -> possibleMoves.getAll().stream())
                .collect(Collectors.toList());
    }


    private List<PossibleMoves> getDefendingPossibleMovesList(PlayerColor player) {
        return game.getAllFiguresOfPlayer(player)
                .stream()
                .map(this::getDefendingMoves)
                .collect(Collectors.toList());
    }


}
