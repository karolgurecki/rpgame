package eu.kgorecki.rpgame.commands.application.commands;

import eu.kgorecki.rpgame.commands.application.Command;
import eu.kgorecki.rpgame.commands.application.CommandMapFactory;
import eu.kgorecki.rpgame.commands.application.ports.UserInteractionPort;

public class ListCommandCommand implements Command {

    private static final String COMMAND = "list command";
    private final UserInteractionPort userInteractionPort;

    public ListCommandCommand(UserInteractionPort userInteractionPort) {
        this.userInteractionPort = userInteractionPort;
    }

    @Override
    public void execute() {
        CommandMapFactory.commandMap().values()
                .stream()
                .map(Command::getStringForWithCommandMustByExecuted)
                .forEach(text -> userInteractionPort.displayText(text));
    }

    @Override
    public String getStringForWithCommandMustByExecuted() {
        return COMMAND;
    }
}
