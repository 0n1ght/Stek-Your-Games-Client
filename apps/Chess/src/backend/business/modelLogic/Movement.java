package backend.business.modelLogic;

import backend.business.analize.event.MoveEvent;
import backend.model.figure.value.Coord;
import backend.model.figure.model.Figure;
import backend.business.analize.event.MovingStyle;
import backend.model.game.ChessGameImpl;
import backend.model.repository.FigureRepository;


public class Movement {
    private ChessGameImpl game;
    private FigureRepository repository;

    private Figure selectedFigure;
    private Coord destination;
    private MovingStyle style;

    public void move(MoveEvent move, ChessGameImpl game, FigureRepository repository) {
        setData(move, game, repository);
        moveTypeDependentMove();
    }

    private void setData(MoveEvent move, ChessGameImpl game, FigureRepository repository) {
        this.game = game;
        this.repository = repository;
        Coord selectedCoord = move.getStart();
        destination = move.getDestination();
        selectedFigure = repository.get(selectedCoord);
        style = move.getStyle();
    }

    private void moveTypeDependentMove() {
        doTypeSpecificStuff();
        changeLocation(selectedFigure,destination);
        game.setLastPlayed(selectedFigure);
    }

    private void doTypeSpecificStuff() {
        switch (style) {
            case MOVE:
                break;
            case ATTACK:
                beatOtherFigure(destination);
                break;
            case CASTLING:
                moveRookInCastling(destination);
                break;
            case EN_PASSANT:
                beatOtherFigure(game.getLastPlayed().getCoord());
                break;
        }
    }

    private void changeLocation(Figure figure, Coord destination) {
        Coord oldCoord = figure.getCoord();
        figure.move(destination);
        repository.updateCoord(figure);
        repository.clearCoord(oldCoord);
    }

    private void beatOtherFigure(Coord coord) {
        if (!repository.hasFigure(coord)) {
            return;
        }
        Figure enemy = repository.get(coord);
        enemy.beat();
        repository.clearCoord(coord);
    }

    private void moveRookInCastling(Coord move) {
        Figure rook = getRookForCastling(move);
        int rookColumn = rook.getColumn();
        if (rookColumn == 1) {
            changeLocation(rook, new Coord(move.getRow(), 4));
        } else {
            changeLocation(rook, new Coord(move.getRow(), 6));
        }
    }

    private Figure getRookForCastling(Coord destination) {
        int moveColumn = destination.getColumn();
        if (moveColumn == 3) {
            return repository.get(new Coord(destination.getRow(), 1));
        }
        return repository.get(new Coord(destination.getRow(), 8));
    }

}
