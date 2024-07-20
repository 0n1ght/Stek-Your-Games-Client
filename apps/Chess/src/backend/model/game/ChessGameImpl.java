package backend.model.game;

import backend.business.analize.event.MoveEvent;
import backend.business.modelLogic.Movement;
import backend.business.modelLogic.PawnPromotion;
import backend.model.figure.value.Coord;
import backend.model.figure.value.FigureType;
import backend.model.figure.value.PlayerColor;
import backend.model.figure.model.Figure;
import backend.model.repository.FigureRepository;
import backend.model.factory.FigureFactory;

import java.util.Collection;
import java.util.Set;

import static backend.model.figure.value.PlayerColor.NO_ONE;
import static backend.model.figure.value.PlayerColor.WHITE;

//punkt dostępowy dla stanu rozgrywki
public class ChessGameImpl implements ChessGame, Cloneable {

    private FigureRepository repository;
    private Movement movement;
    private PawnPromotion pawnPromotion;

    private Figure lastPlayed;
    private PlayerColor actualColor;
    private PlayerColor check;
    private boolean pat;
    private boolean checkMate;

    public ChessGameImpl(FigureRepository repository, Movement movement, PawnPromotion pawnPromotion) {
        this.movement = movement;
        this.repository = repository;
        this.pawnPromotion = pawnPromotion;

        actualColor = WHITE;
        check = NO_ONE;
        lastPlayed = FigureFactory.getEmpty();
        pat = false;
    }

    private ChessGameImpl() {
    }

    @Override
    protected ChessGame clone() throws CloneNotSupportedException {
        ChessGameImpl copy = new ChessGameImpl();
        copy.check = check;
        copy.actualColor = actualColor;
        copy.pat = pat;
        copy.checkMate = checkMate;
        copy.lastPlayed = lastPlayed.copy();
        copy.repository =  this.repository.copy();
        copy.movement = movement;
        copy.pawnPromotion = pawnPromotion;
        return copy;
    }


    @Override
    public void move(MoveEvent move) {
        movement.move(move,this, repository);

    }

    @Override
    public void tryPromotingPawn() {
        pawnPromotion.tryPromotingPawn(this,repository);
    }

    @Override
    public PlayerColor getPlayer() {
        return actualColor;
    }

    @Override
    public void setPlayer(PlayerColor player) {
        actualColor = player;
    }

    @Override
    public void setCheck(PlayerColor player) {
        this.check = player;
    }

    @Override
    public PlayerColor isCheck() {
        return check;
    }

    @Override
    public ChessGame copy() throws CloneNotSupportedException {
        return this.clone();
    }

    @Override
    public boolean hasFigure(Coord coord) {
        return repository.hasFigure(coord);
    }

    @Override
    public Figure getFigure(Coord coord) {
        return repository.get(coord);
    }

    @Override
    public Figure getFigureByType(FigureType type, PlayerColor player) {
        return repository.getFigureByType(type, player);
    }

    @Override
    public Set<Figure> getAllFiguresOfPlayer(PlayerColor player) {
        return repository.getAllOfPlayer(player);
    }

    @Override
    public Collection<Figure> getAll() {
        return repository.getAll();
    }

    @Override
    public PlayerColor lastPlayer() {
        return PlayerColor.nextColor(actualColor);
    }

    @Override
    public Collection<Figure> getAllFiguresOfActualPlayer() {
        return getAllFiguresOfPlayer(actualColor);
    }

    @Override
    public void setPat(boolean pat) {
        this.pat = pat;
    }

    @Override
    public void setCheckMate(boolean checkMate) {
        this.checkMate = checkMate;
    }

    @Override
    public boolean isPat() {
        return pat;
    }

    @Override
    public boolean isCheckMate() {
        return checkMate;
    }

    public Figure getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(Figure lastPlayed) {
        this.lastPlayed = lastPlayed;
    }


    @Override
    public String toString() {
        return "ChessGameImpl{" +
                "repository=" + repository +
                ", movement=" + movement +
                ", pawnPromotion=" + pawnPromotion +
                ", lastPlayed=" + lastPlayed +
                ", actualColor=" + actualColor +
                ", check=" + check +
                ", pat=" + pat +
                ", checkMate=" + checkMate +
                '}';
    }
}
