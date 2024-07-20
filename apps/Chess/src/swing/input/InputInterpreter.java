package swing.input;

import backend.model.figure.value.Coord;
import swing.Pixel;

import java.util.ArrayList;
import java.util.List;


//zbiera growe informacje z myszy - dzięki zastosowaniu wzorca wizytator przesyła informację o klikniętych polach
public final class InputInterpreter {

    private List<HoverObserver> hoverObservers;
    private List<ClickObserver> clickObservers;
    private Coord hoveredCoord;


    public InputInterpreter() {
        hoverObservers = new ArrayList<>();
        clickObservers = new ArrayList<>();
    }

    void updateMousePosition(Pixel mousePixel) {
        Coord newCoord = mousePixel.convertToCoords();

        if (newCoord.equals(hoveredCoord)) {
            return;
        }
        hoveredCoord = newCoord;
        nextHoveredCoord(hoveredCoord);
    }


    private void nextHoveredCoord(Coord hoveredCoord) {
        for (HoverObserver observer : hoverObservers) {
            observer.hoveredCoordChanged(hoveredCoord);
        }
    }

    void click() {
        for (ClickObserver observer : clickObservers) {
            observer.onClick(hoveredCoord);
        }
    }


    public void registerObserver(HoverObserver observer) {
        hoverObservers.add(observer);
    }
    public void registerObserver(ClickObserver observer) {
        clickObservers.add(observer);
    }

}
