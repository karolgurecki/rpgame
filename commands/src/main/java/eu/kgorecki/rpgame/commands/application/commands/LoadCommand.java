package eu.kgorecki.rpgame.commands.application.commands;

import eu.kgorecki.rpgame.commands.application.Command;
import eu.kgorecki.rpgame.commands.application.Messages;
import eu.kgorecki.rpgame.commands.application.ports.LoadPort;
import eu.kgorecki.rpgame.commands.application.ports.UserInteractionPort;

public class LoadCommand implements Command {

    private static final String COMMAND = "load state";
    private final LoadPort loadPort;
    private final UserInteractionPort userInteractionPort;

    public LoadCommand(LoadPort loadPort, UserInteractionPort userInteractionPort) {
        this.loadPort = loadPort;
        this.userInteractionPort = userInteractionPort;
    }

    @Override
    public void execute() {
        loadPort.load();

        userInteractionPort.displayText(Messages.LOAD_COMPLETE);
    }

    @Override
    public String getStringForWithCommandMustByExecuted() {
        return COMMAND;
    }
}
