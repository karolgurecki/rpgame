package eu.kgorecki.rpgame.commands.application;

import eu.kgorecki.rpgame.commands.dto.ExecuteCommandCommand;

public class Service {
    private final CommandResolver commandResolver;

    public Service(CommandResolver commandResolver) {
        this.commandResolver = commandResolver;
    }


    public void execute(ExecuteCommandCommand executeCommand) {
        commandResolver.resolve(executeCommand)
                .ifPresent(Command::execute);
    }
}
