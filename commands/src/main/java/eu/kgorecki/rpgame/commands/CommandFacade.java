package eu.kgorecki.rpgame.commands;

import eu.kgorecki.rpgame.commands.application.Service;
import eu.kgorecki.rpgame.commands.dto.ExecuteCommandCommand;

public class CommandFacade {

    private final Service service;

    CommandFacade(Service service) {
        this.service = service;
    }

    public void execute(ExecuteCommandCommand executeCommand) {
        service.execute(executeCommand);
    }
}
