package eu.kgorecki.rpgame.commands.application.commands;

import eu.kgorecki.rpgame.commands.application.Command;
import eu.kgorecki.rpgame.commands.application.ports.ExitPort;

public class ExitCommand implements Command {

    private static final String COMMAND = "exit";
    private final ExitPort exitPort;

    public ExitCommand(ExitPort exitPort) {
        this.exitPort = exitPort;
    }

    @Override
    public void execute() {
        exitPort.exitGame();
    }

    @Override
    public String getStringForWithCommandMustByExecuted() {
        return COMMAND;
    }
}
