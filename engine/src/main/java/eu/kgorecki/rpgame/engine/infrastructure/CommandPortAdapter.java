package eu.kgorecki.rpgame.engine.infrastructure;

import eu.kgorecki.rpgame.commands.CommandFacade;
import eu.kgorecki.rpgame.commands.dto.ExecuteCommandCommand;
import eu.kgorecki.rpgame.engine.domain.CommandPort;
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacade;

public class CommandPortAdapter implements CommandPort {

    private final UserInterfaceFacade userInterfaceFacade;
    private final CommandFacade commandFacade;

    public CommandPortAdapter(UserInterfaceFacade userInterfaceFacade, CommandFacade commandFacade) {
        this.userInterfaceFacade = userInterfaceFacade;
        this.commandFacade = commandFacade;
    }

    @Override
    public void execute(String command) {
        commandFacade.execute(new ExecuteCommandCommand(command));
    }

    @Override
    public String read() {
        return userInterfaceFacade.getUserInput();
    }
}
