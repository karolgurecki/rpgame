package eu.kgorecki.rpgame.commands.application;

import eu.kgorecki.rpgame.commands.application.ports.UserInteractionPort;
import eu.kgorecki.rpgame.commands.dto.ExecuteCommandCommand;

public class Service {
    private final CommandResolver commandResolver;
    private final UserInteractionPort userInteractionPort;

    public Service(CommandResolver commandResolver, UserInteractionPort userInteractionPort) {
        this.commandResolver = commandResolver;
        this.userInteractionPort = userInteractionPort;
    }


    public void execute(ExecuteCommandCommand executeCommand) {
        commandResolver.resolve(executeCommand)
                .ifPresentOrElse(Command::execute,
                        () -> userInteractionPort.displayText(Messages.COMMAND_NOT_FOUNT));
    }
}
