package eu.kgorecki.rpgame.userinterface;

import eu.kgorecki.rpgame.userinterface.application.DisplayPort;
import eu.kgorecki.rpgame.userinterface.infrastructure.UserInputAdapter;
import eu.kgorecki.rpgame.userinterface.application.UserInteractionService;
import eu.kgorecki.rpgame.userinterface.infrastructure.ToConsoleDisplayAdapter;
import eu.kgorecki.rpgame.userinterface.application.UserInputPort;

public class UserInterfaceFactory {

    private static UserInterfaceFacade facadeInstance;

    private UserInterfaceFactory() {
    }

    public static UserInterfaceFacade createFacade() {
        if (facadeInstance == null) {
            facadeInstance = createFacade(new ToConsoleDisplayAdapter(), new UserInputAdapter(System.in));
        }

        return facadeInstance;
    }

    private static UserInterfaceFacade createFacade(DisplayPort displayInfrastructureService, UserInputPort userInputPort) {
        UserInteractionService service = new UserInteractionService(displayInfrastructureService, userInputPort);

        return new UserInterfaceFacade(service);
    }
}
