package swing.display.assets;

import backend.model.figure.value.PlayerColor;
import backend.model.figure.value.FigureType;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import static backend.model.figure.value.PlayerColor.BLACK;
import static backend.model.figure.value.PlayerColor.WHITE;
import static backend.model.figure.value.FigureType.*;

public enum Asset {
    PAWN_B(PAWN, BLACK),
    PAWN_W(PAWN, WHITE),
    ROOK_B(ROOK, BLACK),
    ROOK_W(ROOK, WHITE),
    KNIGHT_B(KNIGHT, BLACK),
    KNIGHT_W(KNIGHT, WHITE),
    BISHOP_B(BISHOP, BLACK),
    BISHOP_W(BISHOP, WHITE),
    QUEEN_B(QUEEN, BLACK),
    QUEEN_W(QUEEN, WHITE),
    KING_B(KING, BLACK),
    KING_W(KING, WHITE),
    UNKNOWN(FigureType.UNKNOWN, PlayerColor.NO_ONE);

    private static final String FILE_EXTENSION = ".png";
    private static final String PATH = "/textures/standard/";


    private BufferedImage asset;
    private PlayerColor color;
    private FigureType figureType;

    Asset(FigureType figureType, PlayerColor color) {
        this.figureType = figureType;
        this.color = color;
        generateBufferedImage();

    }

    private void generateBufferedImage() {
        if (figureType.equals(FigureType.UNKNOWN)) {
            return;
        }
        asset = ImageLoader.loadImage(getAssetPath());
    }


    private String getAssetPath() {
        StringBuilder fileName = new StringBuilder();
        fileName
                .append(figureType.name().toLowerCase())
                .setCharAt(0, name().charAt(0));
        fileName.append(color.name().charAt(0));

        return String.format("%s%s%s",
                PATH, fileName, FILE_EXTENSION);
    }

    public BufferedImage getAssetImage() {
        return asset;
    }

    public static Asset getAsset(FigureType figureType, PlayerColor color) {
        return Arrays.stream(Asset.values())
                .filter(asset -> asset.figureType.equals(figureType) && asset.color.equals(color))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
