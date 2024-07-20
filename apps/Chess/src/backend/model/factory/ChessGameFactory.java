package backend.model.factory;

import backend.business.modelLogic.Movement;
import backend.business.modelLogic.PawnPromotion;
import backend.model.game.ChessGame;
import backend.model.figure.value.Coord;
import backend.model.figure.value.PlayerColor;
import backend.model.figure.model.Figure;
import backend.model.game.ChessGameImpl;
import backend.model.repository.FigureRepository;
import backend.model.repository.FigureRepositoryImpl;

import java.util.List;

public class ChessGameFactory {

    private FigureFactory figureFactory;
    private CoordsFactory coordsFactory;

    public ChessGameFactory(FigureFactory figureFactory, CoordsFactory coordsFactory) {
        this.figureFactory = figureFactory;
        this.coordsFactory = coordsFactory;
    }

    public ChessGame buildNewGame(PlayerColor colorUp) {
        FigureRepository figureRepository = buildRepository(colorUp);
        return new ChessGameImpl(figureRepository, new Movement(),new PawnPromotion());
    }

    private FigureRepository buildRepository(PlayerColor colorUp) {
        List<Figure> figures = figureFactory.buildAllFigures(colorUp);
        List<Coord> fields = coordsFactory.buildBoardCoords();
      return new FigureRepositoryImpl(figures, fields);
    }


}
