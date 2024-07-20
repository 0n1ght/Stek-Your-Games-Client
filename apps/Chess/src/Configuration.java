import backend.bot.Bot;
import backend.bot.AISimulation;
import backend.border.ControllerImpl;
import backend.business.analize.analizator.*;
import backend.business.manipulator.Manipulator;
import backend.business.manipulator.RealManipulator;
import backend.business.manipulator.SimulationManipulator;
import backend.business.manipulator.SimulationManipulatorImpl;
import backend.model.game.ChessGame;
import backend.border.Controller;
import backend.model.factory.ChessGameFactory;
import backend.model.factory.CoordsFactory;
import backend.business.analize.analizator.CheckSimulator;
import swing.display.effects.EffectManager;
import swing.input.players.BotCommunicator;
import swing.input.players.ClickCommand;
import swing.input.players.OfflinePlayer;
import swing.model.figure2d.Figure2D;
import swing.factory.Figure2DFactory;
import swing.model.figure2d.Figure2DService;
import swing.repository.Figure2DRepository;
import swing.display.Display;
import swing.core.GameEngine;
import swing.core.Gameplay;
import swing.factory.FieldFactory;
import backend.model.factory.FigureFactory;
import swing.input.InputInterpreter;
import swing.input.MouseManager;
import swing.states.general.State;
import swing.states.general.StateManager;
import swing.states.general.StateManagerImpl;
import swing.states.general.StateType;
import swing.states.states.MovingState;
import swing.states.states.SelectedState;
import swing.states.states.WaitingState;
import swing.model.field.Field;
import backend.model.figure.value.Coord;
import swing.repository.FieldRepository;
import swing.repository.FieldRepositoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static backend.model.figure.value.PlayerColor.BLACK;
import static backend.model.figure.value.PlayerColor.WHITE;

//Build app objects
final class Configuration {

    private final String GAME_TITLE = "Szachy";
    private ChessGame game;


    GameEngine buildGame() {
        //figures and board
        FigureFactory figureFactory = new FigureFactory();
        CoordsFactory coordsFactory = new CoordsFactory();
        ChessGameFactory chessGameFactory = new ChessGameFactory(figureFactory, coordsFactory);
        game =  chessGameFactory.buildNewGame(BLACK);

        //universal small components
        CollisionDetector collisionDetector = new CollisionDetector();
        MoveEventReader moveEventReader = new MoveEventReader();
        BasicValidator basicValidator = new BasicValidator(collisionDetector,moveEventReader);

        //check simulation
        MoveValidator simulationValidator = new MoveValidationWithoutCheckControl(basicValidator);
        SimulationAnalizator simulationAnalizator = new SimulationAnalizatorImpl(simulationValidator,game);
        SimulationManipulator checkSimulation = new SimulationManipulatorImpl(game,simulationAnalizator);
        CheckSimulator checkSimulator =  new CheckSimulator(checkSimulation,simulationAnalizator,moveEventReader);
        CheckValidator checkValidator = new CheckValidator(checkSimulator);

        //real analizator and manipulator
        MoveValidator realValidator = new MoveValidationWitchCheckControl(basicValidator,checkValidator);
        AnalizatorBase realAnalizator = new AnalizatorBase(realValidator,game);
        Manipulator realManipulator = new RealManipulator(game, realAnalizator);

        //main controller
        Controller controller = new ControllerImpl(realManipulator,game,realAnalizator);

        //si module
        MoveValidator aiValidator = new MoveValidationWithoutCheckControl(basicValidator);
        SimulationAnalizator aiAnalizator = new SimulationAnalizatorImpl(aiValidator,game);
        SimulationManipulator aiManipulator = new SimulationManipulatorImpl(game,aiAnalizator);
        AISimulation aiSimulation = new AISimulation(game,aiManipulator, aiAnalizator,realAnalizator);
        Bot bot = new Bot(controller,BLACK, aiSimulation);
        return buildFrontend(controller,bot);
    }

    private GameEngine buildFrontend(Controller controller, Bot bot) {
        Display display = buildDisplay();
        FieldRepository fieldRepository = buildFieldRepository();
        InputInterpreter inputInterpreter = buildInputInterpreter();
        MouseManager mouseManager = buildMouseManager(display, inputInterpreter);
        Figure2DRepository figure2DRepository = buildFigure2DRepository(controller);
        Figure2DService service = new Figure2DService(figure2DRepository);
        Gameplay gameplay =new Gameplay(fieldRepository, service,mouseManager);
        EffectManager effectsService = new EffectManager(fieldRepository);
        StateManager stateManager = buildStatesModule(controller,service);

        ClickCommand command = new ClickCommand(stateManager);
        OfflinePlayer offlinePlayer1 = new OfflinePlayer(command, WHITE);
        OfflinePlayer offlinePlayer2 = new OfflinePlayer(command, BLACK);
        BotCommunicator botCommunicator = new BotCommunicator(command,BLACK,bot);

        stateManager.register(effectsService);
        inputInterpreter.registerObserver(effectsService);
        inputInterpreter.registerObserver(offlinePlayer1);
        stateManager.register(botCommunicator);
//        inputInterpreter.registerObserver(offlinePlayer2);
        return new GameEngine(display, gameplay);
    }

    private Display buildDisplay() {
        return  new Display(GAME_TITLE);
    }

    private FieldRepository buildFieldRepository() {
        FieldFactory fieldBuilder = new FieldFactory();
        Map<Coord, Field> fields = fieldBuilder.buildFields();
        return new FieldRepositoryImpl(fields);
    }

    private InputInterpreter buildInputInterpreter() {
       return new InputInterpreter();
    }

    private MouseManager buildMouseManager(Display display, InputInterpreter inputInterpreter) {
       return new MouseManager(display, inputInterpreter);
    }

    private Figure2DRepository buildFigure2DRepository(Controller controller) {
        Figure2DFactory figure2DFactory = new Figure2DFactory();
        List<Figure2D> figures2D = figure2DFactory.buildFiguresForBoard(controller);
        return new Figure2DRepository(figures2D);
    }

    private StateManager buildStatesModule(Controller controller, Figure2DService service) {
        StateManagerImpl stateManager = new StateManagerImpl();
        State waiting = new WaitingState(stateManager,controller);
        State selected = new SelectedState(stateManager,controller);
        State moving = new MovingState(stateManager,controller, service);
        Map<StateType, State> states = new HashMap<>();
        states.put(waiting.getType(), waiting);
        states.put(selected.getType(), selected);
        states.put(moving.getType(), moving);
        stateManager.attachPossibleStates(states,controller);
        return stateManager;
    }


}
