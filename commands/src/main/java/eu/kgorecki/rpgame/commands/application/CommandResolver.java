package eu.kgorecki.rpgame.commands.application;

import eu.kgorecki.rpgame.commands.dto.ExecuteCommandCommand;

import java.util.Map;
import java.util.Optional;

public class CommandResolver {
    private final Map<String,Command> commands;

    public CommandResolver(Map<String, Command> commands) {
        this.commands = commands;
    }

    Optional<Command> resolve(ExecuteCommandCommand executeCommand){
        String upperCaseCommand = executeCommand.getCommand().toUpperCase();

        return Optional.ofNullable(commands.get(upperCaseCommand));
    }
}
