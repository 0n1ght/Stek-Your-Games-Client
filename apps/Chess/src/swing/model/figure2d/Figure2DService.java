package swing.model.figure2d;

import swing.Tickable;
import swing.repository.Figure2DRepository;
import swing.states.states.MovingState;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Figure2DService implements Tickable {
    private Figure2DRepository repository;
    private  boolean moving;
    private MovingState caller;
    private boolean send;

    public Figure2DService(Figure2DRepository repository) {
        this.repository = repository;
        send = true;
    }

    public void moveFigures(MovingState movingState) {
        moving = true;
        send = false;
        Collection<Figure2D> figures = repository.getAll();
        figures.forEach(Figure2D::move);
        caller = movingState;
    }

    @Override
    public void tick() {
        if (send) {
            return;
        }
        moving = areMoving();

        if (!moving && Objects.nonNull(caller)) {
            send = true;
            caller.movingStopped();
        }
    }

    private boolean areMoving() {
        return repository.getAll()
                .stream()
                .anyMatch(figure2D -> figure2D.isMoving());
    }

    public List<Figure2D> getAll() {
        return repository.getAll();
    }

    public void checkIfBeat() {
        for (Figure2D figure2D : getAll()) {
            figure2D.checkIfBeat();
        }
    }
}
