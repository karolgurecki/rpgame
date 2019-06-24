package eu.kgorecki.rpgame.savestale;

import eu.kgorecki.rpgame.savestale.application.FileService;
import eu.kgorecki.rpgame.savestale.application.GameStateLoaderPort;
import eu.kgorecki.rpgame.savestale.application.GameStateSaverPort;
import eu.kgorecki.rpgame.savestale.application.UserInteractionPort;
import eu.kgorecki.rpgame.savestale.infrastructure.GameStateLoaderFromFileAdapter;
import eu.kgorecki.rpgame.savestale.infrastructure.GameStateSaverToFileAdapter;
import eu.kgorecki.rpgame.savestale.infrastructure.UserInteractionAdapter;
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacadeFactory;

public class SaveStateFacadeFactory {

    private static SaveStateFacade instace;

    private SaveStateFacadeFactory() {
    }

    public static SaveStateFacade createFacade() {
        if (instace == null) {
            instace = createFacade(new UserInteractionAdapter(UserInterfaceFacadeFactory.createFacade()),
                    new GameStateSaverToFileAdapter(),
                    new GameStateLoaderFromFileAdapter());
        }

        return instace;
    }

    private static SaveStateFacade createFacade(UserInteractionPort userInteractionPort, GameStateSaverPort gameStateSaverPort, GameStateLoaderPort gameStateLoaderPort) {
        FileService service = new FileService(gameStateLoaderPort, gameStateSaverPort, userInteractionPort);

        return new SaveStateFacade(service);
    }
}
