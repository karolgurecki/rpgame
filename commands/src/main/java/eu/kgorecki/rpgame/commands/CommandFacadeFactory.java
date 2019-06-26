package eu.kgorecki.rpgame.commands;

import eu.kgorecki.rpgame.commands.application.Command;
import eu.kgorecki.rpgame.commands.application.CommandResolver;
import eu.kgorecki.rpgame.commands.application.Service;

import java.util.Map;

public class CommandFacadeFactory {

    private static CommandFacade instance;

    private CommandFacadeFactory() {
    }

    public static CommandFacade createFacade() {
        if (instance == null) {
            instance = createFacade(Map.of());
        }

        return instance;
    }

    private static CommandFacade createFacade(Map<String, Command> commandMap) {
        CommandResolver commandResolver = new CommandResolver(commandMap);
        Service service = new Service(commandResolver);

        return new CommandFacade(service);
    }
}
