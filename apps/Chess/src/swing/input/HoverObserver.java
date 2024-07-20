package swing.input;

import backend.model.figure.value.Coord;

public interface HoverObserver {

    void hoveredCoordChanged(Coord coord);

}
