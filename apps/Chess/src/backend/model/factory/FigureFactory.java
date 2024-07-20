package backend.model.factory;

import backend.model.figure.model.*;
import backend.business.modelLogic.behavior.*;
import backend.model.figure.value.BoardSide;
import backend.model.figure.value.Coord;
import backend.model.figure.value.PlayerColor;
import backend.model.figure.value.FigureType;

import java.util.*;
import java.util.List;

import static backend.model.figure.value.BoardSide.BOTTOM;
import static backend.model.figure.value.BoardSide.TOP;
import static backend.model.figure.value.FigureType.*;

//builds Figures
public final class FigureFactory {

    private static Figure figure;

    private List<FigureType> order;
    private PlayerColor buildingPlayer;
    private BoardSide buildingSide;


    public FigureFactory() {
        order = Arrays.asList(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK);
//        order = Arrays.asList(KING);
//        order = List.of(UNKNOWN, KNIGHT, UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN, KNIGHT, UNKNOWN);
//        order = new ArrayList<>();
        buildingPlayer = PlayerColor.NO_ONE;
        buildingSide = BoardSide.UNKNOWN;
    }

    public List<Figure> buildAllFigures(PlayerColor colorOnTop) {
        List<Figure> allFigures = new ArrayList<>();
        allFigures.addAll(buildOneSide(colorOnTop, TOP));
        allFigures.addAll(buildOneSide(PlayerColor.nextColor(colorOnTop), BOTTOM));
        return allFigures;
    }


    private List<Figure> buildOneSide(PlayerColor color, BoardSide side) {
        buildingPlayer = color;
        buildingSide = side;
        List<Figure> figures = new ArrayList<>();
        figures.addAll(buildPawns());
        figures.addAll(buildFigures());
        return figures;
    }

    private List<Figure> buildPawns() {
        List<Figure> pawns = new ArrayList<>();
        int column = 1;
        for (int i = 0; i < 8; i++) {
            Coord coord = new Coord(buildingSide.getPawnsRow(), column++);
            pawns.add(buildFigure(PAWN, coord, buildingPlayer));
        }
        return pawns;
    }

    private List<Figure> buildFigures() {
        List<Figure> figures = new ArrayList<>();
        int column = 1;
        for (int i = 0; i < order.size(); i++) {
            FigureType figureType = order.get(i);
            Coord coord = new Coord(buildingSide.getFiguresRow(), column++);
            Figure newFigure = buildFigure(figureType, coord, buildingPlayer);
            figures.add(newFigure);
        }
        return figures;
    }


    private Figure buildFigure(FigureType figureType, Coord coord, PlayerColor color) {
        switch (figureType) {
            case PAWN:
                return new Pawn(coord, color, buildBehavior(figureType));
            case ROOK:
                return new Rook(coord, color, buildBehavior(figureType));
            case KNIGHT:
                return new Knight(coord, color, buildBehavior(figureType));
            case BISHOP:
                return new Bishop(coord, color, buildBehavior(figureType));
            case QUEEN:
                return new Queen(coord, color, buildBehavior(figureType));
            case KING:
                return new King(coord, color, buildBehavior(figureType));
            default:
                return getEmpty();
        }
    }

    private Behavior buildBehavior(FigureType type) {
        switch (type) {
            case PAWN:
                return new EnPassantBehavior( new PawnBehavior(new BehaviorImpl()));
            case ROOK:
                return new RookBehavior(new BehaviorImpl());
            case KNIGHT:
                return new KnightBehavior(new BehaviorImpl());
            case BISHOP:
                return new BishopBehavior(new BehaviorImpl());
            case QUEEN:
                return new RookBehavior(new BishopBehavior(new BehaviorImpl()));
            case KING:
                return new KingCastlingBehavior( new KingBehavior(new BehaviorImpl()));
            default:
                throw new NullPointerException();
        }
    }

    public static Figure getEmpty() {
        if (Objects.isNull(figure)) {
            figure = new Empty();
        }
        return figure;
    }

}
