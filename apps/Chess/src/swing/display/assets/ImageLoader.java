package swing.display.assets;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public final class ImageLoader {

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error while loading image... Please report this bug!");
            e.printStackTrace();
            System.exit(1);
        }
        return new BufferedImage(0,0,0);
    }
}
