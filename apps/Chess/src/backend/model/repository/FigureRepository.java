package backend.model.repository;

import backend.model.figure.value.Coord;
import backend.model.figure.value.FigureType;
import backend.model.figure.value.PlayerColor;
import backend.model.figure.model.Figure;

import java.util.Collection;
import java.util.Set;

public interface FigureRepository {

    Collection<Figure> getAll();

    Figure get(Coord coord);

    void updateCoord(Figure figure);

    void clearCoord(Coord coord);

    Figure getEmptyFigure();

    boolean hasFigure(Coord coord);

    Set<Figure> getAllOfPlayer(PlayerColor player);

    Figure getFigureByType(FigureType type, PlayerColor player);

    FigureRepository copy() throws CloneNotSupportedException;

    Figure getStrongestBeatenFigure(PlayerColor player);
}
