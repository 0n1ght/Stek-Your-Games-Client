package swing.core;

import swing.display.Display;

import java.awt.*;
import java.awt.image.BufferStrategy;

//system timer that ticks and renders game every frame
public final class GameEngine implements Runnable {

    private Thread thread;
    private boolean running;
    private BufferStrategy strategy;

    private final Display display;
    private final Gameplay gameplay;


    public GameEngine(Display display, Gameplay gameplay) {
        running = false;
        this.display = display;
        this.gameplay = gameplay;
    }


    @Override
    public void run() {
        int fps = 60;
        double timePerTick = 1_000_000_000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                delta--;
            }

            if (timer >= 1_000_000_000) {
                timer = 0;
            }
        }
        stop();
    }

    private void tick() {
        gameplay.tick();
    }

    private void render() {
        if (!ensureBufferReady()) return;
        renderFrame();
    }

    private boolean ensureBufferReady() {
        Canvas canvas = display.getCanvas();
        strategy = canvas.getBufferStrategy();

        if (strategy == null) {
            canvas.createBufferStrategy(3);
            return false;
        }
        return true;
    }

    private void renderFrame() {
        Graphics graphics = strategy.getDrawGraphics();
        graphics.clearRect(0, 0, Display.getWidth(), Display.getHeight());
        gameplay.render(graphics);
        strategy.show();
        graphics.dispose();
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
        display.showGame();
    }

    private synchronized void stop() {
        if (!running) {
            return;
        }

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
