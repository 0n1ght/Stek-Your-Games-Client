package backend.business.analize.analizator;

import backend.business.analize.event.MoveEvent;
import backend.business.analize.event.MovingStyle;
import backend.model.figure.value.Coord;

import java.util.*;
import java.util.stream.Collectors;

import static backend.business.analize.event.MovingStyle.UNKNOWN;


public final class PossibleMoves {
    private static PossibleMoves nullObject;

    //todo przerobić na set
    private List<MoveEvent> moves;

    public PossibleMoves(List<MoveEvent> moves) {
        this.moves = moves;
    }

    public boolean isMovePossible(Coord destination) {
        return getByDestination(destination).isValidMove();
    }

    boolean isAttackPossible(Coord destination) {
        return getByDestination(destination).isAttack();
    }

    void removeDefendingMoves() {
        moves = moves.stream()
                .filter(moveEvent -> !moveEvent.isDefence())
                .collect(Collectors.toList());
    }

    void retainOnlyDefendingMoves() {
        moves = moves.stream()
                .filter(MoveEvent::isDefence)
                .collect(Collectors.toList());
    }

    public Collection<MoveEvent> getMovable() {
        return moves.stream()
                .filter(MoveEvent::isMove)
                .collect(Collectors.toSet());
    }

    public Collection<MoveEvent> getAttackable() {
        return moves.stream()
                .filter(MoveEvent::isAttack)
                .collect(Collectors.toSet());
    }


    public static PossibleMoves getEmpty() {
        if (Objects.isNull(nullObject)) {
            nullObject = new PossibleMoves(new LinkedList<>());
        }
        return nullObject;
    }


    public List<MoveEvent> getAll() {
        return moves;
    }

    //todo metoda prawdopodobnie do wywalenia jeśli movy w programie będą działać na FullMove tylko
    public MovingStyle getType(Coord destination) {
        MoveEvent event = getByDestination(destination);
        if (!event.isValidMove()) return UNKNOWN;
        return event.getStyle();
    }

    boolean areThereAnyMoves() {
        return !moves.isEmpty();
    }

    public MoveEvent getByDestination(Coord destination) {
        return moves.stream()
                .filter(move -> move.getDestination().equals(destination))
                .findAny()
                .orElse(MoveEvent.getEmpty());
    }

    @Override
    public String toString() {
        return "PossibleMoves{" +
                "moves=" + moves +
                '}';
    }

    public boolean hasMoveOn(Coord destination) {
        return moves.stream()
                .anyMatch(moveEvent -> moveEvent.getDestination().equals(destination));
    }
}
