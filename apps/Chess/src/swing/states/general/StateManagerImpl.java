package swing.states.general;

import backend.model.factory.CoordsFactory;
import backend.model.factory.FigureFactory;
import backend.border.Controller;
import backend.model.figure.value.Coord;
import backend.business.analize.analizator.PossibleMoves;
import backend.model.figure.model.Figure;
import backend.model.figure.value.PlayerColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static backend.model.figure.value.PlayerColor.NO_ONE;
import static backend.model.figure.value.PlayerColor.WHITE;
import static swing.states.general.StateType.WAITING;

//przechowuje informacje wspólne dla stanów i pozwala na zmianę stanu, ogłasza reszcie programu
//te dane
public class StateManagerImpl implements StateManager, StateStatus {

    private final static StateType DEFAULT_STATE = WAITING;

    //DEPENDENCIES
    private Controller interpreter;

    //STATE
    private State state;
    private Map<StateType, State> states;

    //OBSERVER
    private List<StateObserver> observers;

    //GAME DATA FOR STATES
    private PlayerColor player;
    private PossibleMoves possibleMoves;
    private Figure selected;
    private PlayerColor check;

    public StateManagerImpl() {
        observers = new ArrayList<>();
        player = WHITE;
        possibleMoves = PossibleMoves.getEmpty();
        selected = FigureFactory.getEmpty();
        check = NO_ONE;
    }

    //INITIALIZATION
    public void attachPossibleStates(Map<StateType, State> states, Controller interpreter) {
        this.states = states;
        this.interpreter = interpreter;
        changeState(DEFAULT_STATE);

    }

    //STATE PATTERN
    @Override
    public void onClick(Coord coord) {
        state.onClick(coord);
    }

    public void changeState(StateType newState) {
        state = states.get(newState);
        initiateNewState();
    }

    private void initiateNewState() {
        refreshGameData();
        state.initiateState();
        announceStateChanged(this);
    }

    private void refreshGameData() {
        player = interpreter.getPlayer();
        possibleMoves = interpreter.getPossibleMoves(selected);
        check = interpreter.isCheck();
    }

    //OBSERVER PATTERN
    private void announceStateChanged(StateStatus status) {
        for (StateObserver observer : observers) {
            observer.gameStateChanged(status);
        }
    }

    public void register(StateObserver observer) {
        observers.add(observer);
    }


    //GETTERS SETTERS
    @Override
    public PossibleMoves getPossibleMoves() {
        return possibleMoves;
    }

    @Override
    public Coord getCoordIfCheck() {
        if (check.equals(NO_ONE)) {
            return CoordsFactory.getEmptyCoord();
        }
        return interpreter.getCheckCoord();
    }

    @Override
    public boolean isCheckMate() {
        return interpreter.isCheckMate();
    }

    @Override
    public boolean isPat() {
        return interpreter.isPat();
    }

    public void setPossibleMoves(PossibleMoves possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    public PlayerColor getPlayer() {
        return player;
    }

    public Figure getSelected() {
        return selected;
    }

    public void setSelected(Figure selected) {
        this.selected = selected;
    }

    @Override
    public StateType getType() {
        return state.getType();
    }


}
