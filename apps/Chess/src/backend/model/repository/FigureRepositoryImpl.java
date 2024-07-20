package backend.model.repository;

import backend.model.factory.FigureFactory;
import backend.model.figure.value.Coord;
import backend.model.figure.value.FigureType;
import backend.model.figure.value.PlayerColor;
import backend.model.figure.model.Figure;

import java.util.*;
import java.util.stream.Collectors;

public final class FigureRepositoryImpl implements FigureRepository, Cloneable {

     Map<Coord,Figure> figures;
    Set<Figure> original;

     Figure empty;

    public FigureRepositoryImpl(Collection<Figure> figures, Collection<Coord> coords) {
        empty = FigureFactory.getEmpty();
        this.figures = new HashMap<>();
        prepareMap(figures,coords);
        original = new HashSet<>(figures);
    }

    private FigureRepositoryImpl() {
    }




    private void prepareMap(Collection<Figure> figures, Collection<Coord> coords) {
        for (Coord coord : coords) {
            this.figures.put(coord, empty);
        }
        for (Figure figure : figures) {
            this.figures.put(figure.getCoord(), figure);
        }
    }

    @Override
    public Collection<Figure> getAll() {
        return figures.values()
                .stream()
                .filter(figure -> !figure.equals(empty))
                .collect(Collectors.toList());
    }

    @Override
    public Figure get(Coord coord) {
        return figures.getOrDefault(coord, FigureFactory.getEmpty());
    }



    @Override
    public void updateCoord(Figure figure) {
        figures.put(figure.getCoord(), figure);
    }

    @Override
    public void clearCoord(Coord coord) {
        if (!figures.get(coord).equals(empty)) {
            figures.put(coord, empty);
        }
    }

    @Override
    public Figure getEmptyFigure() {
        return empty;
    }

    @Override
    public boolean hasFigure(Coord coord) {
        return !get(coord).isNullObject();
    }

    @Override
    public Set<Figure> getAllOfPlayer(PlayerColor player) {
        return figures.values()
                .stream()
                .filter(figure -> figure.getPlayer().equals(player))
                .collect(Collectors.toSet());
    }

    @Override
    public Figure getFigureByType(FigureType type, PlayerColor player) {
        return getAllOfPlayer(player)
                .stream()
                .filter(figure -> figure.getType().equals(type))
                .findAny()
                .orElse(empty);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        FigureRepositoryImpl clone = new FigureRepositoryImpl();
        Map<Coord, Figure> figures = new HashMap<>();
        clone.empty = empty;
        for (Coord coord : this.figures.keySet()) {
            figures.put(coord, this.figures.get(coord).copy());
        }
        clone.figures = figures;
        Set<Figure> originalsCopy = new HashSet<>();
        for (Figure figure : original) {
            originalsCopy.add(figure.copy());
        }
        clone.original = originalsCopy;
        return clone;
    }

    @Override
    public FigureRepository copy() throws CloneNotSupportedException {
        return (FigureRepository) this.clone();
    }


    @Override
    public Figure getStrongestBeatenFigure(PlayerColor player) {
        return original
                .stream()
                .filter(figure -> figure.getPlayer().equals(player))
                .filter(Figure::isBeaten)
                .sorted((fig1, fig2) -> Integer.compare(fig2.getStrength(),fig1.getStrength()))
                .findAny()
                .orElse(empty);
    }

}
