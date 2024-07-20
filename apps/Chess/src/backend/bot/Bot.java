package backend.bot;

import backend.border.Controller;
import backend.model.Move;
import backend.business.analize.event.MoveEvent;
import backend.model.figure.model.Figure;
import backend.model.figure.value.PlayerColor;
import backend.business.analize.analizator.PossibleMoves;

import java.util.*;
import java.util.stream.Collectors;

public class Bot extends AbstractBot {


    public Bot(Controller controller, PlayerColor player, AISimulation simulation) {
        super(controller, player, simulation);
    }

    @Override
    Move findBestMove() {
        return computeBestMove();
    }

    private MoveEvent computeBestMove() {
        List<MoveEvent> rated = computeRatedMoves();
      if(rated.isEmpty()) return MoveEvent.getEmpty();
        Collections.sort(rated);
//        System.out.println(rated);
//        System.out.println(rated.get(0));
        return rated.get(0);
    }

    private List<MoveEvent> computeRatedMoves() {
        List<MoveEvent> moves = computeMovesList();
        rateMoves(moves);
        return moves;
    }

    private List<MoveEvent> computeMovesList() {
        return controller.getAllOfPlayer(player)
                .stream()
                .map(figure -> controller.getPossibleMoves(figure))
                .map(PossibleMoves::getAll)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

    }

    private void rateMoves(List<MoveEvent> moves) {
        moves.forEach(move -> simulation.rateMove(move));
    }
}
