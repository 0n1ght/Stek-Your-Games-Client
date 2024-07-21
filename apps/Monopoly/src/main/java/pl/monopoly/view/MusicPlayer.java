package pl.monopoly.view;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MusicPlayer {
    private static Clip clip;

    public static void play() {
        try (InputStream resourceStream = MusicPlayer.class.getResourceAsStream("/sounds/music.wav")) {
            if (resourceStream == null) {
                throw new IOException("Resource /sounds/music.wav not found");
            }

            // Wrap InputStream with BufferedInputStream
            BufferedInputStream bufferedStream = new BufferedInputStream(resourceStream);

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedStream);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}
