package pl.monopoly.view;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private static Clip clip;

    public static void play() {

        try {

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("src\\main\\resources\\sounds\\music.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {

            throw new RuntimeException(e);
        }
    }

    public static void stop() {
        try {
            clip.stop();
        } catch (NullPointerException ignored) {}
    }
}
