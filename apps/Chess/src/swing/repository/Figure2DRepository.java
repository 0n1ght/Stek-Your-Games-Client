package swing.repository;

import swing.model.figure2d.Figure2D;

import java.util.Collection;
import java.util.List;

public class Figure2DRepository {

    private List<Figure2D> figures;


    public Figure2DRepository(List<Figure2D> figures) {
        this.figures = figures;
    }

    public List<Figure2D> getAll() {
        return figures;
    }
}
