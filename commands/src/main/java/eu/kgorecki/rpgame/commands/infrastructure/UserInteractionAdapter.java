package eu.kgorecki.rpgame.commands.infrastructure;

import eu.kgorecki.rpgame.commands.application.ports.UserInteractionPort;
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacade;

public class UserInteractionAdapter implements UserInteractionPort {

    private final UserInterfaceFacade userInterfaceFacade;

    public UserInteractionAdapter(UserInterfaceFacade userInterfaceFacade) {
        this.userInterfaceFacade = userInterfaceFacade;
    }

    @Override
    public void displayText(String text) {
        userInterfaceFacade.displayText(text);
    }
}
