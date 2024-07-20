package swing.display.effects;

import backend.business.analize.event.MoveEvent;
import swing.factory.FieldFactory;
import swing.input.HoverObserver;
import backend.business.analize.analizator.PossibleMoves;
import swing.model.field.Field;
import backend.model.figure.model.Figure;
import swing.states.general.StateObserver;
import swing.states.general.StateStatus;
import swing.states.general.StateType;
import backend.model.figure.value.Coord;
import swing.repository.FieldRepository;

import javax.swing.*;

//trigger visual swing.display.effects that depends on state and swing.input
public final class EffectManager implements HoverObserver, StateObserver {

    private FieldRepository fieldRepository;

    private Field lastHoveredField;
    private Field selectedField;

    public EffectManager(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
        lastHoveredField = FieldFactory.getEmptyField();
        selectedField = FieldFactory.getEmptyField();
    }


    @Override
    public void gameStateChanged(StateStatus status) {
        StateType state = status.getType();

        switch (state) {
            case WAITING:
                setWaitingMode(status);
                break;
            case SELECTED:
                setSelectedMode(status);
                break;
        }
    }

    private void setSelectedMode(StateStatus status) {
        Figure figure = status.getSelected();
        selectedField = convertToField(figure.getCoord());
        selectedField.setSelected(true);
        PossibleMoves possibleMoves = status.getPossibleMoves();
        flashPossibleMoves(possibleMoves);
        flashAttackFields(possibleMoves);
    }

    private void flashPossibleMoves(PossibleMoves possibleMoves) {
        for (MoveEvent moveEvent : possibleMoves.getMovable()) {
            Field field = convertToField(moveEvent.getDestination());
            field.setMovable(true);
        }
    }

    private void flashAttackFields(PossibleMoves possibleMoves) {
        for (MoveEvent moveEvent : possibleMoves.getAttackable()) {
            Field field = convertToField(moveEvent.getDestination());
            field.setAttackable(true);
        }
    }

    private void setWaitingMode(StateStatus status) {
        selectedField.setSelected(false);

        for (Field field : fieldRepository.getAll()) {
            field.setMovable(false);
            field.setAttackable(false);
            field.setCheck(false);
        }
        //TODO przenieść do nowej klasy
        endGameIfCheckMate(status);
        endGameIfPat(status);

        Coord check = status.getCoordIfCheck();
        if (check.areNotValid()) return;
        fieldRepository.getByCoords(check).setCheck(true);    }


    @Override
    public void hoveredCoordChanged(Coord coord) {
        flashNewField(convertToField(coord));
    }


    private void flashNewField(Field newField) {
        lastHoveredField.setHovered(false);
        newField.setHovered(true);
        lastHoveredField = newField;
    }

    private void endGameIfCheckMate(StateStatus status) {
        if (!status.isCheckMate()) {
            return;
        }
        JOptionPane.showMessageDialog(null,"Szach mat!");
        System.exit(0);
    }

    private void endGameIfPat(StateStatus status) {
        if (!status.isPat()) {
            return;
        }
        JOptionPane.showMessageDialog(null,"Pat!");
        System.exit(0);
    }

    private Field convertToField(Coord coord) {
        return fieldRepository.getByCoords(coord);
    }

}
