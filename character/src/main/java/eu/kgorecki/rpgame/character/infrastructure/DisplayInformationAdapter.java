package eu.kgorecki.rpgame.character.infrastructure;

import eu.kgorecki.rpgame.character.domain.UserInteractionPort;
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacade;

public class DisplayInformationAdapter implements UserInteractionPort {

    private final UserInterfaceFacade userInterfaceFacade;

    public DisplayInformationAdapter(UserInterfaceFacade userInterfaceFacade) {
        this.userInterfaceFacade = userInterfaceFacade;
    }

    @Override
    public void displayText(String text) {
        userInterfaceFacade.displayText(text);
    }

    @Override
    public String getUserInputWithTextInTheSameLine(String text) {
        return userInterfaceFacade.getUserInputWithTextInTheSameLine(text);
    }
}
