package eu.kgorecki.rpgame.userinterface;

import eu.kgorecki.rpgame.userinterface.application.DisplayPort;
import eu.kgorecki.rpgame.userinterface.application.UserInputPort;
import eu.kgorecki.rpgame.userinterface.application.UserInteractionService;
import eu.kgorecki.rpgame.userinterface.infrastructure.ToConsoleDisplayAdapter;
import eu.kgorecki.rpgame.userinterface.infrastructure.UserInputFromSystemInAdapter;

public class UserInterfaceFacadeFactory {

    private static UserInterfaceFacade facadeInstance;

    private UserInterfaceFacadeFactory() {
    }

    public static UserInterfaceFacade createFacade() {
        if (facadeInstance == null) {
            facadeInstance = createFacade(new ToConsoleDisplayAdapter(), new UserInputFromSystemInAdapter());
        }

        return facadeInstance;
    }

    private static UserInterfaceFacade createFacade(DisplayPort displayInfrastructureService, UserInputPort userInputPort) {
        UserInteractionService service = new UserInteractionService(displayInfrastructureService, userInputPort);

        return new UserInterfaceFacade(service);
    }
}
