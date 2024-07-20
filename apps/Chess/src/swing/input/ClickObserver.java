package swing.input;

import backend.model.figure.value.Coord;

public interface ClickObserver {
    void onClick(Coord coord);
}
