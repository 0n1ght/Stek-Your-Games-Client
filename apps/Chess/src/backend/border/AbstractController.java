package backend.border;

import backend.business.analize.analizator.AnalizatorBase;
import backend.business.analize.event.MoveEvent;
import backend.business.manipulator.Manipulator;
import backend.model.game.ChessGame;
import backend.model.figure.value.Coord;
import backend.model.figure.value.PlayerColor;
import backend.model.figure.model.Figure;
import backend.business.analize.analizator.PossibleMoves;

import java.util.Collection;
import java.util.Set;

import static backend.model.figure.value.FigureType.KING;

//fasada dla całej logiki gry i aktualnego jej stanu || powinna także czytać i manipulować
//symulacjami
public abstract class AbstractController implements Controller {

    private Manipulator manipulator;
    private ChessGame game;
    private AnalizatorBase analizator;

    public AbstractController(Manipulator manipulator, ChessGame game, AnalizatorBase analizator) {
        this.manipulator = manipulator;
        this.game = game;
        this.analizator = analizator;
    }

    @Override
    public Collection<Figure> getAll() {
        return game.getAll();
    }

    @Override
    public PossibleMoves generatePossibleMoves(Figure figure) {
      return   analizator.getPossibleMoves(figure);
    }

    @Override
    public Figure getFigure(Coord coord) {
        return game.getFigure(coord);
    }

    @Override
    public PlayerColor getPlayer() {
        return game.getPlayer();
    }

    @Override
    public void move(MoveEvent move) {
        manipulator.move(move);
    }

    @Override
    public Set<Figure> getAllOfPlayer(PlayerColor player) {
        return game.getAllFiguresOfPlayer(player);
    }

    @Override
    public PossibleMoves getPossibleMoves(Figure figure) {
        return analizator.getPossibleMoves(figure);
    }

    @Override
    public PlayerColor isCheck() {
        return game.isCheck();
    }

    @Override
    public Coord getCheckCoord() {
        return game.getFigureByType(KING, isCheck())
                .getCoord();
    }

    @Override
    public boolean isCheckMate() {
        return game.isCheckMate() ;
    }

    @Override
    public boolean isPat() {
        return game.isPat();
    }
}
