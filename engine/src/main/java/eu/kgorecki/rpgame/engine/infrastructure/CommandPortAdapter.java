package eu.kgorecki.rpgame.engine.infrastructure;

import eu.kgorecki.rpgame.engine.domain.CommandPort;
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacade;

public class CommandPortAdapter implements CommandPort {

    private final UserInterfaceFacade userInterfaceFacade;

    public CommandPortAdapter(UserInterfaceFacade userInterfaceFacade) {
        this.userInterfaceFacade = userInterfaceFacade;
    }

    @Override
    public void execute(String command) {

    }

    @Override
    public String read() {
        return userInterfaceFacade.getUserInput();
    }
}
