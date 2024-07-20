package swing.core;

import backend.bot.Bot;
import swing.Tickable;
import swing.model.figure2d.Figure2DService;
import swing.repository.Figure2DRepository;
import swing.input.MouseManager;
import swing.Renderable;
import swing.repository.FieldRepository;

import java.awt.*;
import java.util.Collection;
import java.util.stream.Collectors;

//what exacly should be rendered and ticked and in what order?
public final class Gameplay {

    private FieldRepository fieldRepository;
    private MouseManager mouseManager;
    private Figure2DService figure2DService;

    public Gameplay(FieldRepository fieldRepository, Figure2DService figure2DService, MouseManager mouseManager) {
        this.fieldRepository = fieldRepository;
        this.figure2DService = figure2DService;
        this.mouseManager = mouseManager;
    }

    void tick() {
        mouseManager.tick();
        tickObjects(figure2DService.getAll());
        figure2DService.tick();

    }

    void render(Graphics g) {
        renderObjects(fieldRepository.getAll(), g);
        renderObjects( figure2DService.getAll(), g);
    }

    private void renderObjects(Collection<? extends Renderable> objects, Graphics g) {
        for (Renderable object : objects) {
            object.render(g);
        }
    }

    private void tickObjects(Collection<? extends Tickable> objects) {
        for (Tickable object : objects) {
            object.tick();
        }
    }
}
