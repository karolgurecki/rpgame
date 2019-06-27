package eu.kgorecki.rpgame.commands;

import eu.kgorecki.rpgame.commands.application.Command;
import eu.kgorecki.rpgame.commands.application.CommandMapFactory;
import eu.kgorecki.rpgame.commands.application.CommandResolver;
import eu.kgorecki.rpgame.commands.application.Service;
import eu.kgorecki.rpgame.commands.application.UserInteractionPort;
import eu.kgorecki.rpgame.commands.infrastructure.UserInteractionAdapter;
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacadeFactory;

import java.util.Map;

public class CommandFacadeFactory {

    private static CommandFacade instance;

    private CommandFacadeFactory() {
    }

    public static CommandFacade createFacade() {
        if (instance == null) {
            instance = createFacade(CommandMapFactory.commandMap(),
                    new UserInteractionAdapter(UserInterfaceFacadeFactory.createFacade()));
        }

        return instance;
    }

    private static CommandFacade createFacade(Map<String, Command> commandMap, UserInteractionPort userInteractionPort) {
        CommandResolver commandResolver = new CommandResolver(commandMap);
        Service service = new Service(commandResolver, userInteractionPort);

        return new CommandFacade(service);
    }
}
