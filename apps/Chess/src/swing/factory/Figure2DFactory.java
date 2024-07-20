package swing.factory;

import backend.border.Controller;
import backend.model.figure.model.Figure;
import swing.model.figure2d.Figure2D;
import swing.model.figure2d.Figure2DImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Figure2DFactory {


    public Figure2DFactory() {
    }

    public List<Figure2D> buildFiguresForBoard(Controller interpreter) {
        List<Figure2D> figures2D = new ArrayList<>();
        Collection<Figure> figures = interpreter.getAll();
        for (Figure figure : figures) {
            Figure2D figure2D = new Figure2DImpl(figure);
            figures2D.add(figure2D);
        }

        return figures2D;
    }
}
